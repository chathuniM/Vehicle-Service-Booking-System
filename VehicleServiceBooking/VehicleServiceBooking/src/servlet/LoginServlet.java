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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String email = request.getParameter("email");
        String password = request.getParameter("password");


        try {


            Connection con = DBConnection.getConnection();


            if(con == null){

                out.println("<h3>Database connection failed</h3>");
                return;

            }


            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?"
            );


            ps.setString(1, email);
            ps.setString(2, password);


            ResultSet rs = ps.executeQuery();


if (rs.next()) {

    HttpSession session = request.getSession();

    session.setAttribute("userId", rs.getInt("user_id"));
    session.setAttribute("name", rs.getString("name"));
    session.setAttribute("email", rs.getString("email"));
    session.setAttribute("role", rs.getString("role"));

    out.println("<script>");
    out.println("alert('Login Successful');");
    out.println("window.location='dashboard.html';");
    out.println("</script>");

}       else{


                out.println("<script>");
                out.println("alert('Invalid Email or Password');");
                out.println("window.location='login.html';");
                out.println("</script>");


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
