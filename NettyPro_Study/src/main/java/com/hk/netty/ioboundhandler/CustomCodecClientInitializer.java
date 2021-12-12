package com.hk.netty.ioboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author : HK意境
 * @ClassName : CustomCodecClientInitializer
 * @date : 2021/12/11 14:49
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CustomCodecClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 出战，加入出战handler 对数据进行编码：
        pipeline.addLast(new MyLongToByteEncoder());

        // 加入一个自定义的handler ，处理业务
        pipeline.addLast(new MyClientHandler());

    }
}
