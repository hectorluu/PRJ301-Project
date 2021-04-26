<%-- 
    Document   : insert
    Created on : Apr 19, 2021, 1:04:20 AM
    Author     : Admin
--%>

<%@page import="hungld.user.UserInsertErrorDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New User</title>
    </head>
    <body>
        <h1>
            <font color="blue">Input User's Information</font>
        </h1>
        <c:set var="error" value="${sessionScope.UserInsertErrorDTO}"/>
        
        <form action="MainController" method="POST">
            User ID: <input type="text" name="userID" required=""/></br>
            <font color="red">
                ${requestScope.ERROR_USER.userIDError} <br>
            </font>
            Full Name: <input type="text" name="fullname" required=""/></br>
            <font color="red">
                ${requestScope.ERROR_USER.fullnameError} <br>
            </font>
            Role ID: <input type="text" name="roleID" required=""/></br>
            <font color="red">
                ${requestScope.ERROR_USER.roleIDError} <br>
            </font>
            Password: <input type="password" name="password" required=""/></br>
            <font color="red">
                ${requestScope.ERROR_USER.passwordError} <br>
            </font>  
            Confirm: <input type="password" name="confirm" required=""/></br>
            <font color="red">
                ${requestScope.ERROR_USER.confirmError} <br>
            </font>  
            
            <input type="submit" name="action" value="Insert"/>
            <input type="submit" value="Reset"/></br>
        </form> <br>
        
        <a href="login.html">Have an account? Login now</a>
    </body>
</html>
