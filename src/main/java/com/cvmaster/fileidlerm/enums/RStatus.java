package com.cvmaster.fileidlerm.enums;

/**
 * @author liuchun
 * @className RStatus.java
 * @desc TODO
 * @date 2021:10:09 22:30
 * @since version 1.0.0
 */
public enum RStatus {
    SUCCESS(200,"success","操作成功"),
    ERROR(444,"error","操作失败"),

    AUTH_USERNAME_NOT_EXIST(15001,"error","账号/手机号/邮箱不存在"),
    AUTH_PASSWORD_ERROR(15002,"error","密码错误"),

    SUCCESS_LOGIN(200001,"success","登录成功"),
    SUCCESS_LOGOUT(200002,"success","退出登录成功"),

    SYSTEM_ADMINISTRATOR_NOT_OPERATE(1001,"error","超级管理员不可操作"),

    SYSTEM_1002(1002,"error","当前用户未分配角色，无法登录"),
    SYSTEM_1003(1003,"error","当前用户所拥有角色均已过期"),

    //其他操作代码
    PARAM_EMPTY(4001,"error","参数为空"),
    SPECIAL_CHARACTERS(4002,"error","包含特殊字符"),
    SYNC_OPERATE(4010,"error","该数据已被其他用户操作，请刷新后重试!"),

    ;
    private Integer code;
    private String status;
    private String desc;
    RStatus(Integer code, String status, String desc){
        this.code = code;
        this.status = status;
        this.desc = desc;
    }
    public Integer code() {
        return code;
    }
    public String status() {
        return status;
    }
    public String desc() {
        return desc;
    }
}
