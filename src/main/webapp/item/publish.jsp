<%--
  Created by IntelliJ IDEA.
  User: Thea
  Date: 2025/12/7
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.seamarket.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="../user/login.jsp"/>
</c:if>

<html>
<head>
    <title>发布商品 - SeaMarket</title>
</head>
<body>

<h2>发布商品</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form action="../item" method="post">
    <input type="hidden" name="action" value="publish">
    <p>商品标题：<input type="text" name="title" required></p>
    <p>商品描述：<br>
        <textarea name="description" rows="4" cols="40"></textarea>
    </p>
    <p>价格：<input type="number" step="0.01" name="price" required></p>
    <p><button type="submit">发布</button></p>
</form>

<p><a href="${pageContext.request.contextPath}/index.jsp">返回首页</a></p>

</body>
</html>
