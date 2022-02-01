<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Franklin Clothing - Your Cart</title>
    <link href="styles/main.css" rel="stylesheet" />
</head>
<body>
    <h1>Your Cart</h1>
    <table>
        <tr>
            <th style="width:200px">Description</th>
            <th style="width:50px">Price</th>
            <th>Qty</th>
            <th></th>
        </tr>
        
        <c:forEach var="cartEntry" items="${cart}">
            <tr>
                <td>${cartEntry.product.description}</td>
                <td align="right">${cartEntry.product.priceCurrencyFormat}</td>
                <td><input type="text" size="3" value="${cartEntry.qty}"></input></td>
                <td>
                   <form>
                       <input type="submit" value="Update"></input>
                   </form>
                </td>
            </tr>
        </c:forEach>
        
        <tr class="no-border">
            <td>Total:</td>
            <td>${totalPrice}</td>
            <td colspan="2"></td>
        </tr>
        
        <tr class="no-border">
            <td align="right" colspan="3">
                <form action="searchResults">
                    <input type="submit" value="Continue Shopping"></input>
                </form>
            </td>
            <td><form><input type="submit" value="Checkout"></input></form></td>
        </tr>
    </table>
</body>
</html>