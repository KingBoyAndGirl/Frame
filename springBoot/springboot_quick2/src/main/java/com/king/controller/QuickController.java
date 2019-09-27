package com.king.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springBoot_quick
 * @Package: com.king.controller
 * @ClassName: QuickController
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/26 21:37
 * @Version: 1.0
 */
@RestController
public class QuickController {

    @RequestMapping("/quick2")
    public String quick(){
        return "springBoot !!!";
    }
}
