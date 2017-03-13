import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/addMovie")
public class employee_addMovie extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
    {
        response.setContentType("text/html");
        String movie_title = request.getParameter("movie_title");
        String movie_year = request.getParameter("movie_year");
        String movie_director = request.getParameter("director");
        String genre = request.getParameter("genre");
        String last_name = request.getParameter("last_name");
        String first_name = request.getParameter("first_name");
        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
	    
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
            if (movie_title == null || movie_title.length()==0 
            		|| movie_year == null || movie_year.length() == 0 
            		|| movie_director == null || movie_director.length()==0
            		|| genre == null || genre.length()==0
            		|| first_name == null || first_name.length() == 0)
            {
//            	System.out.println("movie_title:"+ movie_title
//            			+"\nmovie_year: "+movie_year
//            			+"\ndirector: "+movie_director
//            			+"\ngenre: "+genre
//            			+"\nfirst_name:"+first_name
//            			+"\nlast_name:"+last_name);
            	request.setAttribute("addMovieMessage","<div class=\"alert alert-danger\" role=\"alert\">not enough input to add movie</div>");
            	request.getRequestDispatcher("/employee_actions.jsp").forward(request, response);
            }
            if (last_name == null || last_name.length() == 0) last_name = "";
            // Declare our statement
            CallableStatement cStmt = dbcon.prepareCall("{CALL Add_Movie(?, ?, ?, ?, ?, ?, ?, ?)}");
    		cStmt.setString(1, movie_title);
    		cStmt.setString(2, movie_year);
    		cStmt.setString(3, movie_director);
    		cStmt.setString(4, null); //banner
    		cStmt.setString(5, null); //trailer
    		cStmt.setString(6, first_name);
    		cStmt.setString(7, last_name);
    		cStmt.setString(8, genre);
    		boolean success = cStmt.execute();
    		if (!success) {
//    			System.out.println("movie_title:"+ movie_title
//            			+"\nmovie_year: "+movie_year
//            			+"\ndirector: "+movie_director
//            			+"\ngenre: "+genre
//            			+"\nfirst_name:"+first_name
//            			+"\nlast_name:"+last_name);
    			request.setAttribute("addMovieMessage","<div class=\"alert alert-danger\" role=\"alert\">inserted successfully</div>");
    		}
    		else {
//    			System.out.println("movie_title:"+ movie_title
//            			+"\nmovie_year: "+movie_year
//            			+"\ndirector: "+movie_director
//            			+"\ngenre: "+genre
//            			+"\nfirst_name:"+first_name
//            			+"\nlast_name:"+last_name);
    			request.setAttribute("addMovieMessage","<div class=\"alert alert-danger\" role=\"alert\">error when insert the movie</div>");
    		}
            request.getRequestDispatcher("/employee_actions.jsp").forward(request, response);
            cStmt.close();
            dbcon.close();
        }
        catch (SQLException ex) {
        	while (ex != null) {
        		out.println ("SQL Exception:  " + ex.getMessage ());
        		ex = ex.getNextException ();
        	}  // end while
        }  // end catch SQLException

        catch(java.lang.Exception ex)
        {
        	out.println("<HTML>" +
        			"<HEAD><TITLE>" +
                    "MovieDB: Error" +
        			"</TITLE></HEAD>\n<BODY>" +
                    "<P>SQL error in doGet: " +
        			ex.getMessage() + "</P></BODY></HTML>");
            return;
         }
        out.close();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
	{
		doGet(request, response);
	}
    
}