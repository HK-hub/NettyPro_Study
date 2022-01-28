package com.hk.design.proxy.dynamic.cglib;

/**
 * @author : HK意境
 * @ClassName : Client
 * @date : 2022/1/22 18:15
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class Client {

    public static void main(String[] args) throws Exception {

        ProxyFactory<TrainStation> proxyFactory = new ProxyFactory<>(TrainStation.class);

        TrainStation proxyObject = proxyFactory.getProxyObject(TrainStation.class);

        proxyObject.sell();

    }




}
