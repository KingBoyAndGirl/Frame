package com.king.dao;

import com.king.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ProjectName: jpaday1
 * @Package: com.king.dao
 * @ClassName: Customer
 * @Author: 王团结
 * @Description: 符合SpringDataJpa的dao层接口规范
 * JpaRepository<操作的实体类类型，实体类中主键属性的类型>
 * *封装了基本的CRUD操作
 * JpaSpecificationExecutor<操作的实体类型>
 * *封装了复杂查询（分页）
 * @Date: 2019/9/21 8:38
 * @Version: 1.0
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    /**
     * 案例：根据客户名称查询客户
     *      使用jpql的形式查询
     *  jpql：from Customer where custName=？1
     *
     *  配置jpql语句，使用@Query注解
     * @param custName
     * @return
     */
    @Query(value = "from Customer where custName = ?1")
    public Customer findJpql(String custName);

    /**
     * 案例：根据客户名称和客户id查询客户
     *         jpql:from Customer where custName = ?1 and custId = ?1
     */
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    public Customer findCustNameAndId(String Name,Long cust);

    /**
     * 使用jpql完成更新操作
     *      案例：根据id更新，客户的名称
     *             更新4号客户的名称，将名称改为“黑马程序员”
     * sql:update cst_customer set cust_name = ? where cust_id= ?
     * jpql: update Customer set custName=? where custId= ?
     *
     *  @Query：代表的是进行查询
     *      *声明此方法是用来进行更新操作
     *  @Modifying
     *      *当前执行的是一个更新操作
     */
    @Query(value = "update Customer set custName= ?2 where custId= ?1")
    @Modifying
    public void updateCustomer(long custId,String custName);

    /**
     * 使用sql的形式查询：
     *      查询全部的客户
     *     sql:select * from cst_customer;
     *
     *  Query：配置sql查询
     *       value：sql语句
     *       nativeQuery：查询方式
     *       true   :  sql查询
     *       false  ：  jpql查询
     */
    //@Query(value = "select '*' from cst_customer",nativeQuery = true)
    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    public List<Object []> findSql(String name);


    /**
     * 方法名的约定：
     *      findBy：查询
     *          对象中的属性名称（首字母大写） ： 查询条件
     *          CustName
     *              *默认情况：使用等于的方式查询
     *                     特殊的查询方式
     *
     *  findByCustName  --根据客户名称查询
     *
     *  在springdataJpa的运行阶段
     *          会根据方法名称进行解析     findBy  from  xxx（实体类）
     *                                       属性名称     where  custName =
     *
     *      1.findBy + 属性名称 （根据属性名称进行完成匹配的查询=）
     *      2.findBy+属性名称+“查询方式（Like | isnull）”
     *          findByCustNameLike
     *      3.多条件查询
     *          findBy+属性名+“查询方式”+“多条件的连接符（and | or ） ”+ 属性名 + “查询方式”
     */
    public Customer findByCustName(String custName);

    public List<Customer> findByCustNameLike(String custName);

    //使用客户名称模糊匹配和客户所属行业精准匹配
    public Customer findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
}
