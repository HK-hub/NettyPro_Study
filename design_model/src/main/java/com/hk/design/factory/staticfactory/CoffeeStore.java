package com.hk.design.factory.staticfactory;

import com.hk.design.factory.simplefactory.Coffee;
import com.hk.design.factory.simplefactory.SimpleCoffeeFactory;
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
public class CoffeeStore {

    public Coffee provideCoffee(Class<? extends Coffee> clazz){

        return StaticCoffeeFactory.createCoffee(clazz);

    }


}
