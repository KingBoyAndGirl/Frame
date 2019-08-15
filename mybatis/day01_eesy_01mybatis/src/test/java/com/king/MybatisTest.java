package com.king;

import com.king.dao.IUserDao;
import com.king.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
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
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

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
        userDao = sqlSession.getMapper(IUserDao.class);
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

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存
     */
    @Test
    public void testSave() throws IOException {
        User user = new User();
        user.setUsername("mybatis lasertid");
        user.setAddress("河南");
        user.setSex("男");
        user.setBirthday(new Date());

        System.out.println("保存之前"+user);

        //5.使用代理对象执行方法
        userDao.saveUser(user);

        System.out.println("保存之后"+user);
    }

    /**
     * 测试更新
     * @throws IOException
     */
    @Test
    public void testUpdate() throws IOException {
        User user = new User();
        user.setId(50);
        user.setUsername("mybatis updateUser");
        user.setAddress("河南");
        user.setSex("女");
        user.setBirthday(new Date());

        //5.使用代理对象执行方法
        userDao.updateUser(user);


    }

    /**
     * 测试删除
     * @throws IOException
     */
    @Test
    public void testDelete() throws IOException {

        //5.使用代理对象执行方法
        userDao.deleteUser(48);
    }

    /**
     * 测试查询一个
     * @throws IOException
     */
    @Test
    public void testFindOne() throws IOException {

        //5.使用代理对象执行方法
        User user=userDao.findById(50);
        System.out.println(user);
    }


    /**
     * 测试模糊查询
     * @throws IOException
     */
    @Test
    public void testFindByName() throws IOException {

        //5.使用代理对象执行方法
        //List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        System.out.println(users);
    }

    /**
     * 测试查询总记录数
     * @throws IOException
     */
    @Test
    public void testFindTotal() throws IOException {

        //5.使用代理对象执行方法
        int total = userDao.findTotal();
        System.out.println(total);
    }

    /**
     * 测试使用QueryVo作为查询条件
     * @throws IOException
     */
    @Test
    public void testfindUserByVo() throws IOException {

        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        queryVo.setUser(user);
        //5.使用代理对象执行方法
        List<User> users = userDao.findUserByVo(queryVo);
        System.out.println(users);
    }

    /**
     * 测试条件查询
     * @throws IOException
     */
    @Test
    public void testFindUserByCondition() throws IOException {

        User u = new User();
        u.setUsername("%王%");
        u.setAddress("%北京%");
        List<User> userByCondition = userDao.findUserByCondition(u);
        System.out.println(userByCondition);
    }

    /**
     * 测试foreach标签的使用
     * @throws IOException
     */
    @Test
    public void testFindUserInIds() throws IOException {

        QueryVo queryVo = new QueryVo();
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(42);
        list.add(46);
        queryVo.setIds(list);


        List<User> userByCondition = userDao.findUserInIds(queryVo);
        System.out.println(userByCondition);
    }
}
