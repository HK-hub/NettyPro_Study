package com.hk.design.factory.simplefactory;

public class LatteCoffee extends Coffee {

    public LatteCoffee(){
        addMilk();
        addSugar();
        getName() ;
    }

    @Override
    public void addMilk() {
        System.out.println("给拿铁加奶");
    }

    @Override
    public void addSugar() {
        System.out.println("给拿铁加糖");
    }

    @Override
    public String getName() {
        return "拿铁";
    }
}
