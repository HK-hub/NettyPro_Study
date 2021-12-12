package com.hk.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author : HK意境
 * @ClassName : GroupChatClient
 * @date : 2021/12/10 15:26
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class GroupChatClient {

    private final String host ;
    private final int port ;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException {

        // 客户端 group
        NioEventLoopGroup group = new NioEventLoopGroup();

        try{
            // 客户端启动器
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 得到 pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 加入相关 handler
                            pipeline.addLast("decoder",new StringDecoder())
                                    .addLast("encoder",new StringEncoder())
                                    .addLast(new GroupChatClientHandler());
                        }
                    });


            // 连接服务器端
            ChannelFuture future = bootstrap.connect(host, port).sync();

            // 获取 channel
            Channel channel = future.channel();
            // 监听通道关闭事件
            //future.channel().closeFuture().sync();

            System.out.println("------- " + channel.localAddress() + " ---------" );
            // 客户端持续输入消息
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                // 获取输入消息
                String msg = scanner.nextLine();
                // 通过 channel 发送消息到服务器
                channel.writeAndFlush(msg + "\r\n");
            }


        }finally {
            group.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws InterruptedException {

        new GroupChatClient("127.0.0.1", 7000).run();

    }

}
