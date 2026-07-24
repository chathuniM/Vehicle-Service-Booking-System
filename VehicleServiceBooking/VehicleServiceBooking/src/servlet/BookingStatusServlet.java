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


@WebServlet("/BookingStatusServlet")
public class BookingStatusServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();


        HttpSession session = request.getSession(false);


        // Check login
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

            "SELECT booking_date, vehicle_no, service, status FROM bookings WHERE user_id=? ORDER BY booking_id DESC"

            );


            ps.setInt(1,userId);



            ResultSet rs = ps.executeQuery();



            out.println("<table>");



            out.println("<tr>");

            out.println("<th>Date</th>");

            out.println("<th>Vehicle</th>");

            out.println("<th>Service</th>");

            out.println("<th>Status</th>");

            out.println("</tr>");



            boolean found = false;



            while(rs.next()){


                found = true;


                out.println("<tr>");



                out.println("<td>"
                + rs.getDate("booking_date")
                + "</td>");



                out.println("<td>"
                + rs.getString("vehicle_no")
                + "</td>");



                out.println("<td>"
                + rs.getString("service")
                + "</td>");



                String status = rs.getString("status");



                out.println("<td>");



                if(status.equalsIgnoreCase("Pending")){


                    out.println("<span class='pending'>Pending</span>");


                }
                else if(status.equalsIgnoreCase("Approved")){


                    out.println("<span class='approved'>Approved</span>");


                }
                else{


                    out.println("<span class='status'>"
                    + status
                    + "</span>");

                }



                out.println("</td>");



                out.println("</tr>");

            }



            if(!found){


                out.println("<tr>");

                out.println("<td colspan='4'>No Bookings Found</td>");

                out.println("</tr>");

            }



            out.println("</table>");



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
