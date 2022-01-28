package com.hk.design.proxy.dynamic.cglib;

import lombok.NoArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * @author : HK意境
 * @ClassName : ProxyFactory
 * @date : 2022/1/22 17:49
 * @description : 代理对象工厂，用来获取代理对象
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@NoArgsConstructor
public class ProxyFactory<T> implements MethodInterceptor {

    private Object proxyObject = null ;

    public ProxyFactory(Class<T> clazz) throws Exception{

       proxyObject = clazz.getConstructor().newInstance();
    }


    public ProxyFactory setProxyClass(Class<T> clazz) throws Exception {

        if(proxyObject == null){

            this.proxyObject = clazz.getDeclaredConstructor().newInstance();
        }

        return this ;
    }


    public T getProxyObject(Class<T> clazz) throws Exception {

        setProxyClass(clazz) ;

        // 获取 Enhancer 对象，类似于 Proxy 对象
        Enhancer enhancer = new Enhancer();

        // 注意这里我们获取到的代理对象是目标类的子类
        // 设置父类为我们的目标类
        enhancer.setSuperclass(clazz);

        // 注意这里的回调函数需要一个类参数，这个类参数就是我们增强后的方法所属的类
        // 设置回调函数。这里我们将增强后的方法放在 ProxyFactory 本类里面，因此需要继承方法增强的接口MethodInterceptor，重写增强方法
        enhancer.setCallback(this);

        // 创建代理对象
        T obj = (T) enhancer.create();

        return obj ;
    }


    // 这个方法就是增强后的方法，代理对象执行的方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + "方法增强");

        // 执行方法
        Object invoke = method.invoke(this.proxyObject, objects);
        return invoke ;
    }
}
