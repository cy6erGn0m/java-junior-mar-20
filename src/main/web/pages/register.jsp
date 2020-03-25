<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="form" type="ru.levelup.web.RegistrationForm" scope="request" />
<%--<jsp:useBean id="verifiedUserName" type="java.lang.String" scope="session" />--%>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <form action="register" method="post" enctype="application/x-www-form-urlencoded">
        <p>
            <label>
                Login:
                <input type="text" name="login" value="${form.login}">
            </label>
        </p>
        <p>
            <label>
                Password:
                <input type="password" name="password" value="${form.password}">
            </label>
        </p>
        <p>
            <label>
                Group:
                <select name="group">
                    <c:forEach items="${form.groups}" var="group">
                        <option value="${group.name}">${group.name}</option>
                    </c:forEach>
                </select>
            </label>
        </p>
        <p>
            <input type="submit">
        </p>
    </form>
</body>
</html>
