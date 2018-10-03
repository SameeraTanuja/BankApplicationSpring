<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Transfer</title>
</head>
<body>
<%@ include file="header.jsp" %>

	<form action="fundTransfer.do" method="post">
		
		<label>Payee Account number:</label>
		<input type="number" name="payeeAccountNumber" size=15 required>
		<label>Amount:</label>
		<input type="number" name="amount" size=15 required>
		<br>
		<input type="submit" value="Pay">
	</form>
</body>
</html>>