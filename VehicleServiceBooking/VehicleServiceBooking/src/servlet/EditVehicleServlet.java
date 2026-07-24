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



@WebServlet("/EditVehicleServlet")
public class EditVehicleServlet extends HttpServlet {


@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)

throws ServletException, IOException {



response.setContentType("text/html");

PrintWriter out = response.getWriter();



String id = request.getParameter("id");



try{


Connection con = DBConnection.getConnection();



PreparedStatement ps = con.prepareStatement(

"SELECT * FROM vehicles WHERE vehicle_id=?"

);



ps.setInt(1,Integer.parseInt(id));



ResultSet rs = ps.executeQuery();



if(rs.next()){



out.println("<html>");

out.println("<head>");

out.println("<title>Edit Vehicle</title>");

out.println("<link rel='stylesheet' href='css/style.css'>");

out.println("</head>");



out.println("<body>");



out.println("<h2>Edit Vehicle</h2>");



out.println("<form action='UpdateVehicleServlet' method='post'>");



// Hidden ID

out.println("<input type='hidden' name='id' value='"
+ rs.getInt("vehicle_id")
+ "'>");




// Vehicle Number

out.println("<label>Vehicle Number</label>");

out.println("<input type='text' name='vehicleNo' value='"
+ rs.getString("vehicle_no")
+ "' required>");




// Vehicle Type Dropdown

out.println("<label>Vehicle Type</label>");

out.println("<select name='type' required>");

String type = rs.getString("type");


out.println("<option value='Car' "
+ (type.equals("Car") ? "selected" : "")
+ ">Car</option>");

out.println("<option value='Bike' "
+ (type.equals("Bike") ? "selected" : "")
+ ">Bike</option>");

out.println("<option value='Van' "
+ (type.equals("Van") ? "selected" : "")
+ ">Van</option>");

out.println("<option value='SUV' "
+ (type.equals("SUV") ? "selected" : "")
+ ">SUV</option>");

out.println("<option value='Truck' "
+ (type.equals("Truck") ? "selected" : "")
+ ">Truck</option>");

out.println("</select>");




// Brand Dropdown

out.println("<label>Brand</label>");

out.println("<select name='brand' required>");

String brand = rs.getString("brand");


out.println("<option value='BMW' "
+ (brand.equals("BMW") ? "selected" : "")
+ ">BMW</option>");

out.println("<option value='Toyota' "
+ (brand.equals("Toyota") ? "selected" : "")
+ ">Toyota</option>");

out.println("<option value='Honda' "
+ (brand.equals("Honda") ? "selected" : "")
+ ">Honda</option>");

out.println("<option value='Suzuki' "
+ (brand.equals("Suzuki") ? "selected" : "")
+ ">Suzuki</option>");

out.println("<option value='Nissan' "
+ (brand.equals("Nissan") ? "selected" : "")
+ ">Nissan</option>");

out.println("<option value='Mercedes' "
+ (brand.equals("Mercedes") ? "selected" : "")
+ ">Mercedes</option>");

out.println("</select>");




// Update Button

out.println("<button type='submit'>Update Vehicle</button>");



out.println("</form>");



out.println("</body>");

out.println("</html>");



}


else{


out.println("<h3>Vehicle Not Found</h3>");


}



rs.close();

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