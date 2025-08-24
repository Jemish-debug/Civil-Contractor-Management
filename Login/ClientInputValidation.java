package Login;
import java.sql.*;
import java.util.Scanner;

public class ClientInputValidation 
{
    String client_name, client_gmail, client_mobile, client_password;
    Scanner sc = new Scanner(System.in);

    
    public static void validateName(String client_name) throws InvalidNameException
    {
        if (client_name == null || client_name.trim().isEmpty()) 
        {
            throw new InvalidNameException("Name cannot be null or empty.");
        }
        if (!client_name.matches("[A-Za-z ]+")) 
        {
            throw new InvalidNameException("Name can only contain letters and spaces.");
        }
    }

    
    public static void validateGmail(String client_gmail) throws InvalidGmailException 
    {
        if (client_gmail == null || client_gmail.trim().isEmpty()) 
        {
            throw new InvalidGmailException("Gmail cannot be null or empty.");
        }
        if (!client_gmail.endsWith("@gmail.com") && !client_gmail.endsWith("@outlook.com") && !client_gmail.endsWith("@yahoo.com")) 
        {
            throw new InvalidGmailException("Invalid gmail format. It must be a valid gmail address.");
        }
    }

   
    public static void validateMobileNumber(String client_mobile) throws InvalidMobileNumberException 
    {
        if (client_mobile == null || client_mobile.trim().isEmpty()) 
        {
            throw new InvalidMobileNumberException("Mobile number cannot be null or empty.");
        }
        if (!client_mobile.matches("\\d{10}")) 
        {
            throw new InvalidMobileNumberException("Mobile number must contain exactly 10 digits.");
        }
    }

    
    public static void validatePassword(String client_password) throws InvalidPasswordException {
        if (client_password == null || client_password.trim().isEmpty()) {
            throw new InvalidPasswordException("Password cannot be null or empty.");
        }
        if (client_password.contains(" ")) {
            throw new InvalidPasswordException("Password must not contain any whitespace.");
        }
        if (client_password.length() <= 8) {
            throw new InvalidPasswordException("Password must be greater than 8 characters.");
        }
    }

    public void clientDetailsInput() 
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try 
        {
            
            conn = DBConnect.dbConnect("civilcontractormanagement");

            while (true) {
                try {
                    
                    System.out.print("Enter your name: ");
                    client_name = sc.nextLine();
                    validateName(client_name);

                    
                    System.out.print("Enter your gmail: ");
                    client_gmail = sc.next();
                    validateGmail(client_gmail);

                    
                    System.out.print("Enter your mobile number: ");
                    client_mobile = sc.next();
                    validateMobileNumber(client_mobile);

              
                    System.out.print("Enter your password: ");
                    client_password = sc.next();
                    validatePassword(client_password);

                    
                    String sql = "INSERT INTO client_data (Name, PhoneNumber, Gmail, Password) VALUES (?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, client_name);
                    pstmt.setString(2, client_mobile);
                    pstmt.setString(3, client_gmail);
                    pstmt.setString(4, client_password);
                    pstmt.executeUpdate();

                    System.out.println("Client information is valid and inserted into the database.");
                    break; 
                } 
                catch (InvalidNameException | InvalidGmailException | InvalidMobileNumberException | InvalidPasswordException e) 
                {
                    System.out.println("Validation Error: " + e.getMessage());
                    System.out.println("Please try again.\n");
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            
            try 
            {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }
}
