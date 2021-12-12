package com.hk.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import java.util.Date;

/**
 * @author : HK意境
 * @ClassName : GroupChatServer
 * @date : 2021/12/10 13:38
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class GroupChatServer {

    // 监听端口
    private int port ;

    public GroupChatServer(int port){
        this.port = port ;
    }


    /**
     * @methodName : 编写rua 方法，处理客户端请求
     * @author : HK意境
     * @date : 2021/12/10 13:40
     * @description :
     * @Todo : 编写rua 方法，处理客户端请求
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public void run() throws InterruptedException {

        // 创建线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 核心数 * 2 = 16 个NioEventLoop
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            // 服务器端启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 配置启动器属性
            serverBootstrap
                    // 设置组
                    .group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            // 获取 pipeline
                            ch.pipeline()
                                    // 向pipeline中添加解码器
                                    .addLast("decoder", new StringDecoder())
                                    // 添加编码器
                                    .addLast("encoder",new StringEncoder())
                                    // 添加 handler
                                    .addLast(new GroupChatServerHandler());

                        }
                    });
            System.out.println("=========================================================");
            System.out.println("[ " + new Date() + " ]" + "Group Chat Server is starting....");
            // 启动服务器
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("[ " + new Date() + " ]" + "Group Chat Server is started....");
            System.out.println("=========================================================");

            // 监听关闭事件
            future.channel().closeFuture().sync();


        }finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws InterruptedException {


        new GroupChatServer(8888).run();

    }

}





