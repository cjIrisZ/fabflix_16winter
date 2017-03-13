/* A servlet to display the contents of the MySQL movieDB database */

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

@WebServlet("/searchMovieList")
public class searchMovieList extends HttpServlet {
	private Connection dbcon;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException
	{
		PrintWriter out = response.getWriter();

	        try
	        {
			Context initCtx = new InitialContext();
		        if (initCtx == null) out.println ("initCtx is NULL");
				   
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
		        if (envCtx == null) out.println ("envCtx is NULL");
					
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			if (ds == null) out.println ("ds is null.");
			      
			Connection dbcon = ds.getConnection();
			if (dbcon == null) out.println ("dbcon is null.");
	              

			// decide by which factor the result is ordered 
			String sortbytitleA = request.getParameter("sortbytitleA");
			String sortbytitleD = request.getParameter("sortbytitleD");
			String sortbyyearA = request.getParameter("sortbyyearA");
			String sortbyyearD = request.getParameter("sortbyyearD");
				  
			String sort = "";
			if(sortbytitleA != null)
				sort = "order by title";
			else if(sortbytitleD != null)
				sort = "order by title DESC";
			else if(sortbyyearA != null)
				sort = "order by year";
			else
				sort = "order by year DESC";
				  
			String perpage5 = request.getParameter("perpage5");
			String perpage10 = request.getParameter("perpage10");
			String perpage15 = request.getParameter("perpage15");
			String perpage20 = request.getParameter("perpage20");
			String perpage25 = request.getParameter("perpage25");

			String prev = request.getParameter("prev");
			String next = request.getParameter("next");
				  
			String real_offset = request.getParameter("offset");
			int offset = 1;
			if(real_offset != null)
				offset = Integer.parseInt(real_offset);
				  
			if(prev != null && offset > 1)
				offset--;
			else if(next != null)
				offset++;
				
			// decide the offset of the displaying page
			String perpage = "";
			int number = 0;
			if(perpage10 != null){
				perpage = " LIMIT 10 offset "+ (offset*10-10);
				number = 10;
			}else if(perpage15 != null){
				perpage = " LIMIT 15 offset "+(offset*15-15);
				number = 15;
			}else if(perpage20 != null){
				perpage = " LIMIT 20 offset "+(offset*20-20);
				number = 10;
			}else if(perpage25 != null){
				perpage = " LIMIT 25 offset "+(offset*25-25);
				number = 25;
			}else if(perpage5 != null){
				perpage = " LIMIT 5 offset "+ (offset*5-5);
				number = 5;
			}else if(prev != null || next != null){
				String n = request.getParameter("number");
				if(n == null){
					perpage = " LIMIT 5 offset "+ (offset*5-5);
					number = 5;
				}else{
					number = Integer.parseInt(n);
					perpage = " LIMIT "+ n + " offset " + (offset*number-number);						  
				}
	           	}else{
				perpage = " LIMIT 5 offset "+ (offset*5-5);
				number = 5;
	           	}	
			
			// get parameters of the movie searched

	              	String title = request.getParameter("title");
	              	String year = request.getParameter("year");
	              	String director = request.getParameter("director");
	              	String firstname = request.getParameter("first_name");
	              	String lastname = request.getParameter("last_name");
	              
	              
	              	ArrayList<String> id_list = new ArrayList<String>();
	              	String query = "";
	              	Statement statement = dbcon.createStatement(); 
	              	int total_result = 0;
	              	String notFound = "";
	              
	            	  
            	  	int field_num = 0;
	              	HashMap<String,String> field = new HashMap<String,String>();
	              	field.put("title", title);
	              	field.put("year", year);
	              	field.put("director", director);
	              	field.put("first_name", firstname);
	              	field.put("last_name", lastname);

		        for(Map.Entry<String, String> kv: field.entrySet()){	            	  
		        	if(kv.getValue().length() != 0 ){
		            		if(kv.getKey().compareTo("year") == 0 || kv.getKey().compareTo("title") == 0 || kv.getKey().compareTo("director") == 0){
		            			query = "select id from movies where "+kv.getKey()+" LIKE '%"+kv.getValue()+"%';";
		            		}else{
		            			query = "select id from movies where id in (select movie_id from stars_in_movies where star_id in (select id from stars where "+kv.getKey()+" LIKE '%"+kv.getValue()+"%'));";
		            		}
	            			ResultSet rs = statement.executeQuery(query);
	            			while(rs.next())
	            				id_list.add(rs.getString("id"));
	            			rs.close();
		            		++field_num;
		            	}
		         }
		              
	              
	              	if(id_list.isEmpty()){
	            	  	notFound = "Nothing is found on record!";
	            	  	request.setAttribute("notFound",notFound);
	            	  	request.getRequestDispatcher("searchMovieList.jsp").forward(request, response);
	             	}
	              
	              	HashMap<String,Integer> id_count = new HashMap<String,Integer>();
	              	for(String i: id_list){
	            	  	if(id_count.containsKey(i))
	            			id_count.put(i,id_count.get(i)+1);
				else
					id_count.put(i,1);
	              	}
	              
	              	for(Entry<String, Integer> kv: id_count.entrySet()){
	            	  	if(kv.getValue() != field_num)
	            		  	id_list.remove(kv.getKey());
	              	}
	              
		      	
	              	if(id_list.isEmpty()){
	            		notFound = "Sorry. Cannot find any record!";
	            	  	request.setAttribute("notFound",notFound);
	            	  	request.getRequestDispatcher("searchMovieList.jsp").forward(request, response);
	              	}
	        	      
			total_result = id_list.size();
  
	              

            	  	query = "select * from movies where id in (" + id_list.get(0);          	  
            	  	for(int i = 1; i < id_list.size(); ++i)
            		 	query += ", " + id_list.get(i); 
            	  		query += ") " + sort+ perpage +";";

            	  
            	  
	              	ArrayList<String> item_list = new ArrayList<String>();
	              	ArrayList<String> info_list = new ArrayList<String>();
            	  	ResultSet rs = statement.executeQuery(query);
            	  	int total_perpage = 0;
            	  
            	  	while(rs.next()){
            		  	Statement g_statement = dbcon.createStatement();
		              	String genre_query = "SELECT * FROM genres_in_movies m, genres g where m.genre_id = g.id AND m.movie_id = "+rs.getString("id")+";";
		              	ResultSet rs_genre = g_statement.executeQuery(genre_query);
		              
		              	String genre = "";
		              	if(rs_genre.isBeforeFirst()){
			        	rs_genre.next();
			        	genre = rs_genre.getString("name");
			              	while(rs_genre.next()){
			            		genre += ", " + rs_genre.getString("name");
			              	}
			             	g_statement.close();
			              	rs_genre.close();
		              	}
		              
		        Statement s_statement = dbcon.createStatement();
		        String star_query = "SELECT * FROM stars_in_movies s, movies m, stars t where s.movie_id = m.id AND s.star_id = t.id AND m.id = "+rs.getString("id")+";";
		        ResultSet rs_star = s_statement.executeQuery(star_query);
		              
		        String star = "";
		        while(rs_star.next()){
		        	star += "<form method=\"get\" action= \"starDetail\">"
		            	  	+ "<input type=\"hidden\" name=\"star_id\" value="+ rs_star.getString("star_id") +">"
		            	  	+ "<button type=\"submit\" class=\"btn btn-link\" role=\"link\" name=\"star_name\" value=\'"
		            	  	+ rs_star.getString("first_name") +" "+ rs_star.getString("last_name")+"'>" 
		            	  	+ rs_star.getString("first_name") +" "+ rs_star.getString("last_name")+"</button></form>";
		        }
		        s_statement.close();
		        rs_star.close();
		        String t = "";
		        t += "<form method=\"get\" action= \"movieDetail\">"
		            	  	+ "<input type=\"hidden\" name=\"id\" value="+ rs.getString("id") +">"
		            	  	+ "<button type=\"submit\" class=\"btn btn-link\" role=\"link\" name=\"movie_title\">" 
		            	  	+ rs.getString("title") + "</button></form>";
		        
			String item = "";
		        item =  "<table><tr><td width=\"200\"><img src=" + rs.getString("banner_url") 
	            			  +" class=\"img\" width=\"100\" height=\"150\"><br/><br/>"
	            			  + "<form method=\"post\" action=\"shoppingcart\">" 
	            			  +"<input type=\"hidden\" name=\"id\" value="+ rs.getString("id") +">"
	            			  + "<button  type=\"submit\" name=\"cart\" class=\"btn btn-default\">Add to Cart</button></form></td>"
	         
	            	  		  +	"<td width=\"600\"><table class=\"table table-hover\">" 
			            	  +"<tr><th>Title</th><td>"+ t +"</td></tr>"
			            	  +"<tr><th>Year</th><td>"+rs.getString("year")+"</td></tr>"
			            	  +"<tr><th>Director</th><td>"+rs.getString("director")+"</td></tr>"
			            	  +"<tr><th>Movie Id</th><td>"+rs.getString("id")+"</td></tr>"
			            	  +"<tr><th>Stars</th><td>"+star+"</td></tr>"
			            	  +"<tr><th>Genre</th><td>"+genre+"</td></tr>"
			            	  +"<tr><th>Trailer</th><td><a href='" +rs.getString("trailer_url")+"' >Click here!</a></td></tr>"
			            	  +"<tr><th>Price</th><td>$ 10.00</td></tr>"
			            	  +"</table></td></tr></table>";
	            	  
	            	  item_list.add(item); 
	            	  ++total_perpage;
            	  }
            	  
            	  String disabledprev = "";
            	  String disablednext = "";
            	  if(offset == 1)
            		  disabledprev = "disabled";
            	  if(total_perpage < number || total_perpage == total_result)
            		  disablednext = "disabled";
            	  

	              

	              request.setAttribute("title",title);
	              request.setAttribute("year",year);
	              request.setAttribute("director",director);
	              request.setAttribute("firstname",firstname);
	              request.setAttribute("lastname",lastname);
	              
	              request.setAttribute("disabledprev", disabledprev);
	              request.setAttribute("disablednext", disablednext);
	              request.setAttribute("offset", offset);
	              request.setAttribute("number", number);
	              request.setAttribute("notFound",notFound);
	              request.setAttribute("item_list",item_list);	
	              request.getRequestDispatcher("searchMovieList.jsp").forward(request, response);
		     
		   
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
	                            ex.toString() + "</P></BODY></HTML>");
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
