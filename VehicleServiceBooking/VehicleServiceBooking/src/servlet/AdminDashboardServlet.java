/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java
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

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        int users = 0;
        int vehicles = 0;
        int bookings = 0;
        int services = 0;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps1 =
                    con.prepareStatement("SELECT COUNT(*) FROM users");
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                users = rs1.getInt(1);
            }

            PreparedStatement ps2 =
                    con.prepareStatement("SELECT COUNT(*) FROM vehicles");
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                vehicles = rs2.getInt(1);
            }

            PreparedStatement ps3 =
                    con.prepareStatement("SELECT COUNT(*) FROM bookings");
            ResultSet rs3 = ps3.executeQuery();

            if (rs3.next()) {
                bookings = rs3.getInt(1);
            }

            PreparedStatement ps4 =
                    con.prepareStatement("SELECT COUNT(*) FROM services");
            ResultSet rs4 = ps4.executeQuery();

            if (rs4.next()) {
                services = rs4.getInt(1);
            }

            out.print("{");
            out.print("\"users\":" + users + ",");
            out.print("\"vehicles\":" + vehicles + ",");
            out.print("\"bookings\":" + bookings + ",");
            out.print("\"services\":" + services);
            out.print("}");

            rs1.close();
            rs2.close();
            rs3.close();
            rs4.close();

            ps1.close();
            ps2.close();
            ps3.close();
            ps4.close();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            out.print("{");
            out.print("\"users\":0,");
            out.print("\"vehicles\":0,");
            out.print("\"bookings\":0,");
            out.print("\"services\":0");
            out.print("}");

        }

    }

}