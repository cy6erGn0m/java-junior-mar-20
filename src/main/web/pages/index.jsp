<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title></title>
    <script type="text/javascript" src="scripts/index.js"></script>
  </head>
  <body onload="loadUserStat()">
    <c:if test="${not empty username}">
    <p>
      ${username}
    </p>
    </c:if>

    <c:choose>
      <c:when test="${empty username}">
        <a href="login-page">Login</a>
        <a href="user/register">Register</a>
      </c:when>
      <c:otherwise>
        <p>
            Hello, ${username}.
        </p>
      </c:otherwise>
    </c:choose>

  <p>Title: <span id="title">...</span></p>
  <p>Users count: <span id="users-count">...</span></p>
  <p>Some user: <span id="user-login">...</span></p>
  </body>
</html>
