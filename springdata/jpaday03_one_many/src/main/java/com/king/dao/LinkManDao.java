package com.king.dao;

import com.king.domain.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ProjectName: jpaday1
 * @Package: com.king.dao
 * @ClassName: LinkManDao
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/24 22:12
 * @Version: 1.0
 */
public interface LinkManDao extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkManDao> {
}
