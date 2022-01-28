package com.hk.jvm.clsb.runtime_data_area.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HK意境
 * @ClassName : OperandStackTest
 * @date : 2022/1/23 18:46
 * @description : 操作数栈测试
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class OperandStackTest {


    public OperandStackTest(String string){
        ;
    }



    public void testStack(){

        byte f = 120 ;
        short i = 52 ;
        int j = 1 ;
        int k = 9 ;
        int s = j + k ;

        String str = "abcs" ;

    }


    public static void main(String[] args) throws InterruptedException {

        var name = "hello world" ;

        // 动态类型语言

        var list = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 10, 65, 98, 14, 87));

        for(var item : list) {

            System.out.println(item);

        }
        Thread.sleep(1000 * 1000);

    }

}



abstract class AbsClass{

    String name ;

}