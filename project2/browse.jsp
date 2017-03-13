<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<TITLE>Fabflix</TITLE>
<a href="main.html">Home</a>&nbsp;&nbsp;&nbsp;<a href="search.jsp">Search</a>&nbsp;&nbsp;&nbsp;<a href="browse.jsp">Browse</a>&nbsp;&nbsp;&nbsp;
	<body bgcolor="#A7DCE0">

                <div class="container">
                <h1 align="center">Browse</h1>
		<h3 align="center">Please choose GENRE and/or LETTER to browse movies!</h3>
                <table class="table table-hover">
                <tbody>
                        <form method="get" action="browseMovieList">
                        <tr><th>Genre:</th><td>
                                <input type="radio" id="genre_label" name="genre" value="All" checked = "checked">
                                <label for="genre_label">All</label>
                                <c:forEach var="genre" items="${genre_list}">${genre}|</c:forEach></td></tr>
                        
			<tr>
   				<td width="15"><div class="tinyspacer">&nbsp;</div></td>
   				<td width="145"><div class="tinyspacer">&nbsp;</div></td>
   				<td><div class="tinyspacer">&nbsp;</div></td>
			</tr>

			<tr><th>Letter:</th>
                        <td>
                                <input type="radio" id="letter_label" name="letter" value="All"  checked = "checked">
                                <label for="letter_label">All</label>&nbsp;&nbsp;&nbsp;
                                <c:set var="letter" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9" />
                                <c:forTokens var="l" items="${letter}" delims=",">
                                <input type="radio" id="letter_label" name="letter" value="${l}">
                                <label for="letter_label">${l}</label>&nbsp;&nbsp;&nbsp;
                                </c:forTokens>
                        </td></tr>
                        <tr/><td align="center" colspan="2"><button class="btn btn-default" type="submit">Browse</button></td></tr>
                        </form>
                </tbody>
                </table>
                </div>


        </body>

</html>
