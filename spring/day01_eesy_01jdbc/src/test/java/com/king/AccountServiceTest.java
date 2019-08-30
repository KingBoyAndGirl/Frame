package com.king;

import com.king.domain.Account;
import com.king.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ProjectName: day01_eesy_01jdbc
 * @Package: com.king
 * @ClassName: AccountServiceTest
 * @Author: 王团结
 * @Description: 使用Junit单元测试，测试我们的配置
 * @Date: 2019/8/21 20:18
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    @Qualifier("proxyAccountService")
    private IAccountService as;

    @Test
    public void testTransfer(){
        as.transfer("ccc","bbb",10000);
    }


}
