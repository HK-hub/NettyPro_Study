package com.hk.netty.ioboundhandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author : HK意境
 * @ClassName : CustomCodecClient
 * @date : 2021/12/11 14:48
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CustomCodecClient {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();

        try{

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            // 定义通道类型
            bootstrap.channel(NioSocketChannel.class)
                    // 设置通道处理器 自定义一个初始化类
                    .handler(new CustomCodecClientInitializer());


            // 连接服务器
            ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
            future.channel().closeFuture().sync();

        }finally {

            group.shutdownGracefully();
        }

    }

}
