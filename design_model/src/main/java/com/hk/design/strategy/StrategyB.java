package com.hk.design.strategy;

/**
 * @author : HK意境
 * @ClassName : StrategyB
 * @date : 2022/1/26 19:47
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class StrategyB implements Strategy{
    @Override
    public void show() {
        System.out.println("买一送一");
    }
}
