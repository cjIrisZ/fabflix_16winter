import java.sql.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ExtendDatabase {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		SAXMovieParser spMovie = new SAXMovieParser();
		ArrayList<Movie> MovieList = spMovie.runExample();
		SAXStarParser spStar = new SAXStarParser();
		ArrayList<Star> StarList = spStar.runExample();
		SAXStarsInMovieParser spSIM = new SAXStarsInMovieParser();
		ArrayList<Stars_in_Movie> RelationList = spSIM.runExample();

		long start = System.currentTimeMillis();;
		
		HashMap<String,Integer> MovieMap = new HashMap<String,Integer>();
		HashMap<String,Integer> StarMap = new HashMap<String,Integer>();
		// Incorporate mySQL driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String dbusr = "classta";
		String dbpw = "classta";
		//Connect to the test database
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb_project4_grading?useUnicode=true&characterEncoding=utf-8&useSSL=false",dbusr, dbpw);
		connection.setAutoCommit(false);
		// insert stars
		CallableStatement cStmt = connection.prepareCall("{? = CALL Add_Star(?, ?, ?)}");
    	for(Star s:StarList)
    	{
    		String ftn = s.getFirstname(), ltn = s.getLastname();
    		if (ftn == null | ltn == null)
    		{
    			String[] name = s.getStagename().split(" ");
    			ftn = name[0];
    			ltn = name[1];
    		}
    		//if (ftn.length() == 0 | ltn.length() == 0) continue;
    		
    		
    		cStmt.registerOutParameter(1,java.sql.Types.INTEGER);
    		cStmt.setString(2, ftn);
    		cStmt.setString(3, ltn);
    		cStmt.setDate(4, convertDate(s));
    		cStmt.execute();
    		int starid = cStmt.getInt(1);
    		StarMap.put(s.getStagename(), starid);
    		
    		
//    		String Query = "INSERT INTO stars(first_name, last_name, dob) VALUES(?, ?, ?);";
//    		PreparedStatement pStmt = connection.prepareStatement(Query);
//    		pStmt.setString(1, ftn);
//    		pStmt.setString(2, ltn);
//    		pStmt.setDate(3, convertDate(s));
//    		pStmt.execute();
//    		pStmt.close();
//    		
//    		Statement stmt = connection.createStatement();
//    		ResultSet sid = stmt.executeQuery("select max(id) from stars");
//    		sid.next();
//    		StarMap.put(s.getStagename(), sid.getInt(1));
//    		sid.close();
//    		stmt.close();
    	}
    	cStmt.close();
    	
    	// insert movies and genres
    	String Query = "INSERT INTO movies(title, year, director) VALUES(?, ?, ?);";
		PreparedStatement pStmt = connection.prepareStatement(Query);
		CallableStatement gcStmt = connection.prepareCall("{? = CALL Add_Genre(?, ?, ?, ?)}");
    	for(Movie m:MovieList)
    	{
    		int year = convertString2Int(m.getYear());
//    		CallableStatement cStmt = connection.prepareCall("{CALL Add_Movies(?, ?, ?)}");
//    		cStmt.setString(1, m.getTitle());
//    		cStmt.setInt(2, year);
//    		cStmt.setString(3, m.getDirector());
//    		cStmt.execute();
//    		cStmt.close();
    		
    		pStmt.setString(1, m.getTitle());
    		pStmt.setInt(2, year);
    		pStmt.setString(3, m.getDirector());
    		pStmt.execute();
    		
    		
    		ArrayList<String> GenreList = m.getGenres();
    		if (GenreList.isEmpty()) continue;
    		else
    		{
    			for(String g:GenreList)
    			{
    				gcStmt.registerOutParameter(1,java.sql.Types.INTEGER);
    				gcStmt.setString(2,g);
    	    		gcStmt.setString(3, m.getTitle());
    	    		gcStmt.setInt(4, year);
    	    		gcStmt.setString(5, m.getDirector());
    	    		gcStmt.execute();
    				int movie_id = gcStmt.getInt(1);
    				MovieMap.put(m.getId(), movie_id);
    	    		
//    				int genre_id,movie_id;
//    				Statement gst = connection.createStatement();
//	            	ResultSet gid = gst.executeQuery("select id from genres where name='"+g+"';");
//	            	if (gid.next()) genre_id = gid.getInt(1);
//	            	else
//	            	{
//	            		String gquery = "insert into genres(name) values(?)";
//	            		PreparedStatement gpstmt = connection.prepareStatement(gquery);
//	            		gpstmt.setString(1, g);
//	            		gpstmt.execute();
//	            		gpstmt.close();
//	            		ResultSet maxgid = gst.executeQuery("select max(id) from genres;");
//	            		maxgid.next();
//	            		genre_id = maxgid.getInt(1);
//	            		maxgid.close();
//	            	}
//	            	ResultSet mid = gst.executeQuery("select max(id) from movies;");
//	            	mid.next();
//	            	movie_id = mid.getInt(1);
//	            	String g2mQuery = "insert into genres_in_movies(genre_id, movie_id) values(?, ?)";
//	            	PreparedStatement g2mstmt = connection.prepareStatement(g2mQuery);
//	            	g2mstmt.setInt(1, genre_id);
//	            	g2mstmt.setInt(2, movie_id);
//	            	g2mstmt.execute();
//	            	MovieMap.put(m.getId(), movie_id);
//	            	g2mstmt.close();
//	            	mid.close();
//	            	gid.close();
//	            	gst.close();
    			}
    		}
    	}
    	gcStmt.close();
    	pStmt.close();
    	
    	// insert stars in movies info
    	String SIMQuery = "insert into stars_in_movies(star_id, movie_id) values (?,?)";
		PreparedStatement SIMpstmt = connection.prepareStatement(SIMQuery);
    	for(Stars_in_Movie SIM:RelationList)
    	{
    		String m_id = SIM.getMovieid();
    		ArrayList<String> s_List = SIM.getStarList();
    		if (m_id == null | s_List == null) continue;
    		if (m_id.length() == 0 | s_List.size() == 0) continue;
    		int movie_id,star_id;
    		if (MovieMap.containsKey(m_id)) movie_id = MovieMap.get(SIM.getMovieid());
    		else continue;
    		for (String s:s_List)
    		{
    			if (s == null) continue;
    			if (s.length() == 0) continue;
    			if (StarMap.containsKey(s))  star_id = StarMap.get(s);
    			else continue;
    			
    			SIMpstmt.setInt(1, star_id);
    			SIMpstmt.setInt(2, movie_id);
    			SIMpstmt.execute();
    			
    		}
    		
    	}
    	SIMpstmt.close();
    	connection.commit();
    	connection.close();
    	
    	long elapsedTimeMillis = System.currentTimeMillis()-start;
    	float elapsedTimeSec = elapsedTimeMillis/1000F;
    	System.out.println(elapsedTimeSec);
	}
	
	private static java.sql.Date convertDate(Star s)
	{
		try {
			String year = "0000";
			if (!(s.getDob() == null)){
				if (s.getDob().matches("^\\d{4}$")) year = s.getDob();
			}
			String strDate = year+"-00-00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	        java.util.Date date;
			date = sdf.parse(strDate);
			java.sql.Date dob = new Date(date.getTime());
			return dob;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static int convertString2Int(String s)
	{
		int n = 0;
		if (s.matches("^\\d{4}$")) n = Integer.parseInt(s.trim());
		return n;
	}
}
