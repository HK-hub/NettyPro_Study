package com.hk.design.singleton;

/**
 * @author : HK意境
 * @ClassName : HungryTypeStaticCodeArea
 * @date : 2022/1/19 22:04
 * @description : 单例模式————静态代码块方式
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class HungryTypeStaticCodeArea {


    // 1. 构造器私有化：防止外部 new 对象
    private HungryTypeStaticCodeArea(){

    }



    private final static HungryTypeStaticCodeArea instance;

    // 2. 在本类内部使用静态代码块在类初始化阶段在进行创建对象
    static {
        instance = new HungryTypeStaticCodeArea();
    }


    // 3. 对外暴露一个公共的静态实例获取方法
    public static HungryTypeStaticCodeArea getInstance(){

        return HungryTypeStaticCodeArea.instance ;
    }


    public static void main(String[] args) {

        // 测试
        HungryTypeStaticCodeArea instance = HungryTypeStaticCodeArea.getInstance();
        HungryTypeStaticCodeArea instance1 = HungryTypeStaticCodeArea.getInstance();


        // 判断地址是否相等
        System.out.println(instance == instance1);
        System.out.println(instance.hashCode() + " —————— " + instance1.hashCode());

    }



}
