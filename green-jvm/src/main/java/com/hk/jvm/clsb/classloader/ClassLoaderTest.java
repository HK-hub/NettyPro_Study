package com.hk.jvm.clsb.classloader;

/**
 * @author : HK意境
 * @ClassName : ClassLoaderTest
 * @date : 2022/1/19 16:23
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        //jdk.internal.loader.ClassLoaders$AppClassLoader@2437c6dc
        System.out.println(systemClassLoader);


        //获取其上层：扩展类加载器
        ClassLoader extendClassLoader = systemClassLoader.getParent();
        //jdk.internal.loader.ClassLoaders$PlatformClassLoader@4c203ea1
        System.out.println(extendClassLoader);

        //试图获取其上层：引导类加载器,获取不到
        ClassLoader bootstrapClassLoader = extendClassLoader.getParent();
        // null
        System.out.println(bootstrapClassLoader);


        // 对于用户自定义类来说：默认使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //jdk.internal.loader.ClassLoaders$AppClassLoader@2437c6dc
        System.out.println(classLoader);


        // 查看 String 类的类加载器: 使用 引导类加载器
        // Java 的核心类库都是使用引导类加载器进行加载器的
        ClassLoader stringClassLoader = String.class.getClassLoader();
        //null
        System.out.println(stringClassLoader);

    }


}
