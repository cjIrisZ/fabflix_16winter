<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>FabFlix</title>
	</head>

	
	<body>		
		<div class="container">
 			<h2>Results: </h2> 
			<table class="table">
				<thead>
				      <tr>
				        <th>Movie ID</th>
				        <th>Title</th>
				        <th>Year</th>
				        <th>Director</th>
				        <th>Stars</th>
				        <th>Genres</th>
				      </tr>
				 </thead>
				 <tbody><c:forEach var="item" items="${info}">${item}</c:forEach></tbody>
			 </table>
		</div>
	
	</body>
</html>