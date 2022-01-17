package com.example.springsecuritydemo.model;

import lombok.Data;

@Data
public class ScUser {
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 0:男 1:女
     */
    private String sex;

    /**
     * 是否启用 1:启用 0:禁用
     */
    private String status;
}