package com.hk.design.proxy.dynamic.jdk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : HK意境
 * @ClassName : TrancePoint
 * @date : 2022/1/22 14:52
 * @description : 火车站类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrancePoint implements SellTickets{

    private String name ;

    @Override
    public void sell() {

        System.out.println( name + "正在卖票...");

    }
}
