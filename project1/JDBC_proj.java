// CS122b Project1
// Created by Iris Zeng, Cheryl Yang and Yuren Shen

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class JDBC_proj {

	public static void main(String[] args)  throws Exception{

		// Print out the greeting message
		System.out.println("Welcome to Our CS122b Project! \n This database interface is made by Iris Zeng, Cheryl Yang and Yuren Shen.");

		Scanner user_input = new Scanner(System.in);

		// Incorporate mySQL driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.out.print("Please enter the username of your database (root):");
		String dbusr = user_input.next();
		System.out.print("Please enter the password of your database (shenzengyang):");
		String dbpw = user_input.next();
		//Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useUnicode=true&characterEncoding=utf-8&useSSL=false",dbusr, dbpw);

		int exitcode = 0;
		while(true){
			System.out.print("\nPlease select an option:\n"
					+ "1. Print the movies featuring a given star\n"
					+ "2. Insert a new star\n"
					+ "3. Insert a customer\n"
					+ "4. Delete a customer\n"
					+ "5. Provide the metadata of the database\n"
					+ "6. Enter a valid SELECT/UPDATE/INSERT/DELETE SQL command\n"
					+ "7. Exit the menu (to enter new usrname and password)\n"
					+ "8. Exit the program\n"
					+ "Please enter the number you choose:");
			int option = user_input.nextInt();
			switch(option){
			case 1:
				PrintMovies(connection);
				break;
			case 2:
				InsertStar(connection);
				break;
			case 3:
				InsertCustomer(connection);
				break;
			case 4:
				DeleteCustomer(connection);
				break;
			case 5:
				PrintMetadata(connection);
				break;
			case 6:
				ExecuteValidQuery(connection);
				break;
			case 7:
				System.out.print("Please enter the username of your database (root):");
				dbusr = user_input.next();
				System.out.print("Please enter the password of your database (shenzengyang):");
				dbpw = user_input.next();
				break;
			case 8:
				System.out.println("Thank you for using, Bye!");
				exitcode = 1;
				connection.close();
				break;
			}
			if (exitcode == 1) break;

			connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useUnicode=true&characterEncoding=utf-8&useSSL=false",dbusr, dbpw);
		}
		user_input.close();
	}

	// All movie attributes should appear, labeled and neatly arranged; 
	// the star can be queried via first name and/or last name or by ID. 
	// First name and/or last name means that a star should be queried by both 
	// a) first name AND last name b) first name or last name.
	public static void PrintMovies(Connection c) throws SQLException
	{
		Statement select = c.createStatement();
		Scanner s = new Scanner(System.in);
		System.out.print("\nPlease select a mode you want to use:\na. First AND Last name\nb. First name only\nc. Last name only\nd. ID\n");
		String option = s.next();
		String query = "select * from movies m, stars s, stars_in_movies sim where m.id = sim.movie_id and sim.star_id = s.id and";
		String firstname,lastname;

		// Get the option and form the query
		switch(option){
		case "a":
			System.out.print("First name: ");
			firstname = s.next();
			System.out.print("Last name: ");
			lastname = s.next();
			query = query + " s.first_name = \""+firstname+ "\" and s.last_name = \""+lastname+"\"";
			break;
		case "b":
			System.out.print("First name: ");
			firstname = s.next();
			query = query + " s.first_name = \""+firstname+ "\"";
			break;
		case "c":
			System.out.print("Last name: ");
			lastname = s.next();
			query = query + " s.last_name = \""+lastname+ "\"";
			break;
		case "d":
			System.out.print("ID: ");
			int id = s.nextInt();
			query = query + " s.id = " + id;
			break;
		}

		// Execute the query
		ResultSet result = select.executeQuery(query);

		// print out the result
		while (result.next())
		{
			System.out.println("Id = " + result.getInt(1));
			System.out.println("Title = " + result.getString(2));
			System.out.println("Year= " + result.getInt(3));
			System.out.println("Director = " + result.getString(4));
			System.out.println("Banner_url = " + result.getString(5));
			System.out.println("Trailer_url = " + result.getString(6));
			System.out.println();
		}
		c.close();
	}

	// If the star has a single name, add it as his last_name and assign an empty string ("") to first_name
	
	
	/// if the dob and photo_url are not entered by user---- not completed.
	public static void InsertStar(Connection c)throws Exception
	{
		// the mysql insert statement
		String query = " INSERT INTO stars (id, first_name, last_name, dob, photo_url)"
				+ " values (?, ?, ?, ?, ?)";
		// String updateString = "update stars set first_name = ? where id = ?";
		// PreparedStatement updateStars = c.prepareStatement(updateString);

		// create the mysql insert preparedstatement
		Scanner s = new Scanner(System.in);
		System.out.print("\nPlease enter the id of the star: ");
		int id = s.nextInt();
		s.nextLine();
		System.out.print("\nPlease enter the name of the star: ");
		String full_name = s.nextLine();
		int space_index = full_name.lastIndexOf(" ");
		String first_name;
		String last_name;
		if(space_index != -1)
		{
			last_name = full_name.substring(full_name.lastIndexOf(" ")+1);
			first_name = full_name.substring(0, full_name.lastIndexOf(" "));
		}
		else
		{
			first_name = "";
			last_name = full_name;
		}
		System.out.print("\nPlease enter the date of birth of the star. Year(Example: 1985): ");
		int year = s.nextInt();
		System.out.print("\nPlease enter the date of birth of the star. Month(Example: 05): ");
		int month = s.nextInt();
		System.out.print("\nPlease enter the date of birth of the star. Day(Example: 30): ");
		int day = s.nextInt();		

		s.nextLine();
		//java.sql.Date dob = new java.sql.Date(year-1900, month-1, day);

		String strDate = year+"-"+month+"-"+day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(strDate);
       
        java.sql.Date dob = new Date(date.getTime());
		
		System.out.print("\nPlease enter the photo url of the star: ");
		String photo_url = s.nextLine();

		PreparedStatement preparedStmt = c.prepareStatement(query);
		preparedStmt.setInt(1, id);
		preparedStmt.setString(2, first_name);
		preparedStmt.setString(3, last_name);
		preparedStmt.setDate(4, dob);
		preparedStmt.setString(5, photo_url);
		preparedStmt.execute();

		c.close();

	}

	// Do not allow insertion of a customer if his credit card does not exist in the credit card table. The credit card table simulates the bank records. If the customer has a single name, add it as his last_name and assign an empty string ("") to first_name.
	public static void InsertCustomer(Connection c) throws SQLException
	{
		// the mysql insert statement
		String query = " INSERT INTO customers (id, first_name, last_name, cc_id, address, email, password)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		// create the mysql insert preparedstatement

		Scanner s = new Scanner(System.in);
		System.out.print("\nPlease enter the id of the customer: ");
		int id = s.nextInt();
		s.nextLine();
		System.out.print("\nPlease enter the name of the customer: ");
		String full_name = s.nextLine();
		int space_index = full_name.lastIndexOf(" ");
		String first_name;
		String last_name;
		if(space_index != -1)
		{
			last_name = full_name.substring(full_name.lastIndexOf(" ")+1);
			first_name = full_name.substring(0, full_name.lastIndexOf(" "));
		}
		else
		{
			first_name = "";
			last_name = full_name;
		}


		System.out.print("\nPlease enter the credit card id of the customer: ");
		PreparedStatement st = c.prepareStatement(
				"SELECT id from creditcards WHERE id = ?");
		String cc_id = s.nextLine();
		st.setString(1, cc_id);
		ResultSet rs = st.executeQuery();
		if (!rs.next()){
			System.out.print("\nThis is an invalid credit card number. The system will exit to the main menu\n");
		}
		else{

		System.out.print("\nPlease enter the address of the customer: ");
		String address = s.nextLine();

		System.out.print("\nPlease enter the email address of the customer: ");
		String email = s.nextLine();

		System.out.print("\nPlease enter the password of the customer: ");
		String password = s.nextLine();

		PreparedStatement preparedStmt = c.prepareStatement(query);
		preparedStmt.setInt(1, id);
		preparedStmt.setString(2, first_name);
		preparedStmt.setString(3, last_name);
		preparedStmt.setString(4, cc_id);
		preparedStmt.setString(5, address);
		preparedStmt.setString(6,  email);
		preparedStmt.setString(7,  password);
		preparedStmt.execute();

		
		}
		c.close();
	}

	// Delete a customer from the database.
	public static void DeleteCustomer(Connection c) throws SQLException
	{
		String query = "delete from customers where id = ?";
		PreparedStatement preparedStmt = c.prepareStatement(query);
		Scanner s = new Scanner(System.in);
		System.out.print("\nPlease enter the id number of the acount that you are deleting: ");
		int id = s.nextInt();
		s.nextLine();
		preparedStmt.setInt(1, id);
		preparedStmt.execute();

		c.close();
	}

	public static void PrintMetadata(Connection c) throws SQLException
	{
		Statement query = c.createStatement();
		Statement select = c.createStatement();
		ResultSet tables = query.executeQuery("select table_name from information_schema.tables where table_schema='moviedb'");
		while(tables.next())
		{
			String tableName = tables.getString(1);
			System.out.println(tableName+":");
			ResultSet result = select.executeQuery("select * from " + tableName);
			ResultSetMetaData metadata = result.getMetaData();
			int n = metadata.getColumnCount();
			// Print name and type of each attribute
			for (int i = 1; i <= n; i++)
			System.out.println(" Type of column "+ metadata.getColumnName(i) + " is " + metadata.getColumnTypeName(i));

		}

		c.close();

	}
	public static void ExecuteValidQuery(Connection c) throws SQLException
	{
		Scanner s = new Scanner(System.in);
		Statement query = c.createStatement();
		System.out.print("\nPlease enter a valid SQL query: ");
		String str = s.nextLine();
		str = str.toUpperCase();
		if (str.startsWith("SELECT"))
		{
			ResultSet result = query.executeQuery(str);
			ResultSetMetaData metadata = result.getMetaData();
			int columnNumber = metadata.getColumnCount();
			System.out.println("\n");
			while (result.next())
			{
				for(int i = 1; i<=columnNumber; i++)
				{
					System.out.println(metadata.getColumnName(i)+": "+result.getString(i));
				}
			}
		}
		else if ((str.startsWith("UPDATE")) || (str.startsWith("INSERT")) || (str.startsWith("DELETE")))
		{
			int rows = query.executeUpdate(str);
			System.out.println(rows);
		}
		else
		{
			System.out.println("Sorry! Invalid Input, please try again");
		}
		c.close();
	}
}

