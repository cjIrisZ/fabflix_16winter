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

@WebServlet("/InsertStar")
public class employee_InsertStar extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
    {
        response.setContentType("text/html");
        
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        
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
            // Declare our statement
            Statement statement = dbcon.createStatement();
            String query = "SELECT * FROM stars WHERE first_name = '"+first_name+"'";
            if (last_name.length() == 0 || last_name == null)
            {
            	query = query+";";
            	last_name = "";
            }
            else query = query + " AND last_name = '"+last_name+"';";
            ResultSet rs = statement.executeQuery(query);
            if (!rs.next()) {
            	Statement st = dbcon.createStatement();
            	ResultSet idrs = st.executeQuery("select MAX(id) from stars;");
            	idrs.next();
            	int id = idrs.getInt(1)+1;
            	idrs.close();
            	st.close();
            	String insertquery = "INSERT INTO stars VALUES ("+id+", '"+first_name+"', '"+last_name+"', null, null);";
            	PreparedStatement preparedStmt = dbcon.prepareStatement(insertquery);
            	preparedStmt.execute();
            	request.setAttribute("message","<div class=\"alert alert-danger\" role=\"alert\">inserted successfully</div>");
            	preparedStmt.close();
            	
            }  else {
            	System.out.println("found in records");
            	request.setAttribute("message","<div class=\"alert alert-danger\" role=\"alert\">Star already exists</div>");
                }
            request.getRequestDispatcher("/employee_actions.jsp").forward(request, response);
            rs.close();
            statement.close();
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