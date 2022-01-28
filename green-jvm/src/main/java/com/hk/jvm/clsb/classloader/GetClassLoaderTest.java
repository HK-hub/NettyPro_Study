package com.hk.jvm.clsb.classloader;

/**
 * @author : HK意境
 * @ClassName : GetClassLoaderTest
 * @date : 2022/1/19 19:25
 * @description :
 * @Todo : 获取ClassLoader 类加载器的方式
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class GetClassLoaderTest {

    public static void main(String[] args) {

        // 方式一：通过 class 对象 getClassLoader() 方式获取
        ClassLoader classLoader = GetClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);

        // 方式二：通过当前线程的线程上下文类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);


        // 方式三：直接通过ClassLoader类获取到系统类加载器，然后可以通过 getParent() 获取父类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader().getParent().getParent();
        System.out.println(systemClassLoader);


    }


}
