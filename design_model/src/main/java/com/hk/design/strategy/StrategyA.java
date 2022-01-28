package com.hk.design.strategy;

/**
 * @author : HK意境
 * @ClassName : Strategy
 * @date : 2022/1/26 19:44
 * @description : 具体策略类，封装算法
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class StrategyA implements Strategy{

    @Override
    public void show() {
        System.out.println("假一赔十");
    }
}
