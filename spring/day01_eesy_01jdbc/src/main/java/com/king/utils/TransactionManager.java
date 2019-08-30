package com.king.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ProjectName: day01_eesy_01jdbc
 * @Package: com.king.utils
 * @ClassName: TransactionManager
 * @Author: 王团结
 * @Description: 和事物管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 * @Date: 2019/8/24 8:53
 * @Version: 1.0
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放连接
     */
    public void release (){
        try {
            connectionUtils.getThreadConnection().close();    //还回池中
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
