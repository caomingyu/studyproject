package com.example.springsecuritydemo.model;

import lombok.Data;

@Data
public class ScRolePermission {
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;

    /**
     * 状态 0:禁用 1:启用
     */
    private String status;
}