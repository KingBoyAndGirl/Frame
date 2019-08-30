package com.king.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @ProjectName: jdbc
 * @Package: com.king.jdbctemplate
 * @ClassName: JdbcTemplateDemo1
 * @Author: 王团结
 * @Description: JdbcTemplate的最基本用法
 * @Date: 2019/8/24 21:15
 * @Version: 1.0
 */
public class JdbcTemplateDemo1 {

    public void m(){

    }
    public static void main(String[] args) {
        //准备数据源:spring内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/eesy?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("18339401841");

        //1.创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        //给jt设置数据源
        jt.setDataSource(ds);
        //2.执行操作

        jt.execute("insert into account(name,money) values('ddd',1000)");
    }
}
