package com.hk.design.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : HK意境
 * @ClassName : SaleMan
 * @date : 2022/1/26 19:50
 * @description : 定义环境角色，用于连接上下文
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleMan {


    // 聚合策略类对象
    private Strategy strategy ;

    public void sale(){
        strategy.show();
    }


    // 由促销员展示促销活动给用户



}
