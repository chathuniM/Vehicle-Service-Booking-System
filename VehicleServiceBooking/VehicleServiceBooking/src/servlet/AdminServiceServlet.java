/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;


import database.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/AdminServiceServlet")
public class AdminServiceServlet extends HttpServlet {


@Override
protected void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {


response.setContentType("text/html");

PrintWriter out=response.getWriter();


try{


Connection con=DBConnection.getConnection();


PreparedStatement ps=con.prepareStatement(
"SELECT * FROM services"
);


ResultSet rs=ps.executeQuery();



while(rs.next()){


out.println("<tr>");

out.println("<td>"
+rs.getInt("service_id")
+"</td>");

out.println("<td>");

out.println(
"<span class='service-name'>"
+ rs.getString("service_name")
+ "</span>"
);

out.println("</td>");
out.println("<td>"
+rs.getString("description")
+"</td>");

out.println("<td>");

out.println(
"<span class='price-badge'>Rs. "
+ rs.getDouble("price")
+ "</span>"
);

out.println("</td>");

out.println("<td>");

out.println("<a href='editService.html?id="
+ rs.getInt("service_id")
+ "&name="
+ java.net.URLEncoder.encode(rs.getString("service_name"), "UTF-8")
+ "&description="
+ java.net.URLEncoder.encode(rs.getString("description"), "UTF-8")
+ "&price="
+ rs.getDouble("price")
+ "' class='edit-btn'>Edit</a>");

out.println("&nbsp;&nbsp;");

out.println("<a href='DeleteServiceServlet?id="
+ rs.getInt("service_id")
+ "' class='delete-btn' onclick=\"return confirm('Are you sure you want to delete this service?');\">Delete</a>");

out.println("</td>");

out.println("</tr>");

}


rs.close();
ps.close();
con.close();


}
catch(Exception e){

e.printStackTrace();

out.println(e.getMessage());

}


}


}
