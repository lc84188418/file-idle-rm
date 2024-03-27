package com.cvmaster.fileidlerm.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wenchao-liuchun
 * @version 1.0.0
 * @Desc 实体时间类型的字段自动填充
 * @ClassName MyMetaObjectHandler.java
 * @createTime 2021年09月27日 09:30:9:30
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 添加方法时，时间的取值
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);
    }

    /**
     * 修改方法时，时间的取值
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        //修改时间
        this.setFieldValByName("updateTime", date, metaObject);
        //删除时间
        this.setFieldValByName("deleteTime", date, metaObject);
    }
}
