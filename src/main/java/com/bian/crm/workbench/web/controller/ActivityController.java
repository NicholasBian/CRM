package com.bian.crm.workbench.web.controller;

import com.bian.crm.settings.domain.User;
import com.bian.crm.settings.service.UserService;
import com.bian.crm.settings.service.impl.UserServiceImpl;
import com.bian.crm.utils.*;
import com.bian.crm.workbench.domain.Activity;
import com.bian.crm.workbench.service.ActivityService;
import com.bian.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");

        String path = request.getServletPath();

        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request, response);
        }else if("/workbench/activity/save.do".equals(path)){
            save(request, response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动添加操作");
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //创建时间为当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人为当前登陆者，从session中获取账户名
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        Activity a = new Activity();
        a.setId(id);
        a.setName(name);
        a.setOwner(owner);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);

        boolean flag = as.save(a);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(response,uList);
    }


}
