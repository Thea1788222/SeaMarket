<%--
  Created by IntelliJ IDEA.
  User: Thea
  Date: 2025/12/7
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>登录 - SeaMarket</title>
</head>
<body>

<h2>用户登录</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<c:if test="${param.success eq '1'}">
    <p style="color:green;">注册成功，请登录！</p>
</c:if>

<form action="../user" method="post">
    <input type="hidden" name="action" value="login">
    <p>邮箱：<input type="text" name="email" required></p>
    <p>密码：<input type="password" name="password" required></p>
    <p><button type="submit">登录</button></p>
</form>

<p>还没有账号？<a href="register.jsp">注册一个</a></p>

</body>
</html>
