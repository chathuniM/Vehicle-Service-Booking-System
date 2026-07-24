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


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");


        try {


            Connection con = DBConnection.getConnection();


            if(con == null){

                out.println("<h3>Database connection failed</h3>");
                return;

            }


            PreparedStatement ps =
            con.prepareStatement(
            "INSERT INTO users(name,email,password,phone) VALUES(?,?,?,?)"
            );


            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);


            int result = ps.executeUpdate();


            if(result > 0){


                out.println("<script>");
                out.println("alert('Registration Successful');");
                out.println("window.location='login.html';");
                out.println("</script>");


            }
            else{


                out.println("<h3>Registration Failed</h3>");


            }


        } 
        catch(Exception e){


            e.printStackTrace();

            out.println("<h3>Error: " 
                    + e.getMessage() 
                    + "</h3>");


        }


    }

}