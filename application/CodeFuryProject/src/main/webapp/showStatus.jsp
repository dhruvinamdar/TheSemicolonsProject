<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400&display=swap" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="showStatus">
    	<label>Number of Products Imported</label>
    	<input type="text" value="${status.noOfProductsImported}" readonly>
    	<label>Status</label>
    	<input type="text" value="${status.status}" readonly>
    </div>
</body>
</html>