package com.example.springsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置密码加密方式
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 配置用户认证
     * 这里的用户密码优先级,会高于配置文件的,配置文件中的密码会失效
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中定义用户、密码、角色信息
        auth.inMemoryAuthentication()
                .withUser("cmy").password("123456").roles("admin")
                .and()
                .withUser("guest").password("123").roles("guest");
    }

    /**
     * 配置web请求放行
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**", "/verifyCode/**");
    }

    @Bean
    public MyUserInfoFilter userInfoFilter() throws Exception {
        MyUserInfoFilter myUserInfoFilter = new MyUserInfoFilter();
        myUserInfoFilter.setAuthenticationManager(this.authenticationManager());

        //这些属性会在configure中失效
        myUserInfoFilter.setUsernameParameter("uname");
        myUserInfoFilter.setPasswordParameter("passwd");
        myUserInfoFilter.setFilterProcessesUrl("/doLogin");
        myUserInfoFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                response.sendRedirect("/hello");
                Map<String, Object> ret = new HashMap<>(8);
                ret.put("code", 200);
                ret.put("msg", "登录成功");
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(ret);
                writeResponse(json, response);
            }
        });
        myUserInfoFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                response.sendRedirect("/login.html");
                Map<String, Object> ret = new HashMap<>(8);
                ret.put("code", 999);
                ret.put("msg", exception.getMessage());
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(ret);
                writeResponse(json, response);
            }
        });
        return myUserInfoFilter;
    }

    private void writeResponse(String json, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
        writer.close();
    }

    /**
     * 配置鉴权逻辑
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
//                .loginProcessingUrl("/doLogin")
                //alwaysUse默认为false,代表会记录上一个页面地址,如果上一个页面地址不是登录页面,则登陆后跳转回上个页面;否则，跳转为指定的页面
                //重定向操作
//                .defaultSuccessUrl("/hello", true)
                .defaultSuccessUrl("/hello")
                //服务端跳转(因为是服务端跳转,会保留登录接口的请求类型,也就是POST,如果这里跳转的地址不是POST请求的,就会报405)
//                .successForwardUrl("/loginSuccess")
                //登录失败跳转地址
                //也是服务端跳转(这里和successForwardUrl不同,不会出现405的问题,不清楚原因)
                .failureForwardUrl("/loginFailure")
                //重定向操作,客户端跳转
//                .failureUrl("/login.html")
//                .usernameParameter("uname")
//                .passwordParameter("passwd")
                .permitAll()
                .and()
                .logout()
                //指定退出接口地址,这里是GET请求
                .logoutUrl("/logout")
                //指定退出接口地址,这里是POST请求的实现方式
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"));
                //清楚cookie
                .deleteCookies("name")
                .permitAll()
                .and()
                .csrf().disable()
                .addFilterAt(userInfoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 验证码生成类
     *
     * @return
     */
    @Bean
    Producer verifyCode() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "150");
        properties.setProperty("kaptcha.image.height", "50");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789abcdefghijklmnopqrstuvwxyz");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
