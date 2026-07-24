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



@WebServlet("/AdminUserServlet")
public class AdminUserServlet extends HttpServlet {


@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
throws ServletException, IOException {



response.setContentType("text/html");

PrintWriter out = response.getWriter();



try{


Connection con = DBConnection.getConnection();



PreparedStatement ps = con.prepareStatement(

"SELECT user_id,name,email,phone,role FROM users"

);



ResultSet rs = ps.executeQuery();



boolean found = false;



while(rs.next()){


found = true;


out.println("<tr>");



out.println("<td>"
+ rs.getInt("user_id")
+ "</td>");



out.println("<td>"
+ rs.getString("name")
+ "</td>");



out.println("<td>"
+ rs.getString("email")
+ "</td>");



out.println("<td>"
+ rs.getString("phone")
+ "</td>");



out.println("<td>"
+ rs.getString("role")
+ "</td>");



out.println("<td>");



out.println(
"<a class='delete-btn' "
+ "onclick=\"return confirm('Are you sure you want to delete this user?');\" "
+ "href='DeleteUserServlet?id="
+ rs.getInt("user_id")
+ "'>Delete</a>"
);



out.println("</td>");



out.println("</tr>");



}



if(!found){


out.println("<tr>");

out.println("<td colspan='6'>No Users Found</td>");

out.println("</tr>");

}



rs.close();

ps.close();

con.close();



}
catch(Exception e){


e.printStackTrace();


out.println("<tr>");

out.println("<td colspan='6'>Error Loading Users</td>");

out.println("</tr>");

}



}



}