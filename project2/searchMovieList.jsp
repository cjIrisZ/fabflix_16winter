<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Fabflix</title>

	</head>

<a href="main.html">Home</a>&nbsp;&nbsp;&nbsp;<a href="search.jsp">Search</a>&nbsp;&nbsp;&nbsp;<a href="browse.jsp">Browse</a>&nbsp;&nbsp;&nbsp;	
<hr>
	<body bgcolor="#A7DCE0">
		<div class="container">
			<form method="get" action="searchMovieList">
				Sort by:&nbsp;&nbsp;
				<input type="submit" class="btn btn-link" name="sortbytitleA" value="Title Ascending" id="sortbytitleA"/>
				<input type="submit" class="btn btn-link" name="sortbytitleD" value="Title Descending"/>
				<input type="submit" class="btn btn-link" name="sortbyyearA" value="Year Ascending"/>
				<input type="submit" class="btn btn-link" name="sortbyyearD" value="Year Descending"/>
				<input type="hidden" name="title" value="${title}"/>
				<input type="hidden" name="year" value="${year}"/>
				<input type="hidden" name="director" value="${director}"/>
				<input type="hidden" name="first_name" value="${first_name}"/>
				<input type="hidden" name="last_name" value="${last_name}"/>			
			</form>
		</div>
		<hr>
		<div class="container">
			  <span class="error"><center>${notFound}</center></span>
			  <c:forEach var="item" items="${item_list}">${item}<br/><hr><br/></c:forEach>
		</div>
		<div class="container">
			<center>
			<form method="get" action="searchMovieList">
			<nav>
			  <ul class="pagination">
			    <input type="submit" class="btn btn-default ${disabledprev}" name="prev" value="&laquo;&nbsp;Previous">
				<input type="submit" class="btn btn-default ${disablednext}" name="next" value="Next&nbsp;&raquo;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Results Per Page: &nbsp;
				<input type="submit" class="btn btn-link ${disabledpage}" name="perpage5" value="5">
				<input type="submit" class="btn btn-link ${disabledpage}" name="perpage10" value="10">
				<input type="submit" class="btn btn-link ${disabledpage}" name="perpage15" value="15">
				<input type="submit" class="btn btn-link ${disabledpage}" name="perpage20" value="20">
				<input type="submit" class="btn btn-link ${disabledpage}" name="perpage25" value="25">
				<input type="hidden" name="title" value="${title}"/>
				<input type="hidden" name="year" value="${year}"/>
				<input type="hidden" name="director" value="${director}"/>
				<input type="hidden" name="first_name" value="${first_name}"/>
				<input type="hidden" name="last_name" value="${last_name}"/>
				<input type="hidden" name="genre" value="${genre}"/>
				<input type="hidden" name="letter" value="${letter}"/>
				<input type="hidden" name="offset" value="${offset}"/>
				<input type="hidden" name="number" value="${number}"/>
			  </ul>
			</nav>
			</form>
			</center>
		</div>	
	</body>
</html>
