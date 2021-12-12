package com.hk.netty.rpc.consumer.client;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hk.netty.rpc.consumer.handler.RpcClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.*;

/**
 * @author : HK意境
 * @ClassName : RpcClient
 * @date : 2021/12/12 18:20
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class RpcClient {

    private static ExecutorService executor;
    private static RpcClientHandler handler ;


    // 使用代理模式，获取代理对象
    public Object getBean(final Class<?> serviceClass, final String prefix){

        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class<?>[]{serviceClass}, ((proxy, method, args) -> {

            if (handler == null) {
                initClient();
            }

            handler.setParam(prefix+args[0]);

            return executor.submit(handler).get();
        }));

    }



    static {

        //获取系统处理器个数，作为线程池数量
        int nThreads = Runtime.getRuntime().availableProcessors();
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
// 创建线程池
        executor = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    }


    private static void initClient() throws InterruptedException {

        handler = new RpcClientHandler();

        // 创建 EventLoopGroup
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    // TCP  不延时
                    .option(ChannelOption.TCP_NODELAY ,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline
                                    .addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(handler);

                        }
                    });


            ChannelFuture future = bootstrap.connect("127.0..0.1", 8888).sync();

            future.channel().closeFuture();


        }finally {
            workerGroup.shutdownGracefully();
        }


    }








}
