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
import jakarta.servlet.http.HttpSession;


@WebServlet("/AddVehicleServlet")
public class AddVehicleServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();



        // Get logged user session
        HttpSession session = request.getSession(false);



        if(session == null || session.getAttribute("userId") == null){


            out.println("<script>");
            out.println("alert('Please Login First');");
            out.println("window.location='login.html';");
            out.println("</script>");

            return;

        }



        int userId = (Integer) session.getAttribute("userId");



        String vehicleNo = request.getParameter("vehicleNo");
        String type = request.getParameter("type");
        String brand = request.getParameter("brand");



        try {


            Connection con = DBConnection.getConnection();



            PreparedStatement ps = con.prepareStatement(

                "INSERT INTO vehicles(user_id, vehicle_no, type, brand) VALUES(?,?,?,?)"

            );



            ps.setInt(1, userId);

            ps.setString(2, vehicleNo);

            ps.setString(3, type);

            ps.setString(4, brand);



            int result = ps.executeUpdate();



            if(result > 0){


                out.println("<script>");

                out.println("alert('Vehicle Added Successfully');");

                out.println("window.location='vehicles.html';");

                out.println("</script>");


            }
            else{


                out.println("<script>");

                out.println("alert('Vehicle Adding Failed');");

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