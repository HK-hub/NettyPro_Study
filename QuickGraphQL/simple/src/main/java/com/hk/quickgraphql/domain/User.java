package com.hk.quickgraphql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;


import java.time.LocalDateTime;


@Data
@TableName(value = "user")
public class User  {

    @TableId(type = IdType.ASSIGN_ID)
    private String id ;

    private Integer age ;

    private String username ;

    private String password ;

    private LocalDateTime createTime ;

    private LocalDateTime updateTime ;

    private Boolean deleted ;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }
}