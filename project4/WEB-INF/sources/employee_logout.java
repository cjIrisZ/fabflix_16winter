import java.io.IOException;  
import java.io.PrintWriter;  
  

import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession; 

@WebServlet("/employee_logout")
public class employee_logout extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException { 
        	
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
              
            HttpSession session=request.getSession();  
            session.invalidate();  
                  
            
            out.println("<html><head><meta http-equiv=\"refresh\" content=\"3;url=_dashboard.jsp\"/>" +
            		"<div clas=\"container\"><section class=\"text-center\"><h3>You are successfully logged out!"+
            		"</h3><br/>Redirecting in 3 seconds...</section></body></html>");
              
            out.close();  
    }  
}