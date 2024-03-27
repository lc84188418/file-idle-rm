package com.cvmaster.fileidlerm.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author cvmaster
 * @version v.1.0.0
 * @desc (User)表实体类
 * @create 2023-06-12 23:09:32
 */
@Data
@TableName("user")
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    /**
     * 头像
     */
    private String img;
    /**
     * 头像
     */
    private String img2;
}
