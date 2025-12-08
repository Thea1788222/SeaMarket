
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

<form action="${pageContext.request.contextPath}/user" method="post">
    <input type="hidden" name="action" value="login">
    <p>邮箱：<input type="text" name="email" required></p>
    <p>密码：<input type="password" name="password" required></p>
    <p><button type="submit">登录</button></p>
</form>

<p>还没有账号？<a href="${pageContext.request.contextPath}/register.jsp">注册一个</a></p>

</body>
</html>
