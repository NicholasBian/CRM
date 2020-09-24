package com.bian.crm.workbench.web.controller;

import com.bian.crm.settings.domain.User;
import com.bian.crm.settings.service.UserService;
import com.bian.crm.settings.service.impl.UserServiceImpl;
import com.bian.crm.utils.*;
import com.bian.crm.vo.PaginationVO;
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
        }else if("/workbench/activity/pageList.do".equals(path)){
            pageList(request, response);
        }else if("/workbench/activity/delete.do".equals(path)){
            delete(request, response);
        }else if("/workbench/activity/getUserListAndActivity.do".equals(path)){
            getUserListAndActivity(request, response);
        }else if("/workbench/activity/update.do".equals(path)){
            update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行市场活动修改操作");
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //修改时间为当前系统时间
        String editTime = DateTimeUtil.getSysTime();
        //修改人为当前登陆者，从session中获取账户名
        String editBy = ((User)request.getSession().getAttribute("user")).getName();

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        Activity a = new Activity();
        a.setId(id);
        a.setName(name);
        a.setOwner(owner);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setEditTime(editTime);
        a.setEditBy(editBy);

        boolean flag = as.update(a);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入查询用户信息列表和根据市场活动id查询单条记录的操作");
        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        //map中包含uList和a
        Map<String, Object> map = as.getUserListAndActivity(id);
        PrintJson.printJsonObj(response,map);
        /*
        总结： controller调用service的方法，返回值应该是什么？
              前端要什么，就从service层取什么
              前端需要的，管业务层去要
         */
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动的删除操作");
        String ids[] = request.getParameterValues("id");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = as.delete(ids);

        PrintJson.printJsonFlag(response,flag);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到查询市场活动信息列表的操作（结合分页查询和条件查询）");

        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出略过的记录数
        int skipCount = (pageNo - 1)*pageSize;

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        /*
            前端要：市场活动信息列表
                  查询总条数

                  业务层拿到了以上两条信息之后，如果做返回
                  map
                  map.put("dataList":dataList)
                  map.put("total":total)
                  PrintJson map-->json
                  {"total":100,"dataList":[{1},{2},{3}]}

                  vo
                  PaginationVO<T>
                    private int total;
                    private List<T> dataList;

                  PaginationVO<Activity> vo = new PaginationVO<>;
                  vo.setTotal(total)
                  将来分页查询，每个模块都有，所以我们选择使用一个通用vo，操作起来比较方便
         */
        PaginationVO<Activity> vo = as.pageList(map);
        PrintJson.printJsonObj(response,vo);
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
