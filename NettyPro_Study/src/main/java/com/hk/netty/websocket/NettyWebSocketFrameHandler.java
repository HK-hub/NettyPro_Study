package com.hk.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @author : HK意境
 * @ClassName : NettyWebSocketFrameHandler
 * @date : 2021/12/10 21:18
 * @description :  <p>TextWebSocketFrame</p> 表示通道交互的数据类型，这里是一个文本帧(frame)
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println("服务器收到消息：" + msg.text());

        ctx.writeAndFlush(new TextWebSocketFrame("服务器时间" + LocalDateTime.now() + msg.text()));

    }




    /**
     * @methodName : 外部 handler 连接后触发方法
     * @author : HK意境
     * @date : 2021/12/10 21:36
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        // id 表示唯一值，LongText 是唯一的值，ShortText 不是唯一
        System.out.println("handlerAdded 被调用" + ctx.channel().id().asLongText());


    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        System.out.println("HandlerRemove 被调用" + ctx.channel().id().asLongText());
    }




    /**
     * @methodName : 出现异常
     * @author : HK意境
     * @date : 2021/12/10 21:38
     * @description :
     * @Todo : 一旦该通道发生异常，则关闭该通道, 关闭连接
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生：" + cause.getMessage());
        ctx.channel().close();

    }
}


