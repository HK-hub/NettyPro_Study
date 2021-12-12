package com.hk.netty.hearbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author : HK意境
 * @ClassName : NettyHearBeatServer
 * @date : 2021/12/10 16:56
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyHearBeatServer {

    /**
     * @methodName : Netty 的心跳机制
     * @author : HK意境
     * @date : 2021/12/10 16:57
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */


    public static void main(String[] args) throws InterruptedException {

        new NettyHearBeatServer().run();

    }




    private void run() throws InterruptedException {

        // 创建 boss 和 worker 线程组
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{

            // 创建服务器端启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 配置服务端启动器
            serverBootstrap
                    // 设置主从工作线程组
                    .group(boosGroup,workerGroup)
                    // 设置服务器端处理器：
                    .channel(NioServerSocketChannel.class)
                    // 添加日志处理器
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            // 获取 pipeline
                            ChannelPipeline pipeline = ch.pipeline();

                            /**
                             *  加入一个netty 提供 IdleStateHandler 空闲状态处理器
                             *      IdleStateHandler(3,5,7):
                             *          readerIdleTimeSeconds:表示多长时间没有读取数据，就会发送一个心跳检测包，检测是否还是连接状态
                             *          writerIdleTimeSeconds:表示多长时间没有写数据，就会发送心跳检测包，检测是否连接
                             *          addIdleTimeSeconds:表示多长时间既没有读也没有写，就会发送心跳检测包，检测是否连接
                             *
                             *  当 IdleStateEvent 事件触发后，就会传递给管道的下一个 handler 去处理
                             */
                            pipeline.addLast(new IdleStateHandler(10,20,30, TimeUnit.SECONDS));

                            pipeline.addLast(new NettyHearBeatServerHandler()) ;
                        }
                    });


            // 启动服务器
            ChannelFuture future = serverBootstrap.bind(7000).sync();

            // 监控 channel 管道关闭事件
            future.channel().closeFuture().sync();

        }finally {

            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully() ;

        }


    }


}
