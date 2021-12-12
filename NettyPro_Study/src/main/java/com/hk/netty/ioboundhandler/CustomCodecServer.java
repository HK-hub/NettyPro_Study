package com.hk.netty.ioboundhandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author : HK意境
 * @ClassName : CustomCodecServer
 * @date : 2021/12/11 13:45
 * @description :
 * @Todo : 自定义编解码器，实现数据字节流传输
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CustomCodecServer {

    public static void main(String[] args) throws InterruptedException {

        new CustomCodecServer().run();

    }




    private void run() throws InterruptedException {

        // 创建线程工作组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            // 创建服务器端启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 配置启动器
            serverBootstrap.group(bossGroup, workerGroup)
                    // 设置服务器端通道类型，
                    .channel(NioServerSocketChannel.class)
                    // 设置客户端最大连接数量
                    .option(ChannelOption.SO_BACKLOG,128)
                    // 设置保持活跃连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    // 给 workergroup 的 EventLoop 对应的管道设置处理器
                    .childHandler(new CustomCodecServerInitializer());



            // 绑定，连接，启动服务器
            ChannelFuture future = serverBootstrap.bind(8888).sync();

            // 监听 future 管道关闭事件
            future.channel().closeFuture().sync();


        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }


}
