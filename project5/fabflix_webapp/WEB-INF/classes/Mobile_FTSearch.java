import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

import com.google.gson.Gson;

@WebServlet("/Mobile_FTSearch")
public class Mobile_FTSearch extends HttpServlet{
//	private HttpSession session;
//	private ArrayList<String> info = new ArrayList<String>();
//	private ArrayList<String> titleList = new ArrayList<String>();
    
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String searchQuery = request.getParameter("search");
        try
        {
            Context initCtx = new InitialContext();
            if (initCtx == null) out.println ("initCtx is NULL");
		   
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null) out.println ("envCtx is NULL");
			
            // Look up our data source
            DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
	    
            if (ds == null)
            	out.println ("ds is null.");
      
            Connection dbcon = ds.getConnection();
            if (dbcon == null) out.println ("dbcon is null.");
            
            
            ArrayList<String> movieList = FTSearch.searchList(dbcon,searchQuery);
            String Query = "select title from movies where id in ("+movieList.get(0);          	  
	  	  	for(int i = 1; i<movieList.size();i++)
	  	  		Query += ", "+movieList.get(i); 
	  	  	Query += ");";
	  	  	Statement stmt = dbcon.createStatement();
	  	  	ResultSet idrs = stmt.executeQuery(Query);
	  	  	String title = "";
            while (idrs.next())
            {
            	title = idrs.getString(1);
            	out.println(title);
            }
//            if (movieList.isEmpty()){
//          	  String s = "<tr>"
//	            	  		+ "<td></td>"
//	            	  		+ "<td></td>"
//	            	  		+ "<td></td>"
//	            	  		+ "<td></td>"
//	            	  		+ "<td></td>"
//	            	  		+ "<td></td>"
//	            	  		+ "</tr>";
//          	  info.add(s);
//          	  request.setAttribute("info", info);
//	          request.getRequestDispatcher("FullTextSearch.jsp").forward(request, response);
//	          return;
//            }
//            
//            String Query = "select * from movies where id in ("+movieList.get(0);          	  
//      	  	for(int i = 1; i<movieList.size();i++)
//      	  		Query += ", "+movieList.get(i); 
//      	  	Query += ");";
//      	  	Statement stmt = dbcon.createStatement();
//      	  	ResultSet idrs = stmt.executeQuery(Query);
//            while(idrs.next()){
//          	  Statement g_statement = dbcon.createStatement();
//          	  String genre_query = "SELECT * FROM genres_in_movies m, genres g where m.genre_id = g.id AND m.movie_id = "+idrs.getString("id")+";";
//          	  ResultSet rs_genre = g_statement.executeQuery(genre_query);
//	          String genre_name = "";
//	          while(rs_genre.next()){
//	        	  genre_name +=  "&nbsp;" + rs_genre.getString("name");
//	          }
//	          
//	          Statement s_statement = dbcon.createStatement();
//	          String star_query = "SELECT * FROM stars_in_movies s, movies m, stars t where s.movie_id = m.id AND s.star_id = t.id AND m.id = "+idrs.getString("id")+";";
//	          ResultSet rs_star = s_statement.executeQuery(star_query);    
//	          String star = "";
//	          while(rs_star.next()){
//	        	  star += "<form method=\"get\" action= \"star\">"
//	            	  + "<input type=\"hidden\" name=\"star_id\" value="+ rs_star.getString("star_id") +">"
//	            	  + "<button type=\"submit\" class=\"btn btn-link\" role=\"link\" name=\"star_name\" value=\'"
//	            	  + rs_star.getString("first_name") +" "+ rs_star.getString("last_name")+"'>" 
//	            	  + rs_star.getString("first_name") +" "+ rs_star.getString("last_name")+"</button></form>";
//	        }
//	          s_statement.close();
//	          rs_star.close();
//          	  
//	          String title = idrs.getString("title");
//          	  String s = "<tr>"
//          	  		+ "<td>"+idrs.getString("id")+"</td>"
//          	  		+ "<td>"+title+"</td>"
//          	  		+ "<td>"+idrs.getString("year")+"</td>"
//          	  		+ "<td>"+idrs.getString("director")+"</td>"
//          	  		+ "<td>"+star+"</td>"
//          	  		+ "<td>"+genre_name+"</td>"
//          	  		+ "</tr>";
//          	  info.add(s);
//          	  titleList.add(title);
//            } 
//            
//            
////            for(String s:movieMap.keySet())
////            {
////            	System.out.println("movie: "+s);
////            }
//            
//            request.setAttribute("info", info);
//            request.getRequestDispatcher("FullTextSearch.jsp").forward(request, response);
//            
//            stmt.close();
        }
    	catch (SQLException ex) {
              while (ex != null) {
            	  out.println ("SQL Exception:  " + ex.getMessage ()+"\nSQLEXCEPTION");
                  ex = ex.getNextException ();
              }  // end while
         }  // end catch SQLException
        catch(java.lang.Exception ex)
        {
        	out.println("<HTML>" +"<HEAD><TITLE>" + 
        				"MovieDB: Error hehehehehe" +
                        "</TITLE></HEAD>\n<BODY>" +
                        "<P>SQL error in doGet: " +
                        ex.getMessage() + "searchQuery = " + searchQuery+
                        "</P></BODY></HTML>");
            return;
        }
        
        out.close();
    }

}
