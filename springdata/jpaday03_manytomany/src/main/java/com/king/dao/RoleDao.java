package com.king.dao;

import com.king.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ProjectName: jpaday1
 * @Package: com.king.dao
 * @ClassName: RoleDao
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/25 20:36
 * @Version: 1.0
 */
public interface RoleDao extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {
}
