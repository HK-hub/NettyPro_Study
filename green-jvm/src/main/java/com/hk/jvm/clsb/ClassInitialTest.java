package com.hk.jvm.clsb;

/**
 * @author : HK意境
 * @ClassName : ClassInitialTest
 * @date : 2022/1/19 14:48
 * @description : 类加载三阶段中的初始化阶段
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ClassInitialTest {

    public int aaa = 1 ;
    private int bbb  ;

    private String ccc = "ccccc" ;

    private static String  d = "d" ;

    private static int fff = 3333 ;

    private final static int EEE = 123 ;


    public static void main(String[] args) {


        System.out.println(ClassInitialTest.EEE);


    }



}
