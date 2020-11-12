package com.happyfoal.background_demo.admin.controller;

import com.happyfoal.background_demo.admin.entity.Login;
import com.happyfoal.background_demo.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

/**
 * @author majiaju
 * @date
 */
@Controller
public class AdminController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/")
    public String in1(){
        return "login";
    }

    @RequestMapping("/login")
    public String in2(){
        return "login";
    }

    @RequestMapping(value = "/change_pwd",method = RequestMethod.POST)
    public String changePwd(Login login){
        boolean flag = loginService.changePwd(login);
        if (flag){
            return "login";
        }
        return "fail";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(Login login){
        boolean flag = loginService.register(login);
        if (flag){
            return "login";
        }
        return "fail";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public String login(Login login, HttpSession session){
        boolean flag = loginService.login(login);
        if (flag){
            session.setAttribute("login",login);
            session.setAttribute("url","");
            return "redirect:/static/index.html";
        }
        return "fail";
    }
    @RequestMapping("/logout")
    public void logout(HttpSession session){
        // session.removeAttribute("login");
        session.setAttribute("url","/static/admin/register.html");
    }

}
