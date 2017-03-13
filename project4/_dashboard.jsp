<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fabflix Employee Access</title>
</head>
<BODY BGCOLOR="#A7DCE0">
<H1 ALIGN="CENTER">Employee Log In</H1>
<div class="container">
<CENTER>
<div class="jumbotron vertical-center horizontal-center" style="border-radius: 25px; width: 500px; margin-top: 100px; height: 400px; background: rgba(255, 255, 255, 0.7);">
<FORM ACTION="employee_login"
      METHOD="POST">
${message}
<table>
<tr>
   <td width="15"><div class="tinyspacer">&nbsp;</div></td>
   <td width="145"><div class="tinyspacer">&nbsp;</div></td>
   <td><div class="tinyspacer">&nbsp;</div></td>
</tr>
<tr>
   <td width="15">&nbsp;</td>
   <td width="145"><div align="left">email:</div></td>
   <td><input name="employee_email" type="email" placeholder="user@uci.edu" size="42" maxlength="100" required></td>
</tr>
<tr>
   <td width="15"><div class="tinyspacer">&nbsp;</div></td>
   <td width="145"><div class="tinyspacer">&nbsp;</div></td>
   <td><div class="tinyspacer">&nbsp;</div></td>
</tr>
<tr>
   <td width="15">&nbsp;</td>
   <td width="145"><div align="left">password:</div></td>
   <td><input name="employee_password" type="password" placeholder="fabflix" size="42" maxlength="100" required></td>
</tr>
<tr>
   <td width="15"><div class="tinyspacer">&nbsp;</div></td>
   <td width="145"><div class="tinyspacer">&nbsp;</div></td>
   <td><div class="tinyspacer">&nbsp;</div></td>
</tr>
</table>
    <INPUT TYPE="SUBMIT" VALUE="Login">
</FORM>
</center>
</div>
</BODY>
</html>