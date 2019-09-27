package com.king.mapper;

import com.king.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: springBoot_quick
 * @Package: com.king.mapper
 * @ClassName: UserMapper
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/27 10:35
 * @Version: 1.0
 */
@Mapper
public interface UserMapper {

        public List<User> queryUserList();
}
