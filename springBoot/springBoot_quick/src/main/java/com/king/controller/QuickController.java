package com.king.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: springBoot_quick
 * @Package: com.king.controller
 * @ClassName: QuickController
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/26 20:19
 * @Version: 1.0
 */
@Controller
public class QuickController {

    @RequestMapping("/quick")
    @ResponseBody
    public String quick(){
        return "hello springboot";
    }
}
