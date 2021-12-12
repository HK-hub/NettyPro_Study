package com.hk.netty.test;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;
import org.junit.Test;

/**
 * @author : HK意境
 * @ClassName : CpuCoreNumTest
 * @date : 2021/12/9 19:56
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CpuCoreNumTest {


    @Test
    public void num(){

        System.out.println(NettyRuntime.availableProcessors());

    }


}
