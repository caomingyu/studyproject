package com.example.springsecuritydemo.service.impl;

import com.example.springsecuritydemo.mapper.ScRoleMapper;
import com.example.springsecuritydemo.mapper.ScUserMapper;
import com.example.springsecuritydemo.model.ScRole;
import com.example.springsecuritydemo.model.ScUser;
import com.example.springsecuritydemo.model.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service
public class SecurityScUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    ScUserMapper userMapper;
    @Autowired
    ScRoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ScUser scUser = new ScUser();
        scUser.setName(username);
        List<ScUser> userList = userMapper.selectList(scUser);
        if (CollectionUtils.isEmpty(userList)) {
            throw new UsernameNotFoundException("找不到用户:" + username);
        }
        if (userList.size() > 1) {
            throw new UsernameNotFoundException(username + "-找到了多个用户,错误");
        }
        ScUser user = userList.get(0);
        SecurityUser retUser = new SecurityUser();
        BeanUtils.copyProperties(user, retUser);

        //查询用户下的角色
        List<ScRole> scRoles = roleMapper.selectRoleByUserId(user.getId());
        retUser.setRoleList(scRoles);
        return retUser;
    }
}
