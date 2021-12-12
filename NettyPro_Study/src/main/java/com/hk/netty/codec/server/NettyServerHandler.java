package com.hk.netty.codec.server;

import com.hk.netty.codec.protoc.StudentPOJO;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author : HK意境
 * @ClassName : NettyServerHandler
 * @date : 2021/12/9 16:57
 * @description :
 * @Todo : 自定义 handler 需要继承 netty 规定好的某个HandlerAdapter 处理器适配器
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * @methodName : 读取数据
     * @author : HK意境
     * @date : 2021/12/9 17:56
     * @description :
     * @Todo :
     * @params :
         * @param : ChannelHandlerContext 上下文对象，含有管道pipeline， 通道channel， 地址
         * @param : Object msg : 就是客户端发送的数据默认Object
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 读取从客户端读取的StudentPojo.Student 类型
        StudentPOJO.Student student = (StudentPOJO.Student) msg ;

        // 取数据，处理数据
        int id = student.getId();
        String name = student.getName();
        System.out.println("客户端发送的数据： "+ id + " , " + name);


    }



    /**
     * @methodName : 读取数据完毕发送响应消息
     * @author : HK意境
     * @date : 2021/12/9 18:31
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
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        // writeFlush = write + flush 将数据写入缓冲区并且刷新
        // 一般我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, 喵喵喵0", CharsetUtil.UTF_8));


    }



    /**
     * @methodName : 处理异常
     * @author : HK意境
     * @date : 2021/12/9 18:37
     * @description :
     * @Todo : 发生异常一般是需要关闭通道
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

        ctx.close() ;

    }
}
