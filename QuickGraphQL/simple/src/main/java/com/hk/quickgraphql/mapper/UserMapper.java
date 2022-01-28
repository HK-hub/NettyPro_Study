package com.hk.quickgraphql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hk.quickgraphql.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName : UserMapper
 * @author : HK意境
 * @date : 2022/1/28
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
