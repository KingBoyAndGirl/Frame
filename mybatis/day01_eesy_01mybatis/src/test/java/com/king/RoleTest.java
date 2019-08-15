package com.king;

import com.king.dao.IRoleDao;
import com.king.dao.IUserDao;
import com.king.domain.Role;
import com.king.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ProjectName: day01_eesy_01mybatis
 * @Package: com.king
 * @ClassName: MybatisTest
 * @Author: 王团结
 * @Description: mybatis的入门案例
 * @Date: 2019/8/12 22:14
 * @Version: 1.0
 */
public class RoleTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao roleDao;

    @Before //用于在测试方法之前执行
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        //3.使用工厂生产SqlSession对象
        sqlSession = factory.openSession();

        //4.使用SqlSession创建Dao接口的代理对象
        roleDao = sqlSession.getMapper(IRoleDao.class);
    }

    @After //用于在测试方法之后执行
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 入门案例
     */
    @Test
    public  void testFindAll() throws IOException {

        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }


}
