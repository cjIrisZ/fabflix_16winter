<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope['email']}">
    <c:redirect url="index.jsp" />
</c:if>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Fabflix</title>
	</head>

	<body>
		<div class="container">
                        <form method="get" action="displayMovieB">
                                Sort by:&nbsp;&nbsp;
                                <input type="submit" class="btn btn-default" name="sortbytitleA" value="Title" id="sortbytitleA"/>&nbsp;<label for="sortbytitleA" class="glyphicon glyphicon-sort-by-alphabet"></label>&nbsp;&nbsp;
                                <input type="submit" class="btn btn-default" name="sortbytitleD" value="Title"/>&nbsp;<label for="sortbytitleD" class="glyphicon glyphicon-sort-by-alphabet-alt"></label>&nbsp;&nbsp;
                                <input type="submit" class="btn btn-default" name="sortbyyearA" value="Year"/>&nbsp;<label for="sortbyyearA" class="glyphicon glyphicon-sort-by-order"></label>&nbsp;&nbsp;
                                <input type="submit" class="btn btn-default" name="sortbyyearD" value="Year"/>&nbsp;<label for="sortbyyearD" class="glyphicon glyphicon-sort-by-order-alt"></label>
                                <input type="hidden" name="genre" value="${genre}"/>
                                <input type="hidden" name="letter" value="${letter}"/>
                        </form>
                </div>
                <hr>

                <div class="container">
                          <span class="error"><center>${notFound}</center></span>
                          <c:forEach var="item" items="${item_list}">${item}<br/><hr><br/></c:forEach>
                </div>

                <div class="container">
                        <center>
                        <form method="get" action="displayMovieB">
                        <nav>
                          <ul class="pagination">
                            <li><input type="submit" class="btn btn-default ${disabledprev}" name="prev" value="&laquo;&nbsp;Previous"></li>
                                <li><input type="submit" class="btn btn-default ${disablednext}" name="next" value="Next&nbsp;&raquo;"></li>
                                <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Results Per Page: &nbsp;</li>
                                <li><input type="submit" class="btn btn-default ${disabledpage}" name="perpage5" value="5"></li>
                                <li><input type="submit" class="btn btn-default ${disabledpage}" name="perpage10" value="10"></li>
                                <li><input type="submit" class="btn btn-default ${disabledpage}" name="perpage15" value="15"></li>
                                <li><input type="submit" class="btn btn-default ${disabledpage}" name="perpage20" value="20"></li>
                                <li><input type="submit" class="btn btn-default ${disabledpage}" name="perpage25" value="25"></li>
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
