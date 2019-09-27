package com.king.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: springBoot_quick
 * @Package: com.king.controller
 * @ClassName: Quick2Controller
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/27 9:22
 * @Version: 1.0
 */
@Controller
@ConfigurationProperties(prefix = "person")
public class Quick3Controller {


    private String name;
    private String addr;
    private Integer age;

    @RequestMapping("/quick3")
    @ResponseBody
    public String quick2(){
        //获得配置文件的信息



        return "name: " +name+" addr: "+addr+" age： "+age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
