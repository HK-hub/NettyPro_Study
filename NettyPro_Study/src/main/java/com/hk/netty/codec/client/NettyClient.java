package com.hk.netty.codec.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author : HK意境
 * @ClassName : NettyClient
 * @date : 2021/12/9 18:43
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        // 客户端只需要一个循环事件组即可
        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        try{
            // 创建客户端启动对象
            Bootstrap bootstrap = new Bootstrap();

            // 设置 bootstrap 相关参数
            bootstrap.group(eventExecutors) // 设置线程组
                    // 设置客户端通道的实现类
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 加入编解码器 ProtoBufEncoder
                            ch.pipeline().addLast("encoder",new ProtobufEncoder());
                            // 加入自己的处理器
                            ch.pipeline().addLast(new NettyClientHandler());

                        }
                    });


            System.out.println("客户端准备就绪： ");

            // 启动客户端去连接服务器
            // sync 不会阻塞
            ChannelFuture future = bootstrap.connect("127.0.0.1", 6668).sync();

            // 给关闭通道进行监听
            future.channel().closeFuture().sync();

        }finally{
            eventExecutors.shutdownGracefully();
        }

    }


}
