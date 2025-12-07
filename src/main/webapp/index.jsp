<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>SeaMarket - 首页</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div class="container">
    <h2>欢迎来到 SeaMarket 二手市场</h2>

    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <p>当前用户：<b>${sessionScope.user.username}</b></p>
            <p>
                <a href="${pageContext.request.contextPath}/item/publish.jsp">发布商品</a>
                <a href="${pageContext.request.contextPath}/item?action=search">查看所有商品</a>
                <a href="user?action=logout">退出登录</a>
            </p>
        </c:when>
        <c:otherwise>
            <p>
                <a href="${pageContext.request.contextPath}/user/login.jsp">立即登录</a>
                <a href="${pageContext.request.contextPath}/user/register.jsp">注册新账号</a>
            </p>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
