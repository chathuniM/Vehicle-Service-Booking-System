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
import jakarta.servlet.http.HttpSession;



@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {



@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {



response.setContentType("text/html");

PrintWriter out = response.getWriter();



String username = request.getParameter("username");

String password = request.getParameter("password");



try{


Connection con = DBConnection.getConnection();



PreparedStatement ps = con.prepareStatement(

"SELECT * FROM admin WHERE username=? AND password=?"

);



ps.setString(1, username);

ps.setString(2, password);



ResultSet rs = ps.executeQuery();



if(rs.next()){



HttpSession session = request.getSession();


session.setAttribute(
"adminId",
rs.getInt("admin_id")
);


session.setAttribute(
"adminUsername",
rs.getString("username")
);



out.println("<script>");

out.println("alert('Admin Login Successful');");

out.println("window.location='adminDashboard.html';");

out.println("</script>");



}
else{


out.println("<script>");

out.println("alert('Invalid Admin Login');");

out.println("window.location='adminLogin.html';");

out.println("</script>");



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