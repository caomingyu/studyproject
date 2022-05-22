package com.cmy;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author cao.mingyu
 * 验证Date和LocalDate
 */
public class TestDate {
    public static void main(String[] args) throws FileNotFoundException {
        //控制将System中.in，out，err重定向
//        OutputStream outputStream = new FileOutputStream("D:\\project\\studyproject\\simpleproject\\sysout.txt");
//        PrintStream printStream = new PrintStream(outputStream);
//        System.setOut(printStream);
        //普通Date使用
        Date date = new Date();
        System.out.println("当前时刻:" + date);
        System.out.println("当前年份:" + date.getYear());
        System.out.println("当前月份:" + date.getMonth());

        //使用LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("当前时刻:" + dateTime);
        System.out.println("当前年份:" + dateTime.getYear());
        System.out.println("当前月份:" + dateTime.getMonthValue());
        //构造指定时间
        LocalDateTime specialDate = LocalDateTime.of(2022, Month.FEBRUARY, 1, 12, 1, 1);
        System.out.println("构造的时刻:" + specialDate);
        //修改日期
        //减少一天
        LocalDateTime minusDate = specialDate.minusDays(1);
        System.out.println("修改后的时刻:" + minusDate);

        //增加一天
        LocalDateTime plusDate = specialDate.plusDays(1);
        System.out.println("修改后的时刻:" + plusDate);

        //格式化日期
        String format = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("格式化后,当前时刻:" + format);
        //反解析时刻
        String dateStr = "2021-12-10 06:12:01";
        LocalDateTime parse = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("反解析的时刻:" + parse);

        //对接Mybatis时,直接在实体中定义LocalDateTime即可,Mysql中对应字段的类型为datetime。Mybatis会自动为我们做好映射

        System.out.println(File.separator);

        System.out.println(isNumber("-1"));
    }

    private static boolean isNumber(String str){
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
