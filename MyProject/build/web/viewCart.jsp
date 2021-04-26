<%-- 
    Document   : view
    Created on : Apr 19, 2021, 9:26:09 AM
    Author     : Admin
--%>

<%@page import="hungld.flower.FlowerDTO"%>
<%@page import="hungld.cart.CartDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}"/>
        
        <c:if test="${loginUser eq null}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${loginUser.getRoleID() ne 'user'}">
            <c:redirect url="login.html"/>
        </c:if>
        
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <h1>
                <font color="blue">Your Selected:</font>
            </h1>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Remove</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0" scope="session"/>
                    <c:forEach var="flower" varStatus="counter" items="${cart.getCart().values()}">
                    <form action="MainController" method="POST">
                        <tr>
                            <td> 
                                ${counter.count}
                            </td>
                            <td> 
                                ${flower.productID} 
                                <input type="hidden" name="productID" value="${flower.productID}"/>
                            </td>
                            <td> 
                                ${flower.productName}
                            </td>
                            <td>
                                <input type="number" name="quantity" value="${flower.quantity}" required="" min="0"/>  
                            </td>
                            <td> 
                                ${flower.price}
                            </td>
                            <td>
                                ${flower.price*flower.quantity}
                                <c:set var="total" value="${total + flower.price*flower.quantity}" scope="session"/>
                            </td>
                            <td> 
                                <input type="submit" name="action" value="Remove"/>
                            </td>
                            <td> 
                                <input type="submit" name="action" value="Edit"/>
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
            </tbody>
    </table> <br>
   
    <h2>
        Total: ${total}
    </h2>
    <a href="ItemsLoadController">Return to shopping page</a> <br> <br>
        <form action="MainController">
            Submit your order: <input type="submit" name="action" value="Order"/>
        </form> <br>
    </c:if>

    <c:if test="${empty cart}">
        <h1>Your cart is lost!!!</h1>
        <a href="ItemsLoadController">Return to shopping page</a>
    </c:if>  <br/>
</body>
</html>
