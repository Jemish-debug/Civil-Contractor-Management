package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    static Connection dbConnect(String db) 
    {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        String DB_URL = "jdbc:mysql://localhost/" + db;
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        try 
        {
            Class.forName(JDBC_DRIVER);
            //System.out.println("MySQL Driver Loaded Successfully ....");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected established successfully...");
        } 
        catch (SQLException se) 
        {
            se.printStackTrace();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return conn;
    }
}
