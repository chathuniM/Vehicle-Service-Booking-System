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


@WebServlet("/ReminderServlet")
public class ReminderServlet extends HttpServlet {


@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {


response.setContentType("text/html");
PrintWriter out = response.getWriter();



HttpSession session = request.getSession(false);



if(session == null || session.getAttribute("userId") == null){


    out.println("<script>");
    out.println("alert('Please Login First');");
    out.println("window.location='login.html';");
    out.println("</script>");

    return;

}



int userId = (Integer) session.getAttribute("userId");



try{


Connection con = DBConnection.getConnection();



PreparedStatement ps = con.prepareStatement(

"SELECT sr.*, v.vehicle_no, v.brand, v.type " +
"FROM service_reminder sr " +
"JOIN vehicles v ON sr.vehicle_id=v.vehicle_id " +
"WHERE sr.user_id=?"

);



ps.setInt(1,userId);


ResultSet rs = ps.executeQuery();





out.println("<html>");
out.println("<head>");

out.println("<title>Service Reminders</title>");

out.println("<link rel='stylesheet' href='"
+request.getContextPath()
+"/css/customer.css'>");


out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css'>");


out.println("</head>");



out.println("<body>");



out.println("<div class='wrapper'>");





// SIDEBAR

out.println("<aside class='sidebar'>");


out.println("<div class='logo'>");

out.println("<i class='fa-solid fa-car'></i>");

out.println("<h2>Vehicle Service</h2>");

out.println("<p>Customer Panel</p>");

out.println("</div>");



out.println("<ul>");



out.println("<li><a href='dashboard.html'><i class='fa-solid fa-house'></i><span>Dashboard</span></a></li>");

out.println("<li><a href='addVehicle.html'><i class='fa-solid fa-car'></i><span>Add Vehicle</span></a></li>");

out.println("<li><a href='vehicles.html'><i class='fa-solid fa-list'></i><span>My Vehicles</span></a></li>");

out.println("<li><a href='booking.html'><i class='fa-solid fa-calendar-check'></i><span>Book Service</span></a></li>");

out.println("<li><a href='bookingStatus.html'><i class='fa-solid fa-clock'></i><span>Booking Status</span></a></li>");

out.println("<li><a href='serviceHistory.html'><i class='fa-solid fa-history'></i><span>Service History</span></a></li>");

out.println("<li><a href='ProfileServlet'><i class='fa-solid fa-user'></i><span>My Profile</span></a></li>");

out.println("<li><a class='active' href='reminder.html'><i class='fa-solid fa-bell'></i><span>Service Reminder</span></a></li>");

out.println("<li><a href='LogoutServlet'><i class='fa-solid fa-right-from-bracket'></i><span>Logout</span></a></li>");



out.println("</ul>");

out.println("</aside>");





// MAIN

out.println("<div class='main'>");




// HEADER

out.println("<div class='header'>");


out.println("<div>");

out.println("<h2>Service Reminder</h2>");

out.println("<p>Manage your vehicle maintenance schedule</p>");

out.println("</div>");



out.println("<div class='header-right'>");

out.println("<div class='profile'>");

out.println("<i class='fa-solid fa-user'></i>");

out.println("</div>");



out.println("<a href='LogoutServlet' class='logout'>");

out.println("<i class='fa-solid fa-right-from-bracket'></i> Logout");

out.println("</a>");



out.println("</div>");



out.println("</div>");





out.println("<div class='page-title'>");

out.println("<h1>My Service Reminders</h1>");

out.println("<p>Upcoming vehicle maintenance details</p>");

out.println("</div>");






boolean found=false;



while(rs.next()){


found=true;


String status=rs.getString("status");


String statusClass="";


if(status.equalsIgnoreCase("Upcoming")){

statusClass="upcoming";

}
else if(status.equalsIgnoreCase("Completed")){

statusClass="completed";

}
else if(status.equalsIgnoreCase("Overdue")){

statusClass="overdue";

}



out.println("<div class='reminder-card'>");



out.println("<h3 class='reminder-title'>");

out.println("<i class='fa-solid fa-car'></i> Vehicle Reminder");

out.println("</h3>");



out.println("<p><b>Vehicle No :</b> "
+rs.getString("vehicle_no")
+"</p>");



out.println("<p><b>Brand :</b> "
+rs.getString("brand")
+"</p>");



out.println("<p><b>Type :</b> "
+rs.getString("type")
+"</p>");



out.println("<p><b>Service Date :</b> "
+rs.getDate("service_date")
+"</p>");



out.println("<p><b>Next Service Date :</b> "
+rs.getDate("next_service_date")
+"</p>");



out.println("<p><b>Status :</b> ");

out.println("<span class='status "+statusClass+"'>");

out.println(status);

out.println("</span></p>");



out.println("</div>");



}



if(!found){


out.println("<div class='empty-box'>");

out.println("<i class='fa-solid fa-bell-slash'></i>");

out.println("<h3>No Reminders Found</h3>");

out.println("<p>You don't have any upcoming services.</p>");

out.println("</div>");


}





out.println("<br>");

out.println("<a href='dashboard.html' class='btn btn-primary'>");

out.println("<i class='fa-solid fa-arrow-left'></i> Back to Dashboard");

out.println("</a>");



out.println("</div>");

out.println("</div>");

out.println("</body>");

out.println("</html>");



rs.close();
ps.close();
con.close();



}
catch(Exception e){


e.printStackTrace();

out.println("<h3>Error : "+e.getMessage()+"</h3>");


}



}



}