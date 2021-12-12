package com.hk.netty.simple.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author : HK意境
 * @ClassName : NettyServer
 * @date : 2021/12/9 16:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 创建 BossGroup 和 WorkerGroup
         *  说明：
         *      boosgroup 只负责处理连接请求，真正的客户端业务请求处理，交给 workergroup 完成
         *      两个都是无限循环
         *  boosGroup 和 workreGroup 含有的子线程数（NioEventHandler）的个数默认为CPU处理器核心数 * 2
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 设置参数，使用链式编程来进行配置
            serverBootstrap
                    // 设置两个线程组
                    .group(bossGroup, workerGroup)
                    // 使用 NioServerSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列等待连接的个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活跃连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    // 给 workergroup 的 EventLoop 对应的管道设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 给 pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println(".....服务器已经准备好了!.....");

            // 指定端口进行绑定 : 绑定一个端口并同步处理
            // 启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(6668).sync();

            // 注册 ChannelFutureListener 监听器，监听感兴趣的事件
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()){
                        System.out.println("监听端口 6668 成功" );
                    }else {
                        System.out.println("监听端口失败：" + future.cause());
                    }
                }
            });


            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync() ;

        }finally{
            bossGroup.shutdownGracefully();
        }

    }


}
