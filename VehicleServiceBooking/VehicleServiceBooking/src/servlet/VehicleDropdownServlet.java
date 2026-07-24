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


@WebServlet("/VehicleDropdownServlet")
public class VehicleDropdownServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();


        HttpSession session = request.getSession(false);


        if(session == null || session.getAttribute("userId") == null){

            out.println("<option>Please Login First</option>");
            return;

        }


        int userId = (Integer) session.getAttribute("userId");


        try{


            Connection con = DBConnection.getConnection();


            PreparedStatement ps = con.prepareStatement(
                "SELECT vehicle_no FROM vehicles WHERE user_id=?"
            );


            ps.setInt(1, userId);


            ResultSet rs = ps.executeQuery();



            while(rs.next()){


                out.println("<option value='"
                        + rs.getString("vehicle_no")
                        + "'>");

                out.println(rs.getString("vehicle_no"));

                out.println("</option>");


            }



            rs.close();

            ps.close();

            con.close();



        }
        catch(Exception e){


            e.printStackTrace();

            out.println("<option>Error Loading Vehicles</option>");

        }


    }

}