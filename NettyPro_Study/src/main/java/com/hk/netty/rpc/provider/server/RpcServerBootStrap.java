package com.hk.netty.rpc.provider.server;

/**
 * @author : HK意境
 * @ClassName : RpcServerBootStrap
 * @date : 2021/12/12 16:17
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class RpcServerBootStrap {

    public static void main(String[] args) throws InterruptedException {

        RpcServer.startServer("127.0.0.1",8888);

    }



}
