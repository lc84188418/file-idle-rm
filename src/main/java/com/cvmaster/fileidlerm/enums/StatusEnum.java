package com.cvmaster.fileidlerm.enums;

/**
 * @author liuchun
 *
 * @className RStatus.java
 * @desc 相反的两个状态枚举类
 * 1代表正确的、对的、正常的、
 * 0代表错误的，错的，不正常的
 * @date 2021:10:09 22:30
 * @since version 1.0.0
 */
public enum StatusEnum {
    ok(1,"Y","正确"),
    error(0,"N","错误");
    private Integer code;
    private String status;
    private String desc;
    StatusEnum(Integer code, String status, String desc){
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
