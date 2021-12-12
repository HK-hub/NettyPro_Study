package com.hk.netty.protocoltcp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : HK意境
 * @ClassName : MessageProtocol
 * @date : 2021/12/11 19:23
 * @description : 协议包
 * @Todo : 自定义数据传输协议
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageProtocol {

    private int len ;
    private byte[] content ;

}

