<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<HTML>
<HEAD>
  <TITLE>Fabflix</TITLE>
  <!-- <script src='https://www.google.com/recaptcha/api.js'></script>  -->
</HEAD>

<BODY BGCOLOR="#A7DCE0">
<H1 ALIGN="CENTER">Please Log In</H1>
<div class="container">
<CENTER>
<div class="jumbotron vertical-center horizontal-center" style="border-radius: 25px; width: 500px; margin-top: 100px; height: 400px; background: rgba(255, 255, 255, 0.7);">
<FORM ACTION="login"
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
   <td><input name="email" type="email" size="42" maxlength="100" required></td>
</tr>
<tr>
   <td width="15"><div class="tinyspacer">&nbsp;</div></td>
   <td width="145"><div class="tinyspacer">&nbsp;</div></td>
   <td><div class="tinyspacer">&nbsp;</div></td>
</tr>
<tr>
   <td width="15">&nbsp;</td>
   <td width="145"><div align="left">password:</div></td>
   <td><input name="password" type="password" size="42" maxlength="100" required></td>
</tr>
<tr>
   <td width="15"><div class="tinyspacer">&nbsp;</div></td>
   <td width="145"><div class="tinyspacer">&nbsp;</div></td>
   <td><div class="tinyspacer">&nbsp;</div></td>
</tr>
</table>
<!-- 
	<div class="g-recaptcha" data-sitekey="6LfUKRgTAAAAAOnhl-DnujMK9rwKatAkxlj8Ka6f"></div>
   	<td width="145"><div class="tinyspacer">&nbsp;</div></td> -->
    <INPUT TYPE="SUBMIT" VALUE="Login">
</FORM>
</center>
</div>
</BODY>
</HTML>
