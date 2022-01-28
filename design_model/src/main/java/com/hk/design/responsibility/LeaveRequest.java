package com.hk.design.responsibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : HK意境
 * @ClassName : LeaveRequest
 * @date : 2022/1/27 17:00
 * @description : 请假条 ,请求发起者
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaveRequest {

    private String name ;
    private int days ;
    private String cause ;





}
