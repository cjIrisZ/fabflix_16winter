

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("application/json");
			String searchQuery = request.getParameter("term");
			PrintWriter out = response.getWriter();
			try {
				//String term = request.getParameter("term");
				System.out.println("Data from ajax call " + searchQuery);
				
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
				
//	            for(String s:movieList)
//	            {
//	            	System.out.println("movie id: "+s);
//	            }
				
				String titleQuery = "select title from movies where id = ?;";
				ArrayList<String> resultList = new ArrayList<String>();
				PreparedStatement pstmt = dbcon.prepareStatement(titleQuery);
				for(int i=0;i<movieList.size();i++)
				{
					pstmt.setString(1, movieList.get(i));;
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) resultList.add(rs.getString(1));
				}

				String searchList = new Gson().toJson(resultList);
				response.getWriter().write(searchList);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
