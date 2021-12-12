package com.hk.netty.chat;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : HK意境
 * @ClassName : GroupChatServerHandler
 * @date : 2021/12/10 14:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义一个Channel 组，管理所有的Channel
    // GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单列
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    /**
     * 实现点对点的聊天：
     *     使用 ConcurrentHashMap
     *
     *
     */
    private static ConcurrentHashMap channelMap = new ConcurrentHashMap<String,Channel>();



    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/12/10 14:36
     * @description : 标识连接建立，一旦连接，第一个被执行
     * @Todo : 将当前 channel 加入到 channelGroup
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

        // 获取客户端 channel
        Channel channel = ctx.channel();

        // 将该客户加入聊天的信息推送给其他在线的用户客户端
        /**
         * 该方法会将 channelGroup 中所有的 channel 遍历，并发送消息；不需要我们自己进行遍历
         */
        channelGroup.writeAndFlush("[client: -- " + channel.remoteAddress() + " --> inner chat room]:"+format.format(new Date()).toString() +"\n");
        channelGroup.add(channel);

    }



    /**
     * @methodName : channel 处理器移除，断开连接
     * @author : HK意境
     * @date : 2021/12/10 14:50
     * @description :
     * @Todo : 断开连接，将XX客户离开信息推送给所有在线用户
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        String address = ctx.channel().remoteAddress().toString();
        channelGroup.writeAndFlush("[client: -- " + address + " --> leave chat room]:"+format.format(new Date()).toString() +"\n");
    }




    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/12/10 14:47
     * @description : 标识 channel 处于活动状态
     * @Todo : 提示上线
     * @params :
     * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String address = ctx.channel().remoteAddress().toString();
        System.out.println("[ " + address  + " ] 上线了~");

    }



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        // 获取当前 channel
        Channel channel = ctx.channel();

        // 遍历channelGroup 根据不同情况会送不同消息
        channelGroup.forEach(ch -> {
            if (channel != ch){
                // 不是服务器端 channel
                ch.writeAndFlush("[客户]"+ channel.remoteAddress() + " 发送了消息：" +msg + "\n");
            }else{
                ch.writeAndFlush("[自己(服务器)]发送了消息： " + msg + "\n") ;
            }
        });

    }



    /**
     * @methodName : 当 channel 处于非活动状态时触发
     * @author : HK意境
     * @date : 2021/12/10 14:49
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
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        String address = ctx.channel().remoteAddress().toString();
        System.out.println("[ " + address  + " ] 离线了~");

    }



    /**
     * @methodName : 发生异常
     * @author : HK意境
     * @date : 2021/12/10 15:02
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
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 关闭通道
        ctx.close() ;

    }
}
