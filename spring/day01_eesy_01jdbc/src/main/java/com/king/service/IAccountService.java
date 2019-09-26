package com.king.service;

import com.king.domain.Account;

import java.util.List;

/**
 * @ProjectName: day01_eesy_01jdbc
 * @Package: com.king.service
 * @ClassName: IAccountService
 * @Author: 王团结
 * @Description: 账户业务层的接口
 * @Date: 2019/8/19 9:27
 * @Version: 1.0
 */
public interface IAccountService {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);


    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     * 转账
     * @param sourceName        转出账户名称
     * @param targetName        转入账户名称
     * @param money             转账金额
     */
    void transfer(String sourceName,String targetName,float money);
}