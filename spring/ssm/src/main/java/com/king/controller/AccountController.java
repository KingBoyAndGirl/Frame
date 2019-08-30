package com.king.controller;

import com.king.domain.Account;
import com.king.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: ssm
 * @Package: com.king.controller
 * @ClassName: AccountContreller
 * @Author: 王团结
 * @Description: 账户web
 * @Date: 2019/8/30 12:06
 * @Version: 1.0
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("业务层，查询所有账户。。");
        //调用service的方法
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping("/save")
    public String save(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("业务层，查询所有账户。。");
        //调用service的方法
        accountService.saveAccount(account);
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return "list";
    }
}
