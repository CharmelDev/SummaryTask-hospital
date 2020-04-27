<%@page import="ua.nure.biletska.db.entity.Category"%>
<%@page import="ua.nure.biletska.db.DAO.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% CategoryDAO category = new CategoryDAO();
	Category cat = category.getCategoryById(1);
	String name = cat.getCategoryName();
	pageContext.setAttribute("categoryName", name);
%>

	<p>${categoryName}
	<p>
</body>
</html>