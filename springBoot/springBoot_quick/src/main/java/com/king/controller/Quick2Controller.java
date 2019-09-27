package com.king.controller;

import org.springframework.beans.factory.annotation.Value;
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
public class Quick2Controller {

    @Value("${name}")
    private String name;

    @Value(("${person.addr}"))
    private String addr;

    @RequestMapping("/quick2")
    @ResponseBody
    public String quick2(){
        //获得配置文件的信息
        return "name: "+name +"  addr " +addr;
    }
}
