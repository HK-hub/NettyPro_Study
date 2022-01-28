package com.hk.vfs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : HK意境
 * @ClassName : User
 * @date : 2021/12/29 9:34
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // 用户名
    private String username;
    private String password ;

    // 777
    private int pe ;


    // 角色
    private int role ;

    public User(String username, String password) {


    }


}
