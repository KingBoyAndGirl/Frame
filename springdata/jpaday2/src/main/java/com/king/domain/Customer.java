package com.king.domain;

import javax.persistence.*;

/**
 * @ProjectName: jpaday1
 * @Package: com.king.domain
 * @ClassName: Customer
 * @Author: 王团结
 * @Description: 客户实体类
 *                1.实体类和表的映射关系
 *                  @Entity
 *                  @Table
 *                2.类中属性和表中字段的映射关系
 *                  @Id
 *                  @GeneratedValue
 *                  @Column
 * @Date: 2019/9/21 8:19
 * @Version: 1.0
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;    //客户主键

    @Column(name = "cust_name")
    private String custName;    //客户名称

    @Column(name = "cust_source")
    private String custSource;  //客户来源

    @Column(name = "cust_industry")
    private String custIndustry;    //客户所属行业

    @Column(name = "cust_level")
    private String custLevel;   //客户级别

    @Column(name = "cust_address")
    private String custAddress; //客户地址

    @Column(name = "cust_phone")
    private String custPhone;   //客户联系方式

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
