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

token:  fbba7622990b199e084b13f044bd6e979c4c944f
</body>
</html>
