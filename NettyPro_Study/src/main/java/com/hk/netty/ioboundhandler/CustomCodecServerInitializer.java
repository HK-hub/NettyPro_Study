package com.hk.netty.ioboundhandler;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author : HK意境
 * @ClassName : CustomCodecServerInitializer
 * @date : 2021/12/11 14:30
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CustomCodecServerInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {

        // 获取 pipeline
        ChannelPipeline pipeline = ch.pipeline();

        // 入站 handler 进行解码 MyByteToLongDecoder
        pipeline.addLast(new MyByteToLongDecoder());

        // 自定义handler 处理业务逻辑
        pipeline.addLast(new CustomCodecServerHandler());


    }
}
