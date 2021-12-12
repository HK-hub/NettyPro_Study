package com.hk.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.SneakyThrows;

/**
 * @author : HK意境
 * @ClassName : NettyWebsocketServer
 * @date : 2021/12/10 19:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyWebsocketServer {


    public static void main(String[] args) {

        new NettyWebsocketServer().run();
    }

    @SneakyThrows
    private void run() {

        // 创建线程工作组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建服务器启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 配置服务器端配置
            serverBootstrap.group(bossGroup, workerGroup);
            // 配置channel
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<NioSocketChannel>(){

                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
                            // 应为基于 http 协议，使用 Http 编码解码器
                            pipeline.addLast(new HttpServerCodec());//.addLast(new HttpClientCodec());
                            // 是以块方式写，添加ChunkedWriterHandler 处理器
                            pipeline.addLast(new ChunkedWriteHandler());

                            /**
                             * Http 数据在传输过程中是分段的，HttpObjectAggregator 就是可以将将多个段聚合
                             *      这就是为什么，当浏览器发送大量数据时，就会发送多次请求
                             *
                             *
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));

                            /**
                             *  对应Websocket ，他的数据是以贞（frame）形式进行传递的
                             *      可以看到websocketFrame 下面有六个子类
                             *  浏览器请求时的格式：ws://localhost:port/uri
                             *  WebSocketServerProtocolHandler 核心功能是将Http 协议升级为 ws 协议，保持长连接
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/"));

                            // 自定义handler ，处理业务逻辑
                            pipeline.addLast(new NettyWebSocketFrameHandler());

                        }
                    });


            // 启动服务器，绑定端口
            ChannelFuture future = serverBootstrap.bind(7000).sync();
            // 监听channel 关闭事件
            future.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }



    }


}
