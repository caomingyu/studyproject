package com.example.springsecuritydemo.model;

import lombok.Data;

@Data
public class ScUserRole {
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 状态 0:禁用 1:启用
     */
    private String status;
}