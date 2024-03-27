package com.cvmaster.fileidlerm.entity;

import com.alibaba.fastjson.JSONObject;
import com.cvmaster.fileidlerm.enums.RStatus;

import java.util.HashMap;

/**
 * @author liuchun
 * @version 1.0.0
 * @Desc 全局JSON封装体,可指定返回结果的泛型
 * @ClassName CommonR.java
 * @createTime 2021年09月26日 14:53:14:53
 */
public class CommonR<T> extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;
    private static final ThreadLocal<JSONObject> threadLocal = new ThreadLocal<>();
    private JSONObject jsonObject;

    public CommonR() {
        jsonObject = new JSONObject();
//        threadLocal.set(new JSONObject());
    }
    public CommonR msg(String message) {
        this.put("msg", message);
        return this;
    }

    public CommonR code(Integer code) {
        this.put("code", code);
        return this;
    }

    public CommonR status(String status) {
        this.put("status", status);
        return this;
    }

    public CommonR data(T data) {
        this.put("data", data);
        return this;
    }
    public CommonR data(String key,T data) {
        jsonObject.put(key,data);
        this.put("data", jsonObject);
        return this;
    }
    @Override
    public CommonR put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public CommonR ok(String msg,T data) {
        this.put("code", RStatus.SUCCESS.code());
        this.put("status", RStatus.SUCCESS.status());
        this.put("msg", msg);
        this.put("data", data);
        return this;
    }
    public CommonR ok() {
        this.put("code", RStatus.SUCCESS.code());
        this.put("status", RStatus.SUCCESS.status());
        this.put("msg", RStatus.SUCCESS.desc());
        this.put("data", null);
        return this;
    }
    public CommonR error(String msg) {
        this.put("code", RStatus.ERROR.code());
        this.put("status", RStatus.ERROR.status());
        this.put("msg", msg);
        return this;
    }
}
