package com.king.test;

import com.king.domain.Customer;
import com.king.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @ProjectName: jpaday1
 * @Package: com.king.test
 * @ClassName: JpaTest
 * @Author: 王团结
 * @Description:   测试jpql查询
 * @Date: 2019/9/18 8:23
 * @Version: 1.0
 */
public class JpqlTest {

    /**
     * 查询全部
     *      jpql：from com.king.domain.Customer
     *      sql：select * from cst_customer;
     */
    @Test
    public void testFindAll(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.查询全部
        String jpql="from Customer";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象

        //发送查询，并封装结果集
        List list = query.getResultList();

        for (Object o : list) {
            System.out.println(o);
        }
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();

    }

    /**
     * 排序查询：倒叙查询全部客户（根据id倒叙）
     *      sql：SELECT * FROM cst_customer ORDER BY cust_id DESC;
     *      jpql：from Customer order by custId desc
     *
     * 进行jpql查询
     *      1.创建query查询对象
     *      2.对参数进行赋值
     *      3.查询，并得到返回结果集。
     */
    @Test
    public void testOrders(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.查询全部
        String jpql="from Customer order by custId desc";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象

        //发送查询，并封装结果集
        List list = query.getResultList();

        for (Object o : list) {
            System.out.println(o);
        }
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();

    }

    /**
     * 使用jpql查询，统计客户的总数
     *      sql：SELECT COUNT(cust_id) FROM cst_customer;
     *      jpql：select count(custId) from Customer
     */

    @Test
    public void testCount(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql="select count(custId) from Customer";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //ii.对参数赋值
        //iii.发送查询，并封装结果

        /**
         * getResultList：直接将查询结果封装为list集合
         * singleResult：得到唯一的结果集
         */
        Object result = query.getSingleResult();
        System.out.println(result);
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();

    }


    /**
     * 分页查询
     *      sql：select * from cst_customer limit ?,?
     *      jpql: from Customer
     */
    @Test
    public void testPaged(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql="from Customer";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //ii.对参数赋值--分页参数
        //起始索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);

        //iii.发送查询，并封装结果

        /**
         * getResultList：直接将查询结果封装为list集合
         * singleResult：得到唯一的结果集
         */
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();

    }

    /**
     * 条件查询：
     *      案例：查询客户名以“传智播客”开头的客户
     *      sql:select * from cst_customer WHERE cust_name LIKE ?
     *      jpql: from Customer where custName like ?
     */
    @Test
    public void testCondition(){
        //1.通过工具类获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql="from Customer where custName like ?1 ";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //ii.对参数赋值--占位符参数
        //第一个参数：占位符的索引位置，第二个参数：取值
        query.setParameter(1,"传智播客%");

        //iii.发送查询，并封装结果

        /**
         * getResultList：直接将查询结果封装为list集合
         * singleResult：得到唯一的结果集
         */
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();

    }

}
