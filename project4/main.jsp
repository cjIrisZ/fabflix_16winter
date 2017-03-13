<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope['email']}">
    <c:redirect url="index.jsp" />
</c:if>
<HTML>
<HEAD>
  <TITLE>Fabflix</TITLE>
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script src="autocompleter.js"></script>
	<link rel="stylesheet" 
  	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
</HEAD>

<BODY bgcolor="#A7DCE0">
<CENTER>	
	<H1> Main Page </H1>
     <div class="search-container">
        <div class="ui-widget">
        <form action="FullTextSearch"
		  METHOD="GET">
		  <table><tbody><tr><td>
                <input type="text" id="search" name="search" class="search" />
                </td></tr></tbody></table>
		  <INPUT TYPE="SUBMIT" VALUE="Full-Text Search">
		</form>
        </div>
	 </div>
	<p><a href="search.jsp">Advanced Search</a></p>
    <p><a href="browse.jsp">Browse</a></p>
    <p><a href="shoppingCart.jsp">Shopping Cart</a></p>
</CENTER>
</BODY>
</HTML>
