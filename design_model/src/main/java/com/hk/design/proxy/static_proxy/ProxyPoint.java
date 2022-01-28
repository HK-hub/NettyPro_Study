package com.hk.design.proxy.static_proxy;

import com.hk.design.proxy.dynamic.jdk.SellTickets;
import com.hk.design.proxy.dynamic.jdk.TrancePoint;

/**
 * @author : HK意境
 * @ClassName : ProxyPoint
 * @date : 2022/1/22 14:58
 * @description : 代理贩卖点
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ProxyPoint implements SellTickets {

    private TrancePoint trancePoint = new TrancePoint("重庆");

    @Override
    public void sell() {

        System.out.println("代理点收取中介费用...");
        trancePoint.sell();

    }
}
