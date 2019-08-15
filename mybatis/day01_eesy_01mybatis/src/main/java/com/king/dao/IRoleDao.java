package com.king.dao;

import com.king.domain.Role;

import java.util.List;

/**
 * @ProjectName: day01_eesy_01mybatis
 * @Package: com.king.dao
 * @ClassName: IRoleDao
 * @Author: 王团结
 * @Description:
 * @Date: 2019/8/15 23:09
 * @Version: 1.0
 */
public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
