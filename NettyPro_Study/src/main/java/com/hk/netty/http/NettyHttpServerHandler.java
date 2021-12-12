package com.hk.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author : HK意境
 * @ClassName : NettyHttpServerHandler
 * @date : 2021/12/9 21:54
 * @description :
 * @Todo : SimpleChannelInboundHandler 是 ChannelInboundHandler 的子类
 *          HttpObject 标示客户端和服务器端相互通信的数据被封装成那种数据
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * @methodName : 当有读取事件时出发该函数
     * @author : HK意境
     * @date : 2021/12/9 22:28
     * @description :
     * @Todo : 读取客户端数据
     * @params : 
         * @param : null
     * @return : null
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        // 判断 msg 是不是 HttpRequest 请求
        if (msg instanceof HttpRequest) {

            // 获取客户端地址
            System.out.println("msg 类型： " + msg.getClass());
            System.out.println("客户端地址： " + ctx.channel().remoteAddress());
            System.out.println("pipeline： " + ctx.channel().pipeline().hashCode());


            /***
             *
             * 请求资源的过滤
             *
             *
             *
             */


            // 获取请求 uri
            HttpRequest request = (HttpRequest) msg ;
            String uri = request.uri().toString();
            if ("/favicon.ico".equals(uri)){
                System.out.println("请求了 favicon.ico, 不做响应");
                return ;

            }


            // 回复信息给客户端（浏览器）需要使用 HTTP 协议
            ByteBuf buf = Unpooled.copiedBuffer("Hello 我是服务器~~~ ", CharsetUtil.UTF_16);

            // 构造一个 Http 的响应
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
            // 设置响应头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=urf-8");

            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());

            // 将构建好的 response 对象放回
            ctx.writeAndFlush(response);

        }


    }
}
