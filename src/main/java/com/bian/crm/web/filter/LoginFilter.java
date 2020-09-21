package com.bian.crm.web.filter;

import com.bian.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到判断是否登陆的过滤器");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();
        //不应该被拦截的资源 自动放行
        if("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{//其他资源必须验证是否登陆
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");

            //如果User不为null 说明登陆过
            if(user != null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                //重定向到login页
            /*
            重定向的路径怎么写？
            在实际项目开发中，对于路径的使用，不论前端后端，一律使用绝对路径
            转发：
                使用的是一种特殊的绝对路径，前面不加/项目名，这种路径也称为内部路径
                /login.jsp
            重定向：
                使用的是传统绝对路径的写法，前面必须以/项目名开头，后面跟具体的资源路径
                /CRM/login.jsp
            为什么使用重定向而不能使用转发？
                转发后路径会停留在老路径上，而不是跳转之后最新资源的路径
                我们应该在为用户跳转到登陆页的同时，将浏览器的地址栏自动设置为当前的登陆页路径
             */
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }


    }
}
