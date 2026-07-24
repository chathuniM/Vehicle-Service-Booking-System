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

@WebServlet("/RecentBookingsServlet")
public class RecentBookingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userId")==null){
            return;
        }

        int userId = (Integer)session.getAttribute("userId");

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(

            "SELECT service, vehicle_no, booking_date, status "
          + "FROM bookings "
          + "WHERE user_id=? "
          + "ORDER BY booking_id DESC "
          + "LIMIT 5"

            );

            ps.setInt(1,userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                out.println("<tr>");

                out.println("<td>"+rs.getString("service")+"</td>");

                out.println("<td>"+rs.getString("vehicle_no")+"</td>");

                out.println("<td>"+rs.getDate("booking_date")+"</td>");

                String status = rs.getString("status");

                if(status.equalsIgnoreCase("Pending")){

                    out.println("<td><span class='badge pending'>Pending</span></td>");

                }
                else if(status.equalsIgnoreCase("Approved")){

                    out.println("<td><span class='badge approved'>Approved</span></td>");

                }
                else{

                    out.println("<td><span class='badge completed'>Completed</span></td>");

                }

                out.println("</tr>");

            }

            rs.close();
            ps.close();
            con.close();

        }
        catch(Exception e){

            e.printStackTrace();

        }

    }

}