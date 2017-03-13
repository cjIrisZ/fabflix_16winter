PART I: sql file
1. Run command: 
	mysql -u root -p -D moviedb < createtable.sql
and enter the password to create the database.
2. Run the data.sql with the same format to populate the tables, then you are ready for thesecond part to run the JDBC file:)



PART II: JDBC file Compile and Run

Method 1 (Using Eclipse)

	1.Open Eclipse
	2.Create a project and add JDBC_proj.java source file
	3.Download MySQL Connector zip file from https://dev.mysql.com/downloads/connector/j/ and decompress that, then add .jar file into the external archive library
	4.Click the RUN buttom locate at the tool bar(Short Cut: CTRL+F11)

	4.At the Concole window, follow the instruction:
		(1) Enter the username of your database
		(2) Enter the password of your database
		(3) Select what you want to do

Method 2 (Using Command Line)
	1.Use command 
		javac JDBC_proj.java 
	  to compile the file 
	2.Download the MySQL Connector and remember the path; run
		java -classpath “pathToConnector” JDBC_proj
	3.Interact with the interface by:
		(1) Enter the username of your database
		(2) Enter the password of your database
		(3) Select what you want to do