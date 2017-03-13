import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/reports/connection_pooling")
public class pooling extends HttpServlet {
// Use http GET

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {

        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        out.println("<HTML><HEAD><TITLE>fabflix</TITLE></HEAD>");
        out.println("<BODY><H3>Connection Pooling</H3>");


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

	      out.println("\tEvery java files in our project use the connection pooling. We stored the basic information, i.e username, password, URL,in the META-INF/context.xml, so that every time when we need to get connection , we can just get connection through that file. ");
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


