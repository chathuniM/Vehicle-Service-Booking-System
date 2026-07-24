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



@WebServlet("/AdminChartServlet")

public class AdminChartServlet extends HttpServlet {


@Override

protected void doGet(HttpServletRequest request,
HttpServletResponse response)

throws ServletException, IOException {



response.setContentType("application/json");


PrintWriter out=response.getWriter();



int pending = 0;
int approved = 0;
int completed = 0;



String[] months = {
"Jan",
"Feb",
"Mar",
"Apr",
"May",
"Jun",
"Jul",
"Aug",
"Sep",
"Oct",
"Nov",
"Dec"
};



int[] monthCounts = new int[12];




try {



Connection con = DBConnection.getConnection();





// STATUS COUNT


PreparedStatement ps1 = con.prepareStatement(
"SELECT status, COUNT(*) FROM bookings GROUP BY status"
);


ResultSet rs1 = ps1.executeQuery();



while(rs1.next()){


String status = rs1.getString("status");


int count = rs1.getInt(2);



if(status.equalsIgnoreCase("Pending")){


pending = count;


}

else if(status.equalsIgnoreCase("Approved")){


approved = count;


}

else if(status.equalsIgnoreCase("Completed")){


completed = count;


}



}





// MONTHLY BOOKINGS


PreparedStatement ps2 = con.prepareStatement(

"SELECT MONTH(booking_date), COUNT(*) " +
"FROM bookings " +
"GROUP BY MONTH(booking_date)"

);



ResultSet rs2 = ps2.executeQuery();




while(rs2.next()){


int month = rs2.getInt(1);


int count = rs2.getInt(2);



monthCounts[month-1] = count;


}





rs1.close();

rs2.close();


ps1.close();

ps2.close();


con.close();






out.print("{");



out.print("\"pending\":"+pending+",");


out.print("\"approved\":"+approved+",");


out.print("\"completed\":"+completed+",");





out.print("\"months\":[");



for(int i=0;i<12;i++){


out.print("\""+months[i]+"\"");


if(i<11){

out.print(",");

}

}


out.print("],");





out.print("\"monthCounts\":[");



for(int i=0;i<12;i++){


out.print(monthCounts[i]);


if(i<11){

out.print(",");

}


}



out.print("]");



out.print("}");






}

catch(Exception e){



e.printStackTrace();



out.print("{");

out.print("\"pending\":0,");

out.print("\"approved\":0,");

out.print("\"completed\":0,");

out.print("\"months\":[],");

out.print("\"monthCounts\":[]");


out.print("}");



}



}



}