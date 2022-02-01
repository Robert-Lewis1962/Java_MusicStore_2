<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product Maintenance</title>
	<link rel="stylesheet" href="styles/main.css">
</head>
<body>
	<h1>Products</h1>

	<table>
		<tr>
			<th style="width:75px">Code</th>
			<th style="width:500px">Description</th>
			<th style="width:75px;text-align:right">Price</th>
            <th></th>
            <th></th>
		</tr>

		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.code}</td>
				<td>${product.description}</td>
				<td style="text-align:right">${product.priceCurrencyFormat}</td>
				<td><a href="editProduct?productCode=${product.code}">Edit</a></td>
				<td><a href="deleteProduct?productCode=${product.code}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<form action="editProduct" method="get">
		<input type="submit" value="Add Product" />
	</form>
</body>
</html>