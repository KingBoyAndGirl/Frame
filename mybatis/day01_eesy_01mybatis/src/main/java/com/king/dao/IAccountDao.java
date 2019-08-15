package com.king.dao;

import com.king.domain.Account;
import com.king.domain.AccountUser;
import com.king.domain.QueryVo;
import com.king.domain.User;

import java.util.List;

/**
 * @ProjectName: day01_eesy_01mybatis
 * @Package: com.king
 * @ClassName: IUserDao
 * @Author: 王团结
 * @Description: 用户的持久层接口
 * @Date: 2019/8/12 22:17
 * @Version: 1.0
 */
public interface IAccountDao {

    /**
     * 查询所有操作,同时获取所有账户的信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有用户，并且带有用户名称和地址信息
     * @return
     */
    List<AccountUser> findAllAccount();
}
