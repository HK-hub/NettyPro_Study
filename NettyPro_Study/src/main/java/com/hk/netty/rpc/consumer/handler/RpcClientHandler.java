package com.hk.netty.rpc.consumer.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Data;

import java.util.concurrent.Callable;

/**
 * @author : HK意境
 * @ClassName : RpcClientHandler
 * @date : 2021/12/12 16:41
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class RpcClientHandler extends ChannelInboundHandlerAdapter
                                implements Callable {

    private ChannelHandlerContext context ;

    // 服务端相应结果
    private String res ;
    // 调用服务端参数
    private String param ;


    // 与服务器的连接创建后，就会被自动调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 因为我们在其他方法也会使用到 ctx
        context = ctx ;

    }



    // 收到服务器的数据后，就会调用方法
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        res = (String) msg;

        // 唤醒等待的线程
        notify();

    }

    @Override
    public Object call() throws Exception {

        // 被代理对象调用，发送数据给服务器，等待被唤醒 ——> 返回结果
        context.writeAndFlush(param);
        // 进行wait ; 等到 channelRead() 方法获取到消息后，进行唤醒
        wait();
        return res ;

    }


    @Override
    public synchronized void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();

    }

}
