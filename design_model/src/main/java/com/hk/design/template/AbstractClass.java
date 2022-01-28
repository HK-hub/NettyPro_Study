package com.hk.design.template;

/**
 * @author : HK意境
 * @ClassName : AbstractClass
 * @date : 2022/1/26 17:57
 * @description : 抽象类，定义模板方法和基本方法
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public abstract class AbstractClass {


    // 模板方法定义：完成基本方法的协调工作
    public final void cookProcess(){

        pourOil();
        heatOil();
        pourVegetable();
        pourSauce();
        fry();
        translation();

    }




    // 以下为基本方法

    // 第一步：倒油
    public void pourOil(){

        System.out.println("郭乐倒油");
    }

    // 第二部：热油
    public void heatOil(){
        System.out.println("热油");
    }



    // 第三部：到蔬菜，（一个下包菜，一个下菜心）
    public abstract void pourVegetable();


    // 第四部：倒入调料
    public abstract void pourSauce();

    // 第五部：翻炒
    public void fry(){
        System.out.println("翻炒");
    }

    // 第六部：出锅
    public void translation(){
        System.out.println("出锅");
    }

}
