package com.example.springsecuritydemo.common.constans;

/**
 * 状态枚举
 */
public enum StatusEnum {
    /**
     * 启用
     */
    NORMAL("1", "启用"),

    /**
     * 停用
     */
    STOP("0", "停用");

    /**
     * code值
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    private StatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
