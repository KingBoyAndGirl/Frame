package com.king.controller;

import com.king.domain.User;
import com.king.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: springBoot_quick
 * @Package: com.king.controller
 * @ClassName: MyBatisController
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/27 10:39
 * @Version: 1.0
 */
@Controller
public class MyBatisController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/query")
    @ResponseBody
    public List<User> queryUserList(){
        List<User> users = userMapper.queryUserList();
        return users;
    }
}
