package com.king.dao;

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
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总记录数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo查询中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
