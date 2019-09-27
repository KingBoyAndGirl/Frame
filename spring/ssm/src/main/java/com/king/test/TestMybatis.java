package com.king.test;

import com.king.dao.AccountDao;
import com.king.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @ProjectName: ssm
 * @Package: com.king.test
 * @ClassName: TestMybatis
 * @Author: 王团结
 * @Description:
 * @Date: 2019/8/30 14:11
 * @Version: 1.0
 */
public class TestMybatis {

    /**
     * 查询
     * @throws Exception
     */
    @Test
    public void run1() throws Exception {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SQLSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SQLSession对象
        SqlSession session = factory.openSession();
        //获取代理对象
        AccountDao dao = session.getMapper(AccountDao.class);
        //查询所有数据
        List<Account> accounts = dao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        //关闭资源
        session.close();
        in.close();

    }

    /**
     * 保存
     * @throws Exception
     */
    @Test
    public void run2() throws Exception {

        Account account = new Account();
        account.setName("熊大");
        account.setMoney(100d);
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SQLSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SQLSession对象
        SqlSession session = factory.openSession();
        //获取代理对象
        AccountDao dao = session.getMapper(AccountDao.class);

        //保存
        dao.saveAccount(account);

        //提交事务
        session.commit();
        //关闭资源
        session.close();
        in.close();

    }
}
