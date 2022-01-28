package com.hk.design.strategy;

/**
 * @author : HK意境
 * @ClassName : Client
 * @date : 2022/1/26 19:53
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class Client {

    public static void main(String[] args) {

        SaleMan saleMan = new SaleMan(new StrategyC());
        saleMan.sale();

        saleMan.setStrategy(new StrategyA());
        saleMan.sale();
    }



}
