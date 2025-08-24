package Sign_in;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInDB 
{


    private Connection connect() 
    {
        
        String db = "civilcontractormanagement";
        String url = "jdbc:mysql://localhost/"+ db;
        String user = "root";
        String password = "";
        
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        
        return conn;
    }
    
    
    public boolean checkLogin(String tableName, String username, String password) 
    {
        Connection conn = connect();
        boolean isAuthenticated = false;
        
        if (conn != null) {
            
            String sql = "SELECT * FROM " +tableName+ " WHERE Name = ? AND Password = ?";
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                
                ResultSet rs = pstmt.executeQuery();
                
                // If a record is found, credentials are correct
                if (rs.next()) {
                    isAuthenticated = true;
                }
                
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        
        return isAuthenticated;
    }
}
