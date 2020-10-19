package com.bian.crm.workbench.web.controller;

import com.bian.crm.settings.domain.User;
import com.bian.crm.settings.service.UserService;
import com.bian.crm.settings.service.impl.UserServiceImpl;
import com.bian.crm.utils.DateTimeUtil;
import com.bian.crm.utils.PrintJson;
import com.bian.crm.utils.ServiceFactory;
import com.bian.crm.utils.UUIDUtil;
import com.bian.crm.vo.PaginationVO;
import com.bian.crm.workbench.domain.Activity;
import com.bian.crm.workbench.domain.ActivityRemark;
import com.bian.crm.workbench.domain.Clue;
import com.bian.crm.workbench.domain.Tran;
import com.bian.crm.workbench.service.ActivityService;
import com.bian.crm.workbench.service.ClueService;
import com.bian.crm.workbench.service.impl.ActivityServiceImpl;
import com.bian.crm.workbench.service.impl.ClueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到线索活动控制器");

        String path = request.getServletPath();

        if ("/workbench/clue/getUserList.do".equals(path)) {
            getUserList(request, response);
        } else if ("/workbench/clue/save.do".equals(path)) {
            save(request, response);
        } else if ("/workbench/clue/detail.do".equals(path)) {
            detail(request, response);
        } else if ("/workbench/clue/getActivityListByClueId.do".equals(path)) {
            getActivityListByClueId(request, response);
        } else if ("/workbench/clue/unbund.do".equals(path)) {
            unbund(request, response);
        } else if ("/workbench/clue/getActivityListByNameAndNotByClueId.do".equals(path)) {
            getActivityListByNameAndNotByClueId(request, response);
        } else if ("/workbench/clue/bund.do".equals(path)) {
            bund(request, response);
        } else if ("/workbench/clue/getActivityListByName.do".equals(path)) {
            getActivityListByName(request, response);
        } else if ("/workbench/clue/convert.do".equals(path)) {
            convert(request, response);
        } else if ("/workbench/clue/pageList.do".equals(path)) {
            pageList(request, response);
        } else if ("/workbench/clue/delete.do".equals(path)) {
            delete(request, response);
        } else if ("/workbench/clue/getUserListAndClue.do".equals(path)) {
            getUserListAndClue(request, response);
        } else if ("/workbench/clue/update.do".equals(path)) {
            update(request, response);
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("更新线索信息");

        String id = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String mphone = request.getParameter("mphone");
        String website = request.getParameter("website");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");

        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();


        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());

        Clue c = new Clue();
        c.setId(id);
        c.setFullname(fullname);
        c.setAppellation(appellation);
        c.setOwner(owner);
        c.setCompany(company);
        c.setJob(job);
        c.setEmail(email);
        c.setPhone(phone);
        c.setMphone(mphone);
        c.setWebsite(website);
        c.setState(state);
        c.setSource(source);
        c.setDescription(description);
        c.setContactSummary(contactSummary);
        c.setNextContactTime(nextContactTime);
        c.setAddress(address);
        c.setEditBy(editBy);
        c.setEditTime(editTime);

        boolean flag = cs.update(c);

        PrintJson.printJsonFlag(response,flag);

    }

    private void getUserListAndClue(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入查询用户信息列表和根据线索id查询单条记录的操作");
        String id = request.getParameter("id");
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        //map中包含uList和a
        Map<String, Object> map = cs.getUserListAndClue(id);
        PrintJson.printJsonObj(response,map);
        /*
        总结： controller调用service的方法，返回值应该是什么？
              前端要什么，就从service层取什么
              前端需要的，管业务层去要
         */
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行删除选定线索的操作");

        String ids[] = request.getParameterValues("id");

        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());

        boolean flag = cs.delete(ids);

        PrintJson.printJsonFlag(response,flag);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到查询线索活动信息列表的操作（结合分页查询和条件查询）");

        String id = request.getParameter("id");
        String fullname = request.getParameter("fullname");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String source = request.getParameter("source");
        String state = request.getParameter("state");
        String phone = request.getParameter("phone");
        String mphone = request.getParameter("mphone");


        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出略过的记录数
        int skipCount = (pageNo - 1)*pageSize;

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("fullname",fullname);
        map.put("owner",owner);
        map.put("company",company);
        map.put("source",source);
        map.put("state",state);
        map.put("phone",phone);
        map.put("mphone",mphone);

        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        PaginationVO<Clue> vo = cs.pageList(map);
        PrintJson.printJsonObj(response,vo);
    }

    private void convert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("执行线索转换操作");

        String clueId = request.getParameter("clueId");

        String flag = request.getParameter("flag");

        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        Tran t = null;

        if("a".equals(flag)){
            t = new Tran();

            //接收交易表单中的参数
            String money = request.getParameter("money");
            String name = request.getParameter("name");
            String expectedDate = request.getParameter("expectedDate");
            String stage = request.getParameter("stage");
            String activityId = request.getParameter("activityId");
            String id = UUIDUtil.getUUID();
            String createTime = DateTimeUtil.getSysTime();


            t.setId(id);
            t.setMoney(money);
            t.setName(name);
            t.setExpectedDate(expectedDate);
            t.setStage(stage);
            t.setActivityId(activityId);
            t.setCreateBy(createBy);
            t.setCreateTime(createTime);
        }

        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());

        /*

            为业务层传递的参数：

            1.必须传递的参数clueId，有了这个clueId之后我们才知道要转换哪条记录
            2.必须传递的参数t，因为在线索转换的过程中，有可能会临时创建一笔交易（业务层接收的t也有可能是个null）

         */
        boolean flag1 = cs.convert(clueId,t,createBy);

        if(flag1){

            response.sendRedirect(request.getContextPath()+"/workbench/clue/index.jsp");

        }
    }

    private void getActivityListByName(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("查询市场活动列表（根据名称模糊查）");

        String aname = request.getParameter("aname");

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        List<Activity> aList = as.getActivityListByName(aname);

        PrintJson.printJsonObj(response,aList);
    }

    private void bund(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行关联市场活动的操作");

        String cid = request.getParameter("cid");

        String aids[] = request.getParameterValues("aid");

        ClueService cs = (ClueService)ServiceFactory.getService(new ClueServiceImpl());

        boolean flag = cs.bund(cid,aids);

        PrintJson.printJsonFlag(response,flag);

    }

    private void getActivityListByNameAndNotByClueId(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("查询市场活动列表（根据名称模糊查+排除掉已经关联指定线索的列表）");

        String aname = request.getParameter("aname");
        String clueId = request.getParameter("clueId");

        Map<String,String> map = new HashMap<String, String>();

        map.put("aname",aname);
        map.put("clueId",clueId);

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        List<Activity> aList = as.getActivityListByNameAndNotByClueId(map);

        PrintJson.printJsonObj(response,aList);
    }

    private void unbund(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("解除关联操作");

        String id = request.getParameter("id");

        ClueService cs = (ClueService)ServiceFactory.getService(new ClueServiceImpl());

        boolean flag = cs.unbund(id);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getActivityListByClueId(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("根据线索id查询关联的市场活动列表");

        String clueId = request.getParameter("clueId");

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        List<Activity> aList = as.getActivityListByClueId(clueId);

        PrintJson.printJsonObj(response,aList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("跳转到线索详细信息页");

        String id = request.getParameter("id");

        ClueService cs = (ClueService)ServiceFactory.getService(new ClueServiceImpl());

        Clue c = cs.detail(id);

        request.setAttribute("c",c);

        request.getRequestDispatcher("/workbench/clue/detail.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行线索添加操作");

        String id = UUIDUtil.getUUID();
        String fullname = request.getParameter("fullname");
        String appellation = request.getParameter("appellation");
        String owner = request.getParameter("owner");
        String company = request.getParameter("company");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String website = request.getParameter("website");
        String mphone = request.getParameter("mphone");
        String state = request.getParameter("state");
        String source = request.getParameter("source");
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");
        String address = request.getParameter("address");

        Clue c = new Clue();
        c.setId(id);
        c.setFullname(fullname);
        c.setAppellation(appellation);
        c.setOwner(owner);
        c.setCompany(company);
        c.setJob(job);
        c.setEmail(email);
        c.setPhone(phone);
        c.setWebsite(website);
        c.setMphone(mphone);
        c.setState(state);
        c.setSource(source);
        c.setCreateBy(createBy);
        c.setCreateTime(createTime);
        c.setDescription(description);
        c.setContactSummary(contactSummary);
        c.setNextContactTime(nextContactTime);
        c.setAddress(address);

        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());

        boolean flag = cs.save(c);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("取得用户信息列表");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(response, uList);
    }
}
