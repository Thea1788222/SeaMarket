<%--
  Created by IntelliJ IDEA.
  User: Thea
  Date: 2025/12/7
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>注册 - SeaMarket</title>
</head>
<body>

<h2>用户注册</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form action="../user" method="post">
    <input type="hidden" name="action" value="register">
    <p>用户名：<input type="text" name="username" required></p>
    <p>邮箱：<input type="email" name="email" required></p>
    <p>密码：<input type="password" name="password" required></p>
    <p><button type="submit">注册</button></p>
</form>

<p>已有账号？<a href="${pageContext.request.contextPath}/user/login.jsp">立即登录</a></p>

</body>
</html>
