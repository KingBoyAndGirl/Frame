package com.king.test;

import com.king.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ProjectName: ssm
 * @Package: com.king.test
 * @ClassName: TestSpring
 * @Author: 王团结
 * @Description:
 * @Date: 2019/8/30 12:19
 * @Version: 1.0
 */
public class TestSpring {

    @Test
    public void run1(){
        //加载配置文件
        ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        //获取对象
        AccountService as = (AccountService) ac.getBean("accountService");

        //调用方法
        as.findAll();
    }
}
