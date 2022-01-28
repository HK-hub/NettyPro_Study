package com.hk.design.template;

/**
 * @author : HK意境
 * @ClassName : ConcreteClassBaocai
 * @date : 2022/1/26 18:27
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ConcreteClassCaixin extends AbstractClass{


    // 倒入蔬菜
    @Override
    public void pourVegetable() {
        System.out.println("下菜心");
    }

    @Override
    public void pourSauce() {
        System.out.println("下锅的酱料是豆瓣");
    }
}
