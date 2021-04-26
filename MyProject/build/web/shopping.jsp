<%-- 
    Document   : shoppping
    Created on : Apr 19, 2021, 7:49:00 AM
    Author     : Admin
--%>

<%@page import="hungld.user.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flower Shop</title>
    </head>
    <body>
        <h1>
            <font color="blue">Welcome to my Flowers Shop !</font>
        </h1>

        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
        
        <c:if test="${loginUser eq null}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${loginUser.getRoleID() ne 'user'}">
            <c:redirect url="login.html"/>
        </c:if>
        
        <div>
            <font color="red">
            Hello, ${loginUser.fullName}
            </font> 
        </div> 

        <form action="MainController">
            <input type="submit" value="Logout" name="action" />
        </form> <br>

        <c:set var="result" value="${requestScope.LIST_FLOWER}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Flower Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Add to cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="flower" items="${result}" varStatus="counter" >
                        <c:if test="${flower.quantity > 0}">
                        <form action="MainController">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${flower.productID}
                                    <input type="hidden" name="productID" value="${flower.productID}" />
                                </td>
                                <td>
                                    ${flower.productName}
                                </td>
                                <td>
                                    ${flower.quantity}
                                </td>
                                <td>
                                    ${flower.price}
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Add"/> 
                                </td>
                            </tr>
                        </form>
                    </c:if>
                </c:forEach>
            </tbody>
        </table> <br>
        <form action="MainController">
            <input type="submit" name="action" value="View"/>
        </form> <br>
        <c:set var="message" value="${requestScope.MESSAGE}"/>
        <h2>
            ${message}
        </h2>
    </c:if>
    <c:if test="${empty result}">
        <h2>
            No Flowers Available Now !!!
        </h2>
    </c:if>

    <%--
    <form action="MainController">
        Choose your drink:
        <select name="cmbTea">
            <option value="T01-Matcha Tea-50">Matcha Tea</option>
            <option value="T02-Fresh Coffee-40">Fresh Coffee</option>
            <option value="T03-Bac Siu-20">Bac Siu</option>
            <option value="T04-Pink Tea-90">Pink Tea</option>
        </select> 
        
        <input type="submit" name="action" value="Add"/> 
        <input type="submit" name="action" value="View"/> <br>    
        
        <c:set var="message" value="${requestScope.MESSAGE}"/>
        <h2>
            ${message}
        </h2>
        
    </form>
    --%>
</body>
</html>
