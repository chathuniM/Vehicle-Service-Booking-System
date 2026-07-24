/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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



@WebServlet("/ServiceHistoryServlet")
public class ServiceHistoryServlet extends HttpServlet {



@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)

throws ServletException, IOException {



response.setContentType("text/html");

PrintWriter out = response.getWriter();



// Get logged user

HttpSession session = request.getSession(false);



if(session == null || session.getAttribute("userId") == null){


out.println("<tr>");

out.println("<td colspan='4'>Please Login First</td>");

out.println("</tr>");

return;


}



int userId = (Integer) session.getAttribute("userId");



try{


Connection con = DBConnection.getConnection();



PreparedStatement ps = con.prepareStatement(

"SELECT vehicle_no, service, booking_date, status "
+ "FROM bookings "
+ "WHERE user_id=? AND status='Completed' "
+ "ORDER BY booking_id DESC"

);



ps.setInt(1,userId);



ResultSet rs = ps.executeQuery();



boolean found=false;



while(rs.next()){


found=true;


out.println("<tr>");



out.println("<td>"
+ rs.getString("vehicle_no")
+ "</td>");



out.println("<td>"
+ rs.getString("service")
+ "</td>");



out.println("<td>"
+ rs.getDate("booking_date")
+ "</td>");



out.println("<td>");

out.println("<span class='completed'>");

out.println(rs.getString("status"));

out.println("</span>");

out.println("</td>");



out.println("</tr>");



}



if(!found){


out.println("<tr>");

out.println("<td colspan='4'>No Completed Services Found</td>");

out.println("</tr>");



}



rs.close();

ps.close();

con.close();



}
catch(Exception e){


e.printStackTrace();


out.println("<tr>");

out.println("<td colspan='4'>Error Loading History</td>");

out.println("</tr>");



}



}



}