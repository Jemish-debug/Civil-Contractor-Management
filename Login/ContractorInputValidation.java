package Login;
import java.sql.*;
import java.util.Scanner;

public class ContractorInputValidation 
{
    String contractor_name, contractor_gmail, contractor_mobile, contractor_password;
    Scanner sc = new Scanner(System.in);

    
    void hold()
    {
        try 
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }
    void cls() 
    {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    public static void validateName(String contractor_name) throws InvalidNameException 
    {
        if (contractor_name == null || contractor_name.trim().isEmpty()) 
        {
            throw new InvalidNameException("Name cannot be null or empty.");
        }
        if (!contractor_name.matches("[A-Za-z ]+")) 
        {
            throw new InvalidNameException("Name can only contain letters and spaces.");
        }
    }

   
    public static void validateGmail(String contractor_gmail) throws InvalidGmailException 
    {
        if (contractor_gmail == null || contractor_gmail.trim().isEmpty()) 
        {
            throw new InvalidGmailException("Gmail cannot be null or empty.");
        }
        if (!contractor_gmail.endsWith("@gmail.com") && !contractor_gmail.endsWith("@outlook.com") && !contractor_gmail.endsWith("@yahoo.com")) 
        {
            throw new InvalidGmailException("Invalid Gmail format. It must be a valid Gmail address.");
        }
    }

    
    public static void validateMobileNumber(String contractor_mobile) throws InvalidMobileNumberException 
    {
        if (contractor_mobile == null || contractor_mobile.trim().isEmpty()) 
        {
            throw new InvalidMobileNumberException("Mobile number cannot be null or empty.");
        }
        if (!contractor_mobile.matches("\\d{10}")) 
        {
            throw new InvalidMobileNumberException("Mobile number must contain exactly 10 digits.");
        }
    }

    
    public static void validatePassword(String contractor_password) throws InvalidPasswordException 
    {
        if (contractor_password == null || contractor_password.trim().isEmpty()) 
        {
            throw new InvalidPasswordException("Password cannot be null or empty.");
        }
        if (contractor_password.contains(" ")) 
        {
            throw new InvalidPasswordException("Password must not contain any whitespace.");
        }
        if (contractor_password.length() <= 8) 
        {
            throw new InvalidPasswordException("Password must be greater than 8 characters.");
        }
    }

    public void contractorDetailsInput()
    {
        cls();
        System.out.println("Hang on a moment...");
        hold();
        cls();
        Connection conn = null;
        PreparedStatement pstmt = null;

        try 
        {
            
            conn = DBConnect.dbConnect("civilcontractormanagement");

            while (true) {
                try 
                {
                    
                    System.out.print("Enter your name: ");
                    contractor_name = sc.nextLine();
                    validateName(contractor_name);
                   

                    
                    System.out.print("Enter your gmail: ");
                    contractor_gmail = sc.nextLine();
                    validateGmail(contractor_gmail);
                    

                    
                    System.out.print("Enter your mobile number: ");
                    contractor_mobile = sc.nextLine();
                    validateMobileNumber(contractor_mobile);
                    

                    
                    System.out.print("Enter your password: ");
                    contractor_password = sc.nextLine();
                    validatePassword(contractor_password);
                        

                    
                    String sql = "INSERT INTO contractor_data (Name, PhoneNumber, Gmail, Password) VALUES (?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, contractor_name);
                    pstmt.setString(2, contractor_mobile);
                    pstmt.setString(3, contractor_gmail);
                    pstmt.setString(4, contractor_password);
                    pstmt.executeUpdate();

                    System.out.println("Contractor information is valid and inserted into the database.");
                    break; 
                } 
                catch (InvalidNameException | InvalidGmailException | InvalidMobileNumberException | InvalidPasswordException e) 
                {
                    System.out.println("Validation Error: " + e.getMessage());
                    System.out.println("Please try again.");
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
