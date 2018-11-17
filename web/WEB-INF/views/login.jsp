<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/17
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>login page</title>
</head>
<body>
<c:url value="/login" var="loginUrl"/>
    <form action="${loginUrl}" method="post"><!-- 认证后再次走/login,认证失败默认会停留在login.jsp页面并提示失败，认证成功默认会跳转到web工程默认首页，即index.jsp -->
        <c:if test="${param.error != null}">
            <p>
                Invalid username and password.
            </p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p>
                You have been logged out.
            </p>
        </c:if>
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button type="submit" class="btn">Log in</button>
    </form>
</body>
</html>
<!-- 官网：
1 A POST to the /login URL will attempt to authenticate the user
2 If the query parameter error exists, authentication was attempted and failed
3 If the query parameter logout exists, the user was successfully logged out
4 The username must be present as the HTTP parameter named username
5 The password must be present as the HTTP parameter named password
6 We must the section called “Include the CSRF Token” To learn more read the Section 10.6, “Cross Site Request Forgery (CSRF)” section of the reference
-->