package com.happyfoal.background_demo.admin.controller;

import com.happyfoal.background_demo.admin.entity.Login;
import com.happyfoal.background_demo.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author majiaju
 * @date
 */
@Controller
public class AdminController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/")
    public String in(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Login login){

        boolean flag = loginService.login(login);
        if (flag){
            return "index";
        }
        return "error";
    }


}
