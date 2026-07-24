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



@WebServlet("/ViewVehicleServlet")
public class ViewVehicleServlet extends HttpServlet {



@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {



response.setContentType("text/html");

PrintWriter out = response.getWriter();



// Get logged user session

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

"SELECT vehicle_id, vehicle_no, type, brand FROM vehicles WHERE user_id=?"

);



ps.setInt(1,userId);



ResultSet rs = ps.executeQuery();



boolean found = false;



while(rs.next()){


found = true;



out.println("<tr>");



out.println("<td>"
+ rs.getString("vehicle_no")
+ "</td>");



out.println("<td>"
+ rs.getString("type")
+ "</td>");



out.println("<td>"
+ rs.getString("brand")
+ "</td>");



out.println("<td>");


// Edit button

out.println(
"<a class='edit-btn' href='EditVehicleServlet?id="
+ rs.getInt("vehicle_id")
+ "'>Edit</a>"
);



// Delete button

out.println(
"<a class='delete-btn' "
+ "onclick=\"return confirm('Are you sure you want to delete this vehicle?');\" "
+ "href='DeleteVehicleServlet?id="
+ rs.getInt("vehicle_id")
+ "'>Delete</a>"
);



out.println("</td>");



out.println("</tr>");



}



if(!found){


out.println("<tr>");

out.println("<td colspan='4'>No Vehicles Added Yet</td>");

out.println("</tr>");



}



rs.close();

ps.close();

con.close();



}
catch(Exception e){


e.printStackTrace();


out.println("<tr>");

out.println("<td colspan='4'>Error Loading Vehicles</td>");

out.println("</tr>");

}



}


}