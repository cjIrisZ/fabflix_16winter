<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope['employee_email']}">
    <c:redirect url="_dashboard" />
</c:if>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fabflix Employee Access</title>
</head>
<body BGCOLOR="#A7DCE0">
<div ALIGN="right"><a href="employee_logout">Logout</a></div>
<H1 ALIGN="CENTER">Employee Dashboard</H1>

	<hr>
	<div ALIGN="center">
	<FORM ACTION="InsertStar"
      	  METHOD="GET">
      	  	<table width="450" border="0" cellspacing="0" cellpadding="0" style="font-size: small">
                <tbody><tr>
                <td width="15">&nbsp;</td><td>Insert a Star:</td><td>&nbsp;</td>
                <td width="15">&nbsp;</td>
                  <td><input name="first_name" type="text" placeholder="first name" size="20" maxlength="50" required></td>
                  <td width="15">&nbsp;</td>
                  <td><input name="last_name" type="text" placeholder="last name" size="20" maxlength="50"></td>
                  <td width="15">&nbsp;</td></tr>
            	</tbody>
          	</table>
          	${message}
          	<INPUT TYPE="SUBMIT" VALUE="Insert">      <INPUT TYPE = "RESET" VAlUE="Reset">
	</FORM>
	</div>
	
	<hr>
	
	<div ALIGN="center">
	<FORM ACTION="ShowMetaData"
		  METHOD="get">
		  <INPUT TYPE="submit" VALUE="Show moviedb's MetaData" size="40">
	</FORM>
	<div class="container">
		<c:forEach var="item" items="${item_list}">${item}<br/><br/></c:forEach>
	</div>
	</div>
	
	<hr>
	
	<div ALIGN="center">
	<p>Add movie with a genre and a star:</p>
	<FORM ACTION="addMovie"
		  METHOD="get">
		  <table width="450" border="0" cellspacing="0" cellpadding="0" style="font-size: small">
                <tbody><tr>
                <td width="15">&nbsp;</td>
                            <td width="15">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td width="15">&nbsp;</td>
                            <td width="145"><div align="left">Title:</div></td>
                            <td><input name="movie_title" type="text" size="42" maxlength="100"></td>
                          </tr>
                          <tr>
                            <td width="15"><div class="tinyspacer">&nbsp;</div></td>
                            <td width="145"><div class="tinyspacer">&nbsp;</div></td>
                            <td><div class="tinyspacer">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td width="15">&nbsp;</td>
                            <td width="145"><div align="left">Year:</div></td>
                            <td><input name="movie_year" type="text" size="42" maxlength="4"></td>
                          </tr>
                          <tr>
                            <td width="15"><div class="tinyspacer">&nbsp;</div></td>
                            <td width="145"><div class="tinyspacer">&nbsp;</div></td>
                            <td><div class="tinyspacer">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td width="15">&nbsp;</td>
                            <td width="145"><div align="left">Director:</div></td>
                            <td><input name="director" type="text" size="42" maxlength="64"></td>
                          </tr>
                          <tr>
                            <td width="15"><div class="tinyspacer">&nbsp;</div></td>
                            <td width="145"><div class="tinyspacer">&nbsp;</div></td>
                            <td><div class="tinyspacer">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td width="15">&nbsp;</td>
                            <td width="145"><div align="left">Genre:</div></td>
                            <td><input name="genre" type="text" size="42" maxlength="32"></td>
                          </tr>
                          <tr>
                            <td width="15"><div class="tinyspacer">&nbsp;</div></td>
                            <td width="145"><div class="tinyspacer">&nbsp;</div></td>
                            <td><div class="tinyspacer">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td width="15">&nbsp;</td>
                            <td width="145"><div align="left">Star's First Name:</div></td>
                            <td><input name="first_name" type="text" size="42" maxlength="32"></td>
                          </tr>
                          <tr>
                            <td width="15"><div class="tinyspacer">&nbsp;</div></td>
                            <td width="145"><div class="tinyspacer">&nbsp;</div></td>
                            <td><div class="tinyspacer">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td width="15">&nbsp;</td>
                            <td width="145"><div align="left">Star's Last Name:</div></td>
                            <td><input name="last_name" type="text" size="42" maxlength="32"></td>
                          </tr>
                          <tr>
                            <td width="15"><div class="tinyspacer">&nbsp;</div></td>
                            <td width="145"><div class="tinyspacer">&nbsp;</div></td>
                            <td><div class="tinyspacer">&nbsp;</div></td>
                          </tr>
                        </tbody>
          	</table>
          	${addMovieMessage}
          	<INPUT TYPE="SUBMIT" VALUE="Add movie">      <INPUT TYPE = "RESET" VAlUE="Reset">
	</FORM>
	</div>

</body>
</html>