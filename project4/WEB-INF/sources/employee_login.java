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

@WebServlet("/employee_login")
public class employee_login extends HttpServlet
{
	private HttpSession session;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
    {
        response.setContentType("text/html");
        
        String email = request.getParameter("employee_email");
        String password = request.getParameter("employee_password");
        
        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
	    out.println("<BODY>redirecting...</BODY>");
	    
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
            String query = "SELECT * from employees where email = '" + email + "'AND password = '" + password + "';";
            ResultSet rs = statement.executeQuery(query);
            String name = "";
            if (!rs.isBeforeFirst()) {
            	request.setAttribute("message","<div class=\"alert alert-danger\" role=\"alert\"><span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span><span class=\"sr-only\">Error:</span>The email or password you enter is incorrect.</div>");
            	request.getRequestDispatcher("/_dashboard").forward(request, response);
                return;
            }  else {
            	while (rs.next()){
            		name = rs.getString("fullname");
                }
                session = request.getSession();
                session.setAttribute("employee_email",email);
                session.setAttribute("employee_password",password);
                session.setAttribute("fullname",name);
//                Cookie userName = new Cookie("email", email);
//                Cookie pwd = new Cookie("pwd", password);
//                response.addCookie(userName);
//                response.addCookie(pwd);

            }
            response.sendRedirect("employee_actions.jsp");
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