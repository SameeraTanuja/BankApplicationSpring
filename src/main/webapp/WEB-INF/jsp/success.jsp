<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
</head>
<body>
<%@include file="header.jsp" %>
<c:if test="${requestScope.success == true}">
Success
</c:if>
 <c:if test="${requestScope.success != true}"> 
Failed: ${requestScope.success}


</c:if>  
</body>
</html>