package com.hk.netty.protocoltcp;

import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author : HK意境
 * @ClassName : MyServer
 * @date : 2021/12/11 19:45
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MyServer {

    public static void main(String[] args) {

        new MyServer().run();
    }

    private void run() {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();



    }


}
