package com.king;

import com.king.domain.User;
import com.king.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ProjectName: springBoot_quick
 * @Package: com.king
 * @ClassName: JpaTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/27 11:57
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class JpaTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        List<User> list = userRepository.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
