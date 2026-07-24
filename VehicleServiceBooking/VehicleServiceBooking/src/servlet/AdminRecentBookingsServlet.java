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



@WebServlet("/AdminRecentBookingsServlet")

public class AdminRecentBookingsServlet extends HttpServlet {



@Override

protected void doGet(HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {



response.setContentType("text/html");


PrintWriter out=response.getWriter();



try{


Connection con=DBConnection.getConnection();



String sql=

"SELECT users.name, "+
"bookings.vehicle_no, "+
"bookings.service, "+
"bookings.booking_date, "+
"bookings.status "+
"FROM bookings "+
"INNER JOIN users "+
"ON bookings.user_id = users.user_id "+
"ORDER BY bookings.booking_id DESC "+
"LIMIT 5";



PreparedStatement ps=con.prepareStatement(sql);



ResultSet rs=ps.executeQuery();



while(rs.next()){



out.println("<tr>");



out.println("<td>"+
rs.getString("name")+
"</td>");



out.println("<td>"+
rs.getString("vehicle_no")+
"</td>");



out.println("<td>"+
rs.getString("service")+
"</td>");



out.println("<td>"+
rs.getString("booking_date")+
"</td>");





String status=rs.getString("status");



if(status.equalsIgnoreCase("Pending")){


out.println(
"<td><span class='pending-status'>Pending</span></td>"
);


}


else if(status.equalsIgnoreCase("Approved")){


out.println(
"<td><span class='approved-status'>Approved</span></td>"
);


}


else if(status.equalsIgnoreCase("Completed")){


out.println(
"<td><span class='completed-status'>Completed</span></td>"
);


}


else{


out.println(
"<td><span class='cancelled-status'>Cancelled</span></td>"
);


}



out.println("</tr>");



}




rs.close();

ps.close();

con.close();



}


catch(Exception e){



e.printStackTrace();



out.println(

"<tr><td colspan='5'>No Recent Bookings</td></tr>"

);



}



}



}