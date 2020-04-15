<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="data" type="ru.levelup.web.RegistrationFormData" scope="request" />
<%--<jsp:useBean id="verifiedUserName" type="java.lang.String" scope="session" />--%>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
<%--@elvariable id="form" type="ru.levelup.web.RegistrationForm"--%>
    <form:form modelAttribute="form" action="register" method="post" enctype="application/x-www-form-urlencoded">
        <security:csrfInput/>
        <p>
            <label>
                Login:
                <form:input type="text" path="login" />
            </label>

            <form:errors path="login" cssStyle="color: red" />
        </p>
        <p>
            <label>
                Password:
                <form:input type="password" path="password" />
            </label>

            <form:errors path="password" cssStyle="color: red" />
        </p>
        <p>
            <label>
                Group:
                <form:select path="selectedGroupName">
                    <c:forEach items="${data.groups}" var="group">
                        <option value="${group.name}">${group.name}</option>
                    </c:forEach>
                </form:select>
            </label>

            <form:errors path="selectedGroupName" cssStyle="color: red" />
        </p>
        <p>
            <input type="submit">
        </p>
    </form:form>
</body>
</html>
