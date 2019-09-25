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
 * @Description:
 * @Date: 2019/9/21 8:38
 * @Version: 1.0
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {


}
