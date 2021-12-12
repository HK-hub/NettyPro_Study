package com.hk.netty.hearbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author : HK意境
 * @ClassName : NettyHearBeatServerHandler
 * @date : 2021/12/10 19:34
 * @description :
 * @Todo : 服务端心跳检测机制处理器
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyHearBeatServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/12/10 19:36
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
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        // 判断event 事件类型 ： idleStateEvent
        if (evt instanceof IdleStateEvent){

            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = "";
            switch (event.state()) {

                // 读空闲
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
                default:
                    eventType = "读写空闲";
                    break;
            }

            // 服务器做相应的处理
            System.out.println(ctx.channel().remoteAddress() + " ---超时事件---" + eventType);

            //关闭通道

        }



    }
}
