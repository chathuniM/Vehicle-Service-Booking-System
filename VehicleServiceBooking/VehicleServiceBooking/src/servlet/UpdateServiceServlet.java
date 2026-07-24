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

@WebServlet("/UpdateServiceServlet")
public class UpdateServiceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String id = request.getParameter("serviceId");
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE services SET service_name=?, description=?, price=? WHERE service_id=?");

            ps.setString(1, serviceName);
            ps.setString(2, description);
            ps.setDouble(3, Double.parseDouble(price));
            ps.setInt(4, Integer.parseInt(id));

            int result = ps.executeUpdate();

            if (result > 0) {

                out.println("<script>");
                out.println("alert('Service Updated Successfully');");
                out.println("window.location='manageServices.html';");
                out.println("</script>");

            } else {

                out.println("<script>");
                out.println("alert('Update Failed');");
                out.println("window.history.back();");
                out.println("</script>");

            }

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            out.println("<h3>Error : " + e.getMessage() + "</h3>");

        }

    }

}
