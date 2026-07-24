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


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/AddServiceServlet")
public class AddServiceServlet extends HttpServlet {



@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)

throws ServletException, IOException {



response.setContentType("text/html");

PrintWriter out = response.getWriter();



String serviceName = request.getParameter("serviceName");

String description = request.getParameter("description");

String price = request.getParameter("price");



try{


Connection con = DBConnection.getConnection();



PreparedStatement ps = con.prepareStatement(

"INSERT INTO services(service_name,description,price) VALUES(?,?,?)"

);



ps.setString(1, serviceName);

ps.setString(2, description);

ps.setDouble(3, Double.parseDouble(price));



int result = ps.executeUpdate();



if(result > 0){



out.println("<script>");

out.println("alert('Service Added Successfully');");

out.println("window.location='manageServices.html';");

out.println("</script>");



}
else{


out.println("<script>");

out.println("alert('Service Add Failed');");

out.println("window.history.back();");

out.println("</script>");



}



ps.close();

con.close();



}
catch(Exception e){


e.printStackTrace();


out.println("<h3>Error : "
+ e.getMessage()
+ "</h3>");

}



}


}