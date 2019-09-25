package com.king.test;

import com.king.domain.Customer;
import com.king.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @ProjectName: jpaday1
 * @Package: com.king.test
 * @ClassName: JpaTest
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/18 8:23
 * @Version: 1.0
 */
public class JpaTest {

    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *  Jpa的操作步骤
     *       1.加载配置文件创建工厂（实体管理类工厂）对象
     *       2.通过实体管理类工厂获取实体管理器
     *       3.获取事务对象，开启事务
     *       4.完成CRUD操作
     *       5.提交事务（回滚事务）
     *       6.释放资源
     *
     */
    @Test
    public void testSave(){
        //1.加载配置文件创建工厂（实体管理类工厂）对象
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理类工厂获取实体管理器
      //  EntityManager entityManager = factory.createEntityManager();
        EntityManager entityManager = JpaUtils.getEntityManager();
        //3.获取事务对象，开启事务
        EntityTransaction transaction = entityManager.getTransaction(); //获取事务对象
        transaction.begin();    //开启事务
        //4.完成CRUD操作:保存一个用户到数据库中
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        customer.setCustIndustry("教育");
        //保存
        entityManager.persist(customer);    //保存操作
        //5.提交事务（回滚事务）
        transaction.commit();
        //6.释放资源
        entityManager.close();
       // factory.close();
    }

    /**
     * 根据id查询客户
     *  使用find方法查询：
     *      1.查询的对象就是当前客户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     * 立即加载
     *
     */
    @Test
    public void testFind(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.增删改查
        /**
         * find：根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         */
        Customer customer = entityManager.find(Customer.class,1l);
        System.out.println(customer);
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 根据id查询客户
     *      使用getReference方法
     *          1.获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句查询数据库
     *              *当调用结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     * 延迟加载（懒加载）
     *      *得到的是一个动态代理对象
     *      *什么时候用，什么时候才会查询
     */
    @Test
    public void testReference(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.增删改查
        /**
         * Reference：根据id查询数据
         *      class：查询数据的结果需要包装的实体类类型的字节码
         */
        Customer customer = entityManager.getReference(Customer.class,1l);
        System.out.println(customer);
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }


    /**
     * 删除客户
     */
    @Test
    public void testRemove(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.增删改查

        //i根据id查询客户
        //ii调用remove方法完成删除操作
        Customer customer = entityManager.find(Customer.class, 1l);
        entityManager.remove(customer);
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }

    @Test
    public void testUpdate(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.增删改查

        //i根据id查询客户
        //ii调用方法merge完成更新作
        Customer customer = entityManager.find(Customer.class, 1l);
        customer.setCustIndustry("IT教育");
        entityManager.merge(customer);
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }
}
