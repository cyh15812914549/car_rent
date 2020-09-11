<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 2020/8/14
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>菜单管理</title>
</head>
<!-- 如果使用frameset 的包含页面  主页面不能有 body-->
<frameset cols="250,*" border="1">
    <frame src="${ctx }/sys/toMenuLeft" name="left">
    <frame src="${ctx }/sys/toMenuRight" name="right">
</frameset>
</html>
