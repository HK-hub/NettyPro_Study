package com.hk.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author : HK意境
 * @ClassName : MyServerHandler
 * @date : 2021/12/11 19:58
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count ;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {

        // 进行读取解码, 接收数据
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println("服务端接收到信息如下：");
        System.out.println("\t\tlen= " + len );
        System.out.println("\t\tcontent= " + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务器接收到消息包数量：" + (++count));

        // 回复消息
        String responseContent = UUID.randomUUID().toString();
        int length = responseContent.getBytes(StandardCharsets.UTF_8).length;

        // 构建一个协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(responseContent.getBytes(StandardCharsets.UTF_8));

        ctx.writeAndFlush(messageProtocol);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {



    }
}
