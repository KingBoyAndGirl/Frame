package com.king.service;

import com.king.domain.Account;

import java.util.List;

/**
 * @ProjectName: ssm
 * @Package: com.king.service
 * @ClassName: AccountService
 * @Author: 王团结
 * @Description:
 * @Date: 2019/8/30 12:04
 * @Version: 1.0
 */
public interface AccountService {
    /**
     * 查询所有账户
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    public void saveAccount(Account account);
}
