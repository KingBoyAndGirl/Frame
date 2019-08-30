package com.king.service.impl;

import com.king.dao.IAccountDao;
import com.king.domain.Account;
import com.king.service.IAccountService;
import com.king.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl_OLD implements IAccountService {

   private IAccountDao accountDao;
   private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3.提交事务
            transactionManager.commit();
            //4.返回结果
            return accounts;
        } catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            transactionManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            Account account = accountDao.findAccountById(accountId);
            //3.提交事务
            transactionManager.commit();
            //4.返回结果
            return account;
        } catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //6.释放连接
            transactionManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            accountDao.saveAccount(account);
            //3.提交事务
            transactionManager.commit();
        } catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
        } finally {
            //6.释放连接
            transactionManager.release();
        }

    }

    @Override
    public void updateAccount(Account account) {
        try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            transactionManager.commit();
        } catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
        } finally {
            //6.释放连接
            transactionManager.release();
        }

    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作
            accountDao.deleteAccount(accountId);
            //3.提交事务
            transactionManager.commit();
        } catch (Exception e) {
            //5.回滚操作
            transactionManager.rollback();
        } finally {
            //6.释放连接
            transactionManager.release();
        }

    }

    @Override
    public void transfer(String sourceName, String targetName, float money) {
        try {
            //1.开启事务
            transactionManager.beginTransaction();
            //2.执行操作

            //2.1.根据名称查询转出账户
            Account source=accountDao.findAccountByName(sourceName);
            //2.2.根据名称查询转入账户
            Account target=accountDao.findAccountByName(targetName);
            //2.3.转出账户减钱
            source.setMoney(source.getMoney()-money);
            //2.4.转入账户加钱
            target.setMoney(target.getMoney()+money);
            //2.5.更新转出账户
            accountDao.updateAccount(source);

            int i=1/0;

            //2.6.更新转入账户
            accountDao.updateAccount(target);
            //3.提交事务
            transactionManager.commit();
        } catch (Exception e) {
            //4.回滚操作
            transactionManager.rollback();
            e.printStackTrace();
        } finally {
            //5.释放连接
            transactionManager.release();
        }

    }
}
