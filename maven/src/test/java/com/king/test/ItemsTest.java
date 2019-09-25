package com.king.test;

import com.king.dao.ItemsDao;
import com.king.domain.Items;
import com.king.service.ItemsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ProjectName: maven
 * @Package: com.king.test
 * @ClassName: ItemsTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/3 10:58
 * @Version: 1.0
 */
public class ItemsTest {

    @Test
    public void findById(){
        //获取spring容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//        //dao测试
//        //从容器中拿到所需的dao的代理对象
//        ItemsDao itemsDao = ac.getBean(ItemsDao.class);
//        //调用方法
//        Items items = itemsDao.findById(1);
//        System.out.println(items);

        //service测试
        ItemsService itemsService = ac.getBean(ItemsService.class);
        //调用方法
        Items items = itemsService.findById(1);
        System.out.println(items);
    }
}
