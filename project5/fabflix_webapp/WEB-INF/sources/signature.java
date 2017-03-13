import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class signature extends HttpServlet {

public static String getSignature(String dirPath){
        String[] cmd = {
                "/bin/sh",
                "-c",
                "/usr/bin/find "+dirPath+" -not -path \"*/signature/*\" -not -path \"*/META-INF/MANIFEST.MF\" -exec /usr/bin/md5sum {} + | /usr/bin/awk '{print $1}' | /usr/bin/sort | /usr/bin/md5sum"
        };
       String output = "";
       try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                output += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	return output;
    }

// Use http GET         
public void doGet(HttpServletRequest    request,  HttpServletResponse    response)
                         throws  IOException, ServletException                            {
                                  // Output stream to STDOUT
                                  PrintWriter    out = response.getWriter();
                                  out.println(getSignature("/opt/tomcat/webapps/fabflix/"));
                                  out.close();
                         }

}
