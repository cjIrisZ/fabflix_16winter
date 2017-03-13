<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fabflix Reports</title>
</head>
<body>
<div>
<p>I used two optimizations:</p>
	<p>1. Single-batch Transaction: when first implemented, I only used prepared statements and SQL Statement; 
		then change some them into Callable statement by calling stored procedures and stored movie_id and star_id in cache for later use</p>
		<p>Stored functions created: Add_Star, Add_Genre</p>
		<p>Also turned off the the auto commit, and commit at the last of the function</p> 
	<p>2. Smart Queries(use java for business logic): when inserting stars_in_movies, I need to search the corresponding movie id and star id. When first implement, I searched in the database each time. To timporve, I stored them in a hashmap and use java to do the logic</p>
<p>these improve the runtime from 92.465 seconds to 46.97 seconds</p>
</div> 
</body>
</html>