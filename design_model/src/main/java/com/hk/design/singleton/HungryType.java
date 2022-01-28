package com.hk.design.singleton;

/**
 * @author : HK意境
 * @ClassName : HungryType
 * @date : 2022/1/19 21:55
 * @description : 单列设计及模式饿汉式
 * @Todo : 静态变量
 * @Bug : 没有达到懒加载的效果，可能造成内存浪费
 * @Modified :
 * @Version : 1.0
 */
public class HungryType {

    // 1. 构造器私有化：防止外部 new 对象
    private HungryType(){

    }


    // 2. 在本类内部创建一个对象实例
    private final static HungryType instance = new HungryType() ;


    // 3. 对外暴露一个公共的静态实例获取方法
    public static HungryType getInstance(){

        return HungryType.instance ;
    }


    public static void main(String[] args) {

        // 测试
        HungryType instance = HungryType.getInstance();
        HungryType instance1 = HungryType.getInstance();


        // 判断地址是否相等
        System.out.println(instance == instance1);
        System.out.println(instance.hashCode() + " || " + instance1.hashCode());

    }


}
