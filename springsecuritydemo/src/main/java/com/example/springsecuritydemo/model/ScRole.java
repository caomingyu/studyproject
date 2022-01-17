package com.example.springsecuritydemo.model;

import lombok.Data;

@Data
public class ScRole {
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态 0:禁用 1:启用
     */
    private String status;
}