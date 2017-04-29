<%--
  Created by IntelliJ IDEA.
  User: L.M.Y
  Date: 2017/4/28
  Time: 下午4:42
  To change this template use File | Settings | File Templates.
--%>
<%-- 推荐将JSP放在WEB-INF内部，用户无法通过浏览器直接请求放在此处的JSP，必须通过Servlet进行转发或重定向 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Customer Management</title>
</head>
<body>
    <h1>客户列表</h1>

    <table>
        <tr>
            <th>客户名称</th>
            <th>联系人</th>
            <th>电话号码</th>
            <th>邮箱地址</th>
            <th>操作</th>
        </tr>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td>${customer.name}</td>
                <td>${customer.contact}</td>
                <td>${customer.telephone}</td>
                <td>${customer.email}</td>
                <td>
                    <a href="${BASE}/customer_edit?id=${customer.id}">编辑</a>
                    <a href="${BASE}/customer_delete?id=${customer.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
