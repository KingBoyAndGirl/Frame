package com.king.domain;

import java.io.Serializable;

/**
 * @ProjectName: day01_eesy_01mybatis
 * @Package: com.king.domain
 * @ClassName: Account
 * @Author: 王团结
 * @Description:
 * @Date: 2019/8/15 14:16
 * @Version: 1.0
 */
public class Account implements Serializable{
    private Integer id;
    private Integer uid;
    private Double money;

    //从表实体应该包含一个主表实体的对象引用
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
