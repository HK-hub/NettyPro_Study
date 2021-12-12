package com.hk.netty.rpc.provider.server;

import com.hk.netty.rpc.provider.service.impl.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author : HK意境
 * @ClassName : RpcServerHandler
 * @date : 2021/12/12 16:24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class RpcServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 获取客户端发送的消息，并发送消息,调用服务
        System.out.println("msg=" + msg);

        // 特殊字符防止粘包拆包
        String message = (String) msg;
        if (message.startsWith("[#rpc-service#]")){

            // 截取数据段
            String data = message.substring(message.lastIndexOf(']') + 1);

            String res = new HelloServiceImpl().sayHello(data);

            // 相应消息
            ctx.writeAndFlush(res);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();

    }



}

