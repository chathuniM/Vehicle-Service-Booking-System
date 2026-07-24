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


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/UpdateBookingStatusServlet")
public class UpdateBookingStatusServlet extends HttpServlet {


@Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {


    response.setContentType("text/html");

    PrintWriter out = response.getWriter();



    String id = request.getParameter("id");

    String status = request.getParameter("status");



    try{


        if(id == null || status == null){


            out.println("<h3>Invalid Request</h3>");

            return;

        }



        Connection con = DBConnection.getConnection();



        PreparedStatement ps = con.prepareStatement(

        "UPDATE bookings SET status=? WHERE booking_id=?"

        );



        ps.setString(1,status);

        ps.setInt(2,Integer.parseInt(id));



        int result = ps.executeUpdate();



        if(result > 0){


            out.println("<script>");

            out.println("alert('Booking Status Updated Successfully');");

            out.println("window.location='adminBookings.html';");

            out.println("</script>");



        }
        else{


            out.println("<script>");

            out.println("alert('Booking Update Failed');");

            out.println("window.location='adminBookings.html';");

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