package com.king.domain;

import java.io.Serializable;

/**
 * @ProjectName: ssm
 * @Package: com.king.domain
 * @ClassName: Account
 * @Author: 王团结
 * @Description: 账户
 * @Date: 2019/8/30 9:47
 * @Version: 1.0
 */
public class Account implements Serializable {

    private Integer id;
    private String name;
    private Double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
