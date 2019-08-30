package com.king.dao;

import com.king.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm
 * @Package: com.king.dao
 * @ClassName: AccountDao
 * @Author: 王团结
 * @Description: 账户dao接口
 * @Date: 2019/8/30 9:51
 * @Version: 1.0
 */
@Repository
public interface AccountDao {

    /**
     * 查询所有账户
     * @return
     */
    @Select("select * from account")
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    @Insert("insert into account(name,money) values(#{name},#{money})")
    public void saveAccount(Account account);
}
