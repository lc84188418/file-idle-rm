package com.cvmaster.fileidlerm.entity;

import com.cvmaster.fileidlerm.enums.RStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuchun
 * @version 1.0.0
 * @Desc 全局JSON封装体
 * @ClassName R.java
 * @createTime 2021年09月26日 14:53:14:53
 */
@Data
@AllArgsConstructor//全参
@NoArgsConstructor//空参
public class R<T> implements Serializable {
    private int code;
    private String status;
    private String msg;
    private T data;
    public R(int code, String status, String msg) {
        this(code,status,msg,null);
    }

    public R ok(String msg,T data) {
        this.code = RStatus.SUCCESS.code();
        this.status = RStatus.SUCCESS.status();
        this.msg = msg;
        this.data = data;
        return this;
    }
    public R ok(T data) {
        this.code = RStatus.SUCCESS.code();
        this.status = RStatus.SUCCESS.status();
        this.msg = RStatus.SUCCESS.desc();
        this.data = data;
        return this;
    }
    public R ok() {
        this.code = RStatus.SUCCESS.code();
        this.status = RStatus.SUCCESS.status();
        this.msg = RStatus.SUCCESS.desc();
        this.data = null;
        return this;
    }

    public R data(String key,Object data) {
        Map<String,Object> mapData = (Map<String, Object>) this.getData();
        if(mapData == null){
            mapData = new HashMap<>();
        }
        mapData.put(key,data);
        this.data = (T) mapData;
        return this;
    }

    public R error(String msg) {
        this.code = RStatus.ERROR.code();
        this.status = RStatus.ERROR.status();
        this.msg = msg;
        this.data = null;
        return this;
    }
    public R error(String msg,T data) {
        this.code = RStatus.ERROR.code();
        this.status = RStatus.ERROR.status();
        this.msg = msg;
        this.data = data;
        return this;
    }
    public R error() {
        this.code = RStatus.ERROR.code();
        this.status = RStatus.ERROR.status();
        this.msg = RStatus.ERROR.desc();
        this.data = null;
        return this;
    }

    public R error(RStatus rStatus) {
        this.code = rStatus.code();
        this.status = rStatus.status();
        this.msg = rStatus.desc();
        this.data = null;
        return this;
    }

    /**
     * 正确返回
     * @return
     */
    public R insertOk() {
        this.code = RStatus.SUCCESS.code();
        this.status = RStatus.SUCCESS.status();
        this.msg = "添加成功！";
        return this;
    }
    public R updateOk() {
        this.code = RStatus.SUCCESS.code();
        this.status = RStatus.SUCCESS.status();
        this.msg = "保存成功！";
        return this;
    }
    public R deleteOk() {
        this.code = RStatus.SUCCESS.code();
        this.status = RStatus.SUCCESS.status();
        this.msg = "删除成功！";
        return this;
    }
    /**
     * 错误返回
     * @return
     */
    public R insertError() {
        this.code = RStatus.ERROR.code();
        this.status = RStatus.ERROR.status();
        this.msg = "添加失败！";
        return this;
    }
    public R updateError() {
        this.code = RStatus.ERROR.code();
        this.status = RStatus.ERROR.status();
        this.msg = "保存失败！";
        return this;
    }
    public R deleteError() {
        this.code = RStatus.ERROR.code();
        this.status = RStatus.ERROR.status();
        this.msg = "删除失败！";
        return this;
    }
}
