package com.king.domain;

/**
 * @ProjectName: day01_eesy_01mybatis
 * @Package: com.king.domain
 * @ClassName: AccountUser
 * @Author: 王团结
 * @Description:
 * @Date: 2019/8/15 22:12
 * @Version: 1.0
 */
public class AccountUser extends Account {

    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString()+"AccountUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
