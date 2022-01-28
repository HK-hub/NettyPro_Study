package com.hk.vfs.constant;

import com.hk.vfs.model.HFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author : HK意境
 * @ClassName : PWD
 * @date : 2021/12/22 14:57
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class PWD {

    static {
        // 初始化

    }

    // 当前工作目录
    public static String currentPath = "D:\\C++Code";

    // 浏览记录
    public static Stack<String> browserStack = new Stack<>();

    // hash 散列实现文件索引存放 : Key为路径，value 为当前路径下的文件或文件夹集合
    public static HashMap<String, ArrayList<HFile>> inode = new HashMap<>();


    // 初始化常量，初始值等
    public void init(){

        browserStack.push(currentPath) ;

    }



}
