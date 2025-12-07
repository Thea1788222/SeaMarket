
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <title>商品列表 - SeaMarket</title>
</head>
<body>

<h2>商品列表</h2>

<form action="../item" method="get">
    <input type="hidden" name="action" value="search">
    <input type="text" name="keyword" placeholder="搜索商品...">
    <button type="submit">搜索</button>
</form>

<hr>

<c:choose>
    <c:when test="${empty items}">
        <p>没有找到任何商品。</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" items="${items}">
            <div style="border:1px solid #ddd;padding:10px;margin-bottom:10px;">
                <p><b>${item.title}</b></p>
                <p>价格：¥${item.price}</p>
                <p>
                    <c:out value="${item.description}" escapeXml="false" />
                </p>



                <c:if test="${sessionScope.user != null && sessionScope.user.id == item.sellerId}">
                    <a href="${pageContext.request.contextPath}/item?action=delete&id=${item.id}" onclick="return confirm('确定删除吗？');">删除</a>
                </c:if>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/index.jsp">返回首页</a></p>

</body>
</html>
