<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fabflix Report</title>
</head>
<body>
<p>Task1 load balancing:</p>
<p>I first created three new instances (because the old instance for project 3 and 4 were not open at the time). 
Then setting them up according to the instructions on the website: first make mySQL replication for master instance and slave instance, 
and then test TomcatTest and Session servlet on each of them. Then, set up the front end server and test the two samples for the front end instance.
After making sure all of them work (sticky session, write only only open for master instance), I started to work on fabflix. Before copy war file to the master and slace server, I first deleted the https constraints and reCaptcha.
Then based on the experience from two samples, it is not hard to make that work.</p>
<hr>
<p>Task2 Andriod App</p>
<p>To finish the Android app, I first install the Android studio and went over the tutorial links about Android programming basics, UI, and life cycles on the website.
Then, downloaded the sample fabflix app and played with that for a while. Then started to work on my own fabflix android app.
I first created two new servlets to make sure the output can be shown perfectly on android devices, then created two activities, for login and fulltext search.
Each of them has a java class and an xml file for display.
It took me a while to make login work and to show the search results.</p>
</body>
</html>