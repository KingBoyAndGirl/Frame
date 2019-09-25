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

}
