package com.king.repository;

import com.king.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @ProjectName: springBoot_quick
 * @Package: com.king.repository
 * @ClassName: UserRepositiry
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/27 11:42
 * @Version: 1.0
 */
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    public List<User> findAll();
}
