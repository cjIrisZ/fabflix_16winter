<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<TITLE>Fabflix</TITLE>

<BODY bgcolor="#A7DCE0">
<a href="main.jsp">Home</a>&nbsp;&nbsp;&nbsp;<a href="search.jsp">Search</a>&nbsp;&nbsp;&nbsp;<a href="browse.jsp">Browse</a>&nbsp;&nbsp;&nbsp;
<H1 ALIGN="CENTER">SEARCH</H1>
<H3 ALIGN="CENTER">Please enter one or more boxes at following</H3>
<FORM ACTION="verifySearch.jsp"
      METHOD="GET">
<CENTER>
<table width="400" border="0" cellspacing="0" cellpadding="0" style="font-size: small">
                          <tbody><tr>
                            <td width="15">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td width="15">&nbsp;</td>
                            <td width="145"><div align="left">Title:</div></td>
                            <td><input name="title" type="text" size="42" maxlength="100"></td>
                          </tr>
                          <tr>
                            <td width="15"><div class="tinyspacer">&nbsp;</div></td>
                            <td width="145"><div class="tinyspacer">&nbsp;</div></td>
                            <td><div class="tinyspacer">&nbsp;</div></td>
                          </tr>
                          <tr>
                            <td width="15">&nbsp;</td>
                            <td width="145"><div align="left">Year:</div></td>
                            <td><input name="year" type="text" size="42" maxlength="4"></td>
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
                          </tr>
                        </tbody></table>
 <INPUT TYPE="SUBMIT" VALUE="Search">      <INPUT TYPE = "RESET" VAlUE="Reset">
  </CENTER>
</FORM>

</BODY>
</html>
