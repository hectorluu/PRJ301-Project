<%-- 
    Document   : serach
    Created on : Jun 14, 2020, 1:54:29 PM
    Author     : natton
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="hungld.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        
        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
   
        <c:if test="${loginUser eq null}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${loginUser.getRoleID() ne 'AD'}">
            <c:redirect url="login.html"/>
        </c:if>
    
        <div>
            <font color="red">
            Welcome, ${loginUser.fullName}
            </font>
        </div>

        <form action="MainController">
            <input type="submit" value="Logout" name="action" />
        </form> 

        <h1>
            <font color="blue">Search Page</font>
        </h1>
        <form action="MainController">
            Search: <input type="text" name="search"
                           value="${param.search}"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <c:set var="searchValue" value="${param.search}"/>    

        <c:if test="${not empty searchValue}">
            <c:if test="${requestScope.LIST_USER != null}">
                <c:if test="${not empty requestScope.LIST_USER}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>User ID</th>
                                <th>Full Name</th>
                                <th>Role ID</th>
                                <th>Password</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${user.userID}</td>
                                    <td>${user.fullName}</td>
                                    <td>${user.roleID}</td>
                                    <td>${user.password}</td>
                                    <td>
                                        <c:url value="MainController" var="delete">
                                            <c:param name="action" value="Delete"/>
                                            <c:param name="userID" value="${user.userID}"/>
                                            <c:param name="search" value="${param.search}"/>
                                        </c:url>
                                        <a href="${delete}">Delete</a>
                                    </td>
                                    <td>
                                        <input type="hidden" name="userID" value="${user.userID}"/>
                                        <input type="hidden" name="fullName" value="${user.fullName}"/>
                                        <input type="hidden" name="roleID" value="${user.roleID}"/>
                                        <input type="hidden" name="search" value="${param.search}"/>
                                        <input type="submit" name="action" value="Update"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table> <br>

                <c:set var="error" value="${requestScope.EXIST}"/>
                <c:if test="${error eq true}">
                    <font color="red">
                    CANT DELETE YOURSELF ! 
                    </font> <br>
                </c:if>
            </c:if>
        </c:if>

        <c:if test="${empty requestScope.LIST_USER}">
            <h2>
                No record is matched !!!
            </h2>
        </c:if>
        </c:if>


</body>
</html>
