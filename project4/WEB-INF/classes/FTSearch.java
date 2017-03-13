

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class FTSearch {

	public static ArrayList<String> searchList(Connection dbcon, String searchQuery) throws SQLException{
            // Declare our statement
		System.out.println("searchList");
		if(dbcon == null) System.out.println("searchList");
            String[] tokenArray = searchQuery.split("[^a-z0-9A-Z]+");
            int size = tokenArray.length;
            HashMap<String,String> movieMap = new HashMap<String,String>();
            ArrayList<String> movieList = new ArrayList<String>();
            
            Statement stmt = dbcon.createStatement();
            String Query = "SELECT id FROM movies WHERE MATCH (title) AGAINST ('";
            if (!(tokenArray[0].toLowerCase().equals("and") |tokenArray[0].toLowerCase().equals("the")) )
            		Query += "+"+tokenArray[0];
            for(int i=1;i<size;i++)
            {
            	String s = tokenArray[i].toLowerCase();
            	if(s.equals("and") | s.equals("the")) {
            		continue;
            	}
            	Query+=" +"+s;
            }
            Query+="*'in boolean mode);";
            
            System.out.println(Query);
            //System.out.println(Query);
            ResultSet rs = stmt.executeQuery(Query);
            while(rs.next()) 
            {	
            	String mid = rs.getString(1);
        		if (movieMap.containsKey(mid)) continue;
        		else
        		{
        			movieList.add(mid);
        			movieMap.put(mid, mid);
        		}            
        	}
            rs.close();
            stmt.close();
            
            for(String s:movieList)
            {
            	System.out.println("movie id: "+s);
            }
            
            return movieList;
            
	}
}
