package com.king.dao;

import com.king.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ProjectName: jpaday1
 * @Package: com.king.dao
 * @ClassName: UserDao
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/25 20:35
 * @Version: 1.0
 */
public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

}
