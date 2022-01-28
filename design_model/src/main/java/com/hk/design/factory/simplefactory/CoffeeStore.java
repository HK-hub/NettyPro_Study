package com.hk.design.factory.simplefactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : HK意境
 * @ClassName : CoffeeStore
 * @date : 2022/1/20 17:10
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeStore {

    private SimpleCoffeeFactory factory ;



    public Coffee provideCoffee(Class<? extends Coffee> clazz){

        return this.factory.createCoffee(clazz);

    }


}
