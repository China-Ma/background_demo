package com.happyfoal.background_demo.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author majiaju
 * @date
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        HttpSession session = request.getSession();
        String url = session.getAttribute("url").toString();

        //这里的User是登陆时放入session的

        //如果session中没有user，表示没登陆
        if (session.getAttribute("login") == null) {
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
            System.out.println("登录验证失败");
            response.sendRedirect("/login");
            return false;
        } else {
        //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        System.out.println("进入拦截器......");
        System.out.println(request.getRequestURL().toString());
        if (url.length()==0){
            String path = request.getRequestURL().toString();
            response.sendRedirect(path);
            return true;
        }
        response.sendRedirect("http://localhost:8083" + url);
        return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
