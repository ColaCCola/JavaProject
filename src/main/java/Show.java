import common.DbUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Show", value = "/Show")
public class Show extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        StringBuilder json=new StringBuilder();
        json.append("[");
        String jsonStr="";
        try{
            con= DbUtils.getConnection();
            String sql="select * from msg";
            pstmt=con.prepareStatement(sql);
            rs= pstmt.executeQuery();
            while(rs.next()){
                String flightNumber=rs.getString(1);
                String airlineCompany=rs.getString(2);
                String origin=rs.getString(3);
                String destination=rs.getString(4);
                String stopOver=rs.getString(5);
                String departureTime=rs.getString(6);
                String arrivalTime=rs.getString(7);
                String terminal=rs.getString(8);
                String boardingGate=rs.getString(9);
                String CheckInCounter=rs.getString(10);
                String flightStatus=rs.getString(11);
                json.append("{\"航班号\":\""+flightNumber+"\",");
                json.append("\"航空公司\":\""+airlineCompany+"\",");
                json.append("\"始发地\":\""+origin+"\",");
                json.append("\"目的地\":\""+destination+"\",");
                json.append("\"经停\":\""+stopOver+"\",");
                json.append("\"计划起飞时间\":\""+departureTime+"\",");
                json.append("\"计划到达时间\":\""+arrivalTime+"\",");
                json.append("\"航站楼\":\""+terminal+"\",");
                json.append("\"登机口\":\""+boardingGate+"\",");
                json.append("\"值机柜台\":\""+CheckInCounter+"\",");
                json.append("\"航班状态\":\""+flightStatus+"\"},");
            }
            jsonStr=json.substring(0,json.length()-1)+"]";
            out.println(jsonStr);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            DbUtils.closeConnection(rs,pstmt,con);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
