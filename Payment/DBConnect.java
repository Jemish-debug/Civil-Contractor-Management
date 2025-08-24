package Payment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect 
{
    
    public Connection dbConnect(String dbName) 
    {
        Connection con = null;
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + dbName;
            con = DriverManager.getConnection(url, "root", "");
            System.out.println("Connection to database " + dbName + " established.");

        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } 
        catch (SQLException e) 
        {
            System.out.println("Error establishing connection: " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}
