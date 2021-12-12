package com.hk.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author : HK意境
 * @ClassName : MyClientHandler
 * @date : 2021/12/11 19:29
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count ;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        // 获取数据
        byte[] content = msg.getContent();
        int len = msg.getLen();

        System.out.println("客户端接收消息("+(++count) + ")：");
        System.out.println("len= " + len +" \t content=" + content);

    }

    // 客户端通道处理器就绪
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 使用客户端发送10 条数据 天气冷 ，吃火锅 编号
        for (int i = 0; i < 100; i++) {
            String msg = "今天天气冷, 吃火锅";

            byte[] content = msg.getBytes(StandardCharsets.UTF_8);
            int length = content.length;

            // 创建协议包对象
            MessageProtocol messageProtocol = new MessageProtocol(length, content);

            // 发送协议包
            ctx.writeAndFlush(messageProtocol);

        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 关闭通道处理器
        ctx.close();

    }
}
