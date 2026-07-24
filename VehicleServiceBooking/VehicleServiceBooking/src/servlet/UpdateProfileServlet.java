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


@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request,
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



        String name = request.getParameter("name");
        String phone = request.getParameter("phone");



        try {


            Connection con = DBConnection.getConnection();



            String sql = "UPDATE users SET ";


            boolean first = true;



            if(name != null && !name.trim().isEmpty()){


                sql += "name=?";

                first = false;

            }



            if(phone != null && !phone.trim().isEmpty()){


                if(!first){

                    sql += ", ";

                }


                sql += "phone=?";


            }



            sql += " WHERE user_id=?";



            PreparedStatement ps = con.prepareStatement(sql);



            int index = 1;



            if(name != null && !name.trim().isEmpty()){


                ps.setString(index, name);

                index++;


                // update session name
                session.setAttribute("name", name);

            }



            if(phone != null && !phone.trim().isEmpty()){


                ps.setString(index, phone);

                index++;

            }



            ps.setInt(index, userId);



            int result = ps.executeUpdate();



            if(result > 0){


                out.println("<script>");

                out.println("alert('Profile Updated Successfully');");

                out.println("window.location='ProfileServlet';");

                out.println("</script>");


            }
            else{


                out.println("<script>");

                out.println("alert('No Changes Made');");

                out.println("window.location='ProfileServlet';");

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
