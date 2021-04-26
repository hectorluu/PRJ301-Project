<%-- 
    Document   : update
    Created on : Apr 22, 2021, 9:39:30 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
    
        <c:if test="${loginUser eq null}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${loginUser.getRoleID() ne 'AD'}">
            <c:redirect url="login.html"/>
        </c:if>
 
        <h1>
            <font color="blue">Update Information</font>
        </h1>
        <form action="MainController" method="POST">
            <c:set var="errors" value="${requestScope.ERROR}"/>

            User ID: <input type="text" name="userID" value="${param.userID}" readonly=""/><br>
            <font color="red">${errors.userIDError}</font> <br>
            Full name: <input type="text" name="fullName" value="${param.fullName}" required=""/> (5 - 50 characters) <br>
            <font color="red">${errors.fullnameError}</font> <br>
            Role ID: <input type="text" name="roleID" value="${param.roleID}" required=""/> (2 - 10 characters) <br>
            <font color="red">${errors.roleIDError}</font> <br>
            Old Password: <input type="password" name="oldPassword" required=""/> <br>
            <font color="red">${errors.passwordError}</font> <br>
            New Password: <input type="password" name="newPassword" required=""/> <br>
            <font color="red">${errors.newPasswordError}</font> <br>
            Confirm: <input type="password" name="confirm" required=""/> <br>
            <font color="red">${errors.confirmError}</font> <br>

            <input type="hidden" name="search" value="${param.search}"/>
            <input type="submit" name="action" value="Confirm"/>
        </form> <br> 
        <a href="search.jsp">Return to Search Page</a>
    </body>
</html>
