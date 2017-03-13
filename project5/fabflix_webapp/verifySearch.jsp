<html>
        <head>		<title>>Search Verification</title>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        </head>

        <%@ page import="java.*" %>

        <%
        if (request.getParameter("year").length()==0
                        && request.getParameter("title").length()==0
                        && request.getParameter("director").length()==0
                        && request.getParameter("first_name").length()==0
                        && request.getParameter("last_name").length()==0) {
        %> <jsp:forward page="search.jsp" /> <%} else {
        %> <jsp:forward page="searchMovieList" /> <%} %>

        </body>
        <body bgcolor="#A7DCE0">
</html>

