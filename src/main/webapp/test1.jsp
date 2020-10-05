<%--
  Created by IntelliJ IDEA.
  User: nicolas
  Date: 19/9/20
  Time: 3:00 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath %>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Insert title here</title>
</head>
<body>
$.ajax({
url:"",
data:{

},
type:"",
dataType:"json",
success:function(data){

}
})

//创建时间为当前系统时间
String createTime = DateTimeUtil.getSysTime();
//创建人为当前登陆者，从session中获取账户名
String createBy = ((User)request.getSession().getAttribute("user")).getName();


token:  fbba7622990b199e084b13f044bd6e979c4c944f

$(".time").datetimepicker({
minView: "month",
language:  'zh-CN',
format: 'yyyy-mm-dd',
autoclose: true,
todayBtn: true,
pickerPosition: "bottom-left"
});

<%--
$代表jQuery对象，同时也是一个函数对象
$()和jQuery()是jQuery的核心函数，执行这两个元素返回的是一个DOM元素
$()是一个函数，等同于jQuery()，可在括号内传参数，传参后可获取元素
$(".one")表示获取class=“one"的元素，返回一个jQuery对象
$(”.one").onclick表示class="one"的点击事件
$.post() $.get() $.ajax() 都是jQuery对象的方法
--%>
</body>
</html>
