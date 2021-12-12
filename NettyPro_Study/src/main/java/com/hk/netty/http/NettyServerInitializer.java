package com.hk.netty.http;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author : HK意境
 * @ClassName : NettyServerInitializer
 * @date : 2021/12/9 21:56
 * @description :
 * @Todo : 继承 ServerSocketChannel
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {



    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/12/9 22:22
     * @description :
     * @Todo : 初始化向管道中加入 channel
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        // 向管道中加入编解码处理器


        // 获取管道
        ChannelPipeline pipeline = ch.pipeline();

        // 加入 netty 提供的 HttpServerCodec ： coder 编码器， decoder 解码器的缩写
        // netty 提供的处理 HTTP 的编解码器
        pipeline.addLast("customHttpServerCodec", new HttpServerCodec());

        // 增加自定义的处理器
        pipeline.addLast("CustomHandler",new NettyHttpServerHandler());


    }
}
