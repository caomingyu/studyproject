package com.example.springsecuritydemo.model;

import lombok.Data;

@Data
public class ScPermission {
    private Integer id;

    /**
     * 权限名
     */
    private Integer name;

    /**
     * 描述
     */
    private Integer description;

    /**
     * 状态 0:禁用 1:启用
     */
    private String status;
}