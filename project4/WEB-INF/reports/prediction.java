import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/reports/like-predicate")

public class prediction extends HttpServlet {
// Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {

        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>fabflix</TITLE></HEAD>");
        out.println("<BODY><H1>Like Predicate</H1>");


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
                        if (dbcon == null)
              out.println ("dbcon is null.");
	      out.println ("In our code, we allow users to search movies based on movie title, years, directors, and the last names or/and the first names of the stars featuring the movie.\nOur SQL code assumes every input potentially be part of the real data, and thus we use LIKE %input_data%. It will match everything that contains the input_data. We append SELECT queries with LIKE statements and get queries like \"SELECT * FROM movies WHERE title LIKE 'input_data'\"");

              dbcon.close();
            }
        catch (SQLException ex) {
            ex.printStackTrace();
            while (ex != null) {
                    System.out.println ("SQL Exception:  " + ex.getMessage ());
                    ex = ex.getNextException ();
                }  // end while
            }  // end catch SQLException^M


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
}


