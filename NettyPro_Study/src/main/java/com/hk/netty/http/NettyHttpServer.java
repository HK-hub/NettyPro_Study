package com.hk.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author : HK意境
 * @ClassName : NettyHttpServer
 * @date : 2021/12/9 21:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyHttpServer {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{

            // 服务端启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置启动器线程工作组
            serverBootstrap.group(bossGroup,workerGroup)
                    // 指定服务端通道
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer());


            // 开启服务端监听，绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();

            // 监听channelFuture 通道关闭事件
            channelFuture.channel().closeFuture().sync();

        }finally {

            // 优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
