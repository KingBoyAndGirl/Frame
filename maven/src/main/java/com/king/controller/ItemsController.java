package com.king.controller;

import com.king.domain.Items;
import com.king.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: maven
 * @Package: com.king.controller
 * @ClassName: ItemsController
 * @Author: 王团结
 * @Description:
 * @Date: 2019/9/3 13:23
 * @Version: 1.0
 */
@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/findDetail")
    public String findDetail(Model model){
        Items byId = itemsService.findById(1);
        model.addAttribute("item",byId);
        return "itemDetail";
    }
}
