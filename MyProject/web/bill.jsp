<%-- 
    Document   : bill.jsp
    Created on : Apr 20, 2021, 9:28:25 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Page</title>
    </head>
    <body>
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

        <a href="viewCart.jsp">Return to your cart</a> <br>

        <h1>
            <font color="blue">Your Order:</font>
        </h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="flower" varStatus="counter" items="${cart.getCart().values()}">
                    <form action="MainController">
                        <tr>
                            <td> 
                                ${counter.count}
                            </td>
                            <td> 
                                ${flower.productID} 
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
                                ${flower.price*flower.quantity}
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table> <br>
        
        <h3>
            Total: ${sessionScope.total}
        </h3>

        <h2>Checkout Information</h2>
        <form action="MainController" method="POST">
            <c:set var="error" value="${requestScope.ERROR_ORDER}"/>

            Full Name :<input type="text" name="fullName" value="${param.fullName}" required=""/>(5 - 50 characters) <br>
            <font color="red">${error.usernameLengthError}</font><br>
            Address :<input type="text" name="address" value="${param.address}" required=""/> (2-100 characters)<br>
            <font color="red">${error.addressLengthError}</font><br>
            Phone :<input type="text" name="phone" value="${param.phone}" required=""/> (10-20 numbers)<br>
            <font color="red">${error.phoneLengthError}</font><br>
            <font color="red">${error.phoneFormatError}</font><br>
            
            <%-- gui them bien total --%>
            <input type="hidden" name="total" value="${sessionScope.total}"/>
            <input type="submit" value="Confirm Order" name="action"/>
        </form>
    </c:if>

    <c:if test="${empty cart}">
        <h1>Your cart is empty !</h1>
        <a href="ItemsLoadController">Return to shopping page</a>
    </c:if>  <br/>

</body>
</html>
