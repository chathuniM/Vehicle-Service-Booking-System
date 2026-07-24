/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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



@WebServlet("/UpdateVehicleServlet")
public class UpdateVehicleServlet extends HttpServlet {



@Override
protected void doPost(HttpServletRequest request,
                      HttpServletResponse response)

throws ServletException, IOException {



response.setContentType("text/html");

PrintWriter out = response.getWriter();



String id = request.getParameter("id");

String vehicleNo = request.getParameter("vehicleNo");

String type = request.getParameter("type");

String brand = request.getParameter("brand");



try{


Connection con = DBConnection.getConnection();



PreparedStatement ps = con.prepareStatement(

"UPDATE vehicles SET vehicle_no=?, type=?, brand=? WHERE vehicle_id=?"

);



ps.setString(1, vehicleNo);

ps.setString(2, type);

ps.setString(3, brand);

ps.setInt(4, Integer.parseInt(id));



int result = ps.executeUpdate();



if(result > 0){



out.println("<script>");

out.println("alert('Vehicle Updated Successfully');");

out.println("window.location='vehicles.html';");

out.println("</script>");



}

else{


out.println("<script>");

out.println("alert('Vehicle Update Failed');");

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