package com.king;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.king.domain.User;
import com.king.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ProjectName: springBoot_quick
 * @Package: com.king
 * @ClassName: RedisTes
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/27 12:30
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes =SpringbootJpaApplication.class)
public class RedisTes {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    @Test
    public void test() throws JsonProcessingException {
        //1.redis中获得数据 数据的形式json字符串
        String userListJson = redisTemplate.boundValueOps("user.findAll").get();
        //2.判断redis中是否存在数据
        if (userListJson==null){
            //3.如果不存在数据，从数据库查询
            List<User> userlist = userRepository.findAll();
            //4.将查询出的数据存储到redis缓存中
            //先将list集合转换成json格式的字符串     使用jackson进行转换
            ObjectMapper objectMapper=new ObjectMapper();
            userListJson= objectMapper.writeValueAsString(userlist);
            redisTemplate.boundValueOps("user.findAll").set(userListJson);
            System.out.println("=============从数据库中获得user的数据=====================");
        }else {
            System.out.println("=============从redis缓存中获得user的数据=====================");
        }

        //4.将数据在控制台打印
        System.out.println(userListJson);
    }
}
