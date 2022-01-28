package com.hk.design.inversion;

/**
 * @author : HK意境
 * @ClassName : DependencyInversion
 * @date : 2022/1/18 13:32
 * @description : 依赖倒置原则
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class DependencyInversion {


    public static void main(String[] args) {

        new Person().recive(new Email());


    }



}


// 电子邮件
class Email{

    public String info(String msg){

        return "电子邮件信息：" + msg ;
    }

}

/**
 * 方式一：
 *
 *
 *
 *
 *
 */



// 完成 Persion 接收消息的功能
class Person{

    public void recive(Email email){

        System.out.println("接收消息：" + email.info("你好"));

    }



}