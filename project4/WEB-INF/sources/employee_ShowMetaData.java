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

@WebServlet("/ShowMetaData")
public class employee_ShowMetaData extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
    {
        response.setContentType("text/html");
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
            Statement select = dbcon.createStatement();
            ResultSet tables = statement.executeQuery("select table_name from information_schema.tables where table_schema='moviedb'");
            ArrayList<String> item_list = new ArrayList<String>();
            while(tables.next())
    		{
    			String tableName = tables.getString(1);
    			//System.out.println(tableName+":");
    			String item = "<table><tr>"+ tableName.toUpperCase()+"<td><table class=\"table table-striped\">"
    					+"<tr><td>Attribute</td><td>Type</td></tr>";
    			ResultSet result = select.executeQuery("select * from " + tableName);
    			ResultSetMetaData metadata = result.getMetaData();
    			int n = metadata.getColumnCount();
    			// Print name and type of each attribute
    			for (int i = 1; i <= n; i++)
    			{
    				item = item+"<tr><td>"+ metadata.getColumnName(i) +"</td><td>"+ metadata.getColumnTypeName(i) +"</td></tr>";
    				//System.out.println(" Type of column "+ metadata.getColumnName(i) + " is " + metadata.getColumnTypeName(i));
    			}
    			item = item+"</table></td></tr></table>";
    			item_list.add(item);
    		}
            request.setAttribute("item_list",item_list);
            request.getRequestDispatcher("/employee_actions.jsp").forward(request, response);
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