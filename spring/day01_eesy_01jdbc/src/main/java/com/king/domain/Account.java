package com.king.domain;

import java.io.Serializable;

/**
 * @ProjectName: day01_eesy_01jdbc
 * @Package: com.king.dao
 * @ClassName: Account
 * @Author: 王团结
 * @Description: 账户实体类
 * @Date: 2019/8/21 22:00
 * @Version: 1.0
 */
public class Account implements Serializable {

    private Integer id;
    private String name;
    private float money;

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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
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
