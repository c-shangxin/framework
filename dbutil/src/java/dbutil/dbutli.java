package dbutil;

import java.sql.*;

/**
 * Created by csx on 2017/12/7.
 */
public class dbutli {
    private static Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/mydb";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String pwd = "root";
    //链接数据库
    private static Connection getConnection(){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //关闭
    public static void close(Connection conn , Statement stmt , ResultSet rst){
        if (rst != null){
            try {
                rst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt !=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        System.out.println(conn);
    }
}
