package com.king.factory;

import com.king.domain.Account;
import com.king.service.IAccountService;
import com.king.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @ProjectName: day01_eesy_01jdbc
 * @Package: com.king.factory
 * @ClassName: BeanFactory
 * @Author: 王团结
 * @Description: 用于创建Service的代理对象的工厂
 * @Date: 2019/8/24 11:13
 * @Version: 1.0
 */
public class BeanFactory {

    private IAccountService accountService;
    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取Service代理对象
     * @param
     */
    public IAccountService getAccountService() {
        return (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 添加事务的支持
             * @param proxy
             * @param method
             * @param args
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object rtValue=null;
                try {
                    //1.开启事务
                    transactionManager.beginTransaction();
                    //2.执行操作
                    rtValue=method.invoke(accountService,args);
                    //3.提交事务
                    transactionManager.commit();
                    //4.返回结果
                    return rtValue;
                } catch (Exception e) {
                    //5.回滚操作
                    transactionManager.rollback();
                    throw new RuntimeException(e);
                } finally {
                    //6.释放连接
                    transactionManager.release();
                }
            }
        });
    }
}
