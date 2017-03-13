import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;


@WebServlet("/search")
public class search extends HttpServlet
{
    public String getServletInfo()
    {
       return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {

        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>Fabflix</TITLE></HEAD>");
        out.println("<BODY bgcolor=\"#FDF5E6\"><H1>MovieDB</H1>");
	String title = request.getParameter("title");
	String year = request.getParameter("year");
	String director = request.getParameter("director");
	String firstname = request.getParameter("first_name");
	String lastname = request.getParameter("last_name");

        try
           {
	       // the following few lines are for connection pooling
	       // Obtain our environment naming context
		   
	       Context initCtx = new InitialContext();
             if (initCtx == null) out.println ("initCtx is NULL");
		   
	       Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null) out.println ("envCtx is NULL");
			
	       // Look up our data source
	       DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
	    
			if (ds == null)
			   out.println ("ds is null.");
      
			  Connection dbcon = ds.getConnection();
			if (dbcon == null)
              out.println ("dbcon is null.");
              search(dbcon,response,title,year,director,firstname,lastname);

              dbcon.close();
            }
        catch (SQLException ex) {
            ex.printStackTrace();
	    while (ex != null) {
                    System.out.println ("SQL Exception:  " + ex.getMessage ());
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
    public static void search(Connection c, HttpServletResponse response, String title, String year, String director, String firstname, String lastname) throws SQLException, IOException, ServletException
    {
        
        PrintWriter out = response.getWriter();
        try
        {
	    if ((year=="")&(title=="")&(director=="")&(firstname=="")&(lastname=="")) 
	    {
		out.println("hehehe");
		response.sendRedirect("/fabflix/search.jsp");
	    }
	    Statement query = c.createStatement();
            String select_query = "select * from movies where 1=1 ";
            if (title != "") select_query = select_query + "and title LIKE '%" + title + "%'";
            if (year != "") select_query = select_query + " and year LIKE '%" + year + "%'";
            if (director != "") select_query = select_query + "and director LIKE '%" + director + "%'";
            
            int limit = 10;
            int offset = 10;
            select_query = select_query + " LIMIT " + limit + " OFFSET " + offset;
	    
	    ResultSet rs = query.executeQuery(select_query);
            out.println("<TABLE border>");
            out.println("<tr><td> ID </td><td> Title </td><td> Year </td><td> Director </td></tr>");
	    while (rs.next())
            {
                
		String m_ID = rs.getString(1);
                String m_TT = rs.getString(2);
                String m_YR = rs.getString(3);
                String m_DR = rs.getString(4);
                String m_URL = rs.getString(5);
                String m_TURL = rs.getString(6);
                out.println("<tr>" +
                            "<td>" + m_ID + "</td>" +
                            "<td>" + m_TT + "</td>" +
                            "<td>" + m_YR + "</td>" +
                            "<td>" + m_DR + "</td>" +
                            "</tr>");
            }
	out.println("</TABLE>");
	    rs.close();
	    query.close();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }
}
