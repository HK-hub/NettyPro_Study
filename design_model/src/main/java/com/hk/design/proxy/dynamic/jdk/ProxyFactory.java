package com.hk.design.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : HK意境
 * @ClassName : ProxyFactory
 * @date : 2022/1/22 14:55
 * @description : 用来获取代理对象的工厂类
 *                  代理类也实现了的对应的接口
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ProxyFactory {

    // 声明目标对象
    private TrancePoint trancePoint = new TrancePoint("重庆");


    // 获取代理类对象
    public SellTickets getProxyObject(){
        // 返回代理对象


        /* 参数解释：
        *       ClassLoader loader : 代理类对象所需要的类加载器。可以通过目标对象获取类加载器
        *       Class<?> interfaces : 代理类实现的接口的字节码对象：
        *       InvocationHandler handler : 代理对象的调用处理程序
        *
        *
        * */
        SellTickets proxyObject = (SellTickets) Proxy.newProxyInstance(
                this.trancePoint.getClass().getClassLoader(), // 获取代理类所需要的类加载器
                this.trancePoint.getClass().getInterfaces(),

                // 匿名内部类
                new InvocationHandler() {

                    /**
                     * 参数说明：
                     * @param proxy : 代理对象， 和 proxyObject 是同一个对象，在invoke 中基本不用
                     * @param method : 对代理对象实现接口中的需要进行代理的方法进行封装后的 Method 对象
                     * @param args : 真实调用方法的实际参数
                     * @return Object : 真实调用方法的返回值
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("代理对象执行了");
                        System.out.println("JDK 代售点收取一定的费用...");
                        // 执行目标对象的方法
                        Object invoke = method.invoke(trancePoint, args);

                        return null ;

                    }
                }

        );

        return proxyObject ;

    }


}
