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

@WebServlet("/ServiceDropdownServlet")
public class ServiceDropdownServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT service_name FROM services ORDER BY service_name"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.println("<option value='"
                        + rs.getString("service_name")
                        + "'>"
                        + rs.getString("service_name")
                        + "</option>");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
            out.println(e.getMessage());

        }

    }

}
