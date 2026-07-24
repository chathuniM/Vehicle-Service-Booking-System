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



@WebServlet("/AdminBookingServlet")
public class AdminBookingServlet extends HttpServlet {


@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {



    response.setContentType("text/html");

    PrintWriter out = response.getWriter();



    try{


        Connection con = DBConnection.getConnection();



        PreparedStatement ps = con.prepareStatement(


        "SELECT bookings.booking_id, " +
        "users.name, " +
        "bookings.vehicle_no, " +
        "bookings.service, " +
        "bookings.booking_date, " +
        "bookings.status " +
        "FROM bookings " +
        "INNER JOIN users " +
        "ON bookings.user_id = users.user_id " +
        "ORDER BY bookings.booking_id DESC"


        );



        ResultSet rs = ps.executeQuery();



        boolean found = false;



        while(rs.next()){


            found = true;


            out.println("<tr>");



            // Booking ID

            out.println("<td>"
            + rs.getInt("booking_id")
            + "</td>");



            // Customer Name

            out.println("<td>"
            + rs.getString("name")
            + "</td>");



            // Vehicle Number

            out.println("<td>"
            + rs.getString("vehicle_no")
            + "</td>");



            // Service

            out.println("<td>"
            + rs.getString("service")
            + "</td>");



            // Date

            out.println("<td>"
            + rs.getDate("booking_date")
            + "</td>");





            // Status Badge

            String status = rs.getString("status");


            out.println("<td>");



            if(status == null){

                out.println(
                "<span class='pending-status'>Pending</span>"
                );

            }


            else if(status.equals("Pending")){


                out.println(
                "<span class='pending-status'>Pending</span>"
                );


            }


            else if(status.equals("Approved")){


                out.println(
                "<span class='approved-status'>Approved</span>"
                );


            }


            else if(status.equals("Completed")){


                out.println(
                "<span class='completed-status'>Completed</span>"
                );


            }


            else{


                out.println(status);


            }



            out.println("</td>");







            // Action Buttons

            out.println("<td>");



            out.println(

            "<a class='approve-btn' href='UpdateBookingStatusServlet?id="
            + rs.getInt("booking_id")
            + "&status=Approved'>Approve</a>"


            );



            out.println("&nbsp;&nbsp;");



            out.println(

            "<a class='complete-btn' href='UpdateBookingStatusServlet?id="
            + rs.getInt("booking_id")
            + "&status=Completed'>Complete</a>"


            );



            out.println("</td>");





            out.println("</tr>");



        }





        if(!found){


            out.println("<tr>");

            out.println(
            "<td colspan='7'>No Bookings Found</td>"
            );

            out.println("</tr>");


        }





        rs.close();

        ps.close();

        con.close();



    }

    catch(Exception e){


        e.printStackTrace();


        out.println("<tr>");

        out.println(
        "<td colspan='7'>Error Loading Bookings</td>"
        );

        out.println("</tr>");


    }



}



}