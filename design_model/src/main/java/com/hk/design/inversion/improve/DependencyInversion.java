package com.hk.design.inversion.improve;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : HK意境
 * @ClassName : DependencyInversion
 * @date : 2022/1/18 13:39
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class DependencyInversion {

    public static void main(String[] args) {

        Wechat wechat = new Wechat("hello wechat");
        Email email = new Email("hello email");
        QQ qq = new QQ("hello qq");

        Person person = new Person();
        person.receiveMessage(wechat);
        person.receiveMessage(email);
        person.receiveMessage(qq);


    }


}



// 定义接口
interface IReceiver{


    public String getInfo();


}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Email implements IReceiver {

    public String msg = "";

    @Override
    public String getInfo() {
        return "电子邮件：" + msg;
    }

}


@NoArgsConstructor
@AllArgsConstructor
class Wechat implements IReceiver {

    public String msg = "";

    @Override
    public String getInfo() {

        return "微信：" + msg ;
    }
}

@NoArgsConstructor
@AllArgsConstructor
class QQ implements IReceiver{
    public String msg = "";


    @Override
    public String getInfo() {

        return "QQ: " + msg;
    }
}



// Person
class Person{

    public void receiveMessage(IReceiver iReceiver){

        System.out.println(iReceiver.getInfo());
    }


}



