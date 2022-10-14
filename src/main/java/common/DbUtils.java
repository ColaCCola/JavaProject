package common;

import java.sql.*;

public class DbUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project?&useSSL=false&serverTimezone=UTC","root","loveCola");
        return conn;
    }

    public static void closeConnection(ResultSet rs, Statement stmt, Connection con){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(stmt!=null){
                stmt.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(con!=null&&!con.isClosed()){
                con.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
