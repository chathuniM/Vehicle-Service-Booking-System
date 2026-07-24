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



@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {



@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)

throws ServletException, IOException {



response.setContentType("text/html");

PrintWriter out = response.getWriter();




// Get Session

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

"SELECT * FROM users WHERE user_id=?"

);



ps.setInt(1,userId);



ResultSet rs = ps.executeQuery();






// HTML START


out.println("<html>");

out.println("<head>");



out.println("<title>My Profile</title>");




// CSS

out.println("<link rel='stylesheet' href='"
+ request.getContextPath()
+ "/css/customer.css'>");




// Font Awesome

out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css'>");



out.println("</head>");





out.println("<body>");





out.println("<div class='wrapper'>");






// MAIN CONTENT


out.println("<div class='main'>");






// HEADER


out.println("<div class='header'>");



out.println("<div>");

out.println("<h2>My Profile</h2>");

out.println("<p>View and manage your account details</p>");

out.println("</div>");




out.println("<div class='header-right'>");



out.println("<div class='profile'>");

out.println("<i class='fa-solid fa-user'></i>");

out.println("</div>");



out.println("<a class='logout' href='LogoutServlet'>");

out.println("<i class='fa-solid fa-right-from-bracket'></i> Logout");

out.println("</a>");



out.println("</div>");



out.println("</div>");









if(rs.next()){






out.println("<div class='profile-container'>");



out.println("<div class='profile-card-new'>");





// PROFILE TOP


out.println("<div class='profile-top'>");



out.println("<div class='profile-image-new'>");

out.println("<i class='fa-solid fa-user'></i>");

out.println("</div>");



out.println("<h2>Customer Profile</h2>");

out.println("<p>Your personal information</p>");



out.println("</div>");









// DETAILS


out.println("<div class='profile-details'>");




out.println("<div class='detail-box'>");

out.println("<i class='fa-solid fa-id-card'></i>");

out.println("<div>");

out.println("<h4>User ID</h4>");

out.println("<p>"
+ rs.getInt("user_id")
+ "</p>");

out.println("</div>");

out.println("</div>");








out.println("<div class='detail-box'>");

out.println("<i class='fa-solid fa-user'></i>");

out.println("<div>");

out.println("<h4>Name</h4>");

out.println("<p>"
+ rs.getString("name")
+ "</p>");

out.println("</div>");

out.println("</div>");








out.println("<div class='detail-box'>");

out.println("<i class='fa-solid fa-envelope'></i>");

out.println("<div>");

out.println("<h4>Email</h4>");

out.println("<p>"
+ rs.getString("email")
+ "</p>");

out.println("</div>");

out.println("</div>");









out.println("<div class='detail-box'>");

out.println("<i class='fa-solid fa-phone'></i>");

out.println("<div>");

out.println("<h4>Phone Number</h4>");

out.println("<p>"
+ rs.getString("phone")
+ "</p>");

out.println("</div>");

out.println("</div>");








out.println("<div class='detail-box'>");

out.println("<i class='fa-solid fa-shield'></i>");

out.println("<div>");

out.println("<h4>Role</h4>");

out.println("<p>"
+ rs.getString("role")
+ "</p>");

out.println("</div>");

out.println("</div>");





out.println("</div>");









// BUTTONS


out.println("<div class='profile-buttons'>");



out.println("<a class='btn btn-primary' href='"
+ request.getContextPath()
+ "/updateProfile.html'>");

out.println("<i class='fa-solid fa-pen'></i>");

out.println(" Update Profile");

out.println("</a>");






out.println("<a class='btn btn-secondary' href='"
+ request.getContextPath()
+ "/dashboard.html'>");


out.println("<i class='fa-solid fa-arrow-left'></i>");

out.println(" Back");


out.println("</a>");




out.println("</div>");








out.println("</div>");

out.println("</div>");





}
else{


out.println("<h3>User details not found</h3>");

}





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


out.println("<h3>Error : "
+ e.getMessage()
+ "</h3>");

}



}



}