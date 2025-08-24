package Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Admin 
{
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
    public void viewClientDetails() 
    {
        try 
        {
            
            Connection con = dbConnect();
            if (con == null) 
            {
                System.out.println("Connection to database failed!");
                return;
            }

            
            Statement stmt = con.createStatement();

            
            String query = "SELECT Name, PhoneNumber, Gmail FROM client_data";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Client Details:");
            System.out.println("+------------------+---------------------+-------------------------------+");
            System.out.println("| Name             | Phone Number        | Gmail                         |");
            System.out.println("+------------------+---------------------+-------------------------------+");
            while (rs.next()) 
            {
                String name = rs.getString("Name");
                String phone = rs.getString("PhoneNumber");
                String gmail = rs.getString("Gmail");
                System.out.printf("| %-16s | %-19s | %-29s |\n", name, phone, gmail);
            }

            System.out.println("+------------------+---------------------+-------------------------------+");

            
            con.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Error fetching client details: " + e.getMessage());
            e.printStackTrace();  
        }
    }

   
    public void viewClientTemplates() 
    {
        try 
        {
            
            Connection con = dbConnect();
            if (con == null) 
            {
                System.out.println("Connection to database failed!");
                return;
            }

            
            Statement stmt = con.createStatement();

            
            String query = "SELECT Template, City, Area FROM client_templates";
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Client Template Selections:");
            System.out.println("+----------------+-------------------+-------------------+");
            System.out.println("| Template       | City              | Area              |");
            System.out.println("+----------------+-------------------+-------------------+");

            
            while (rs.next()) 
            {
                String template = rs.getString("Template");
                String city = rs.getString("City");
                String area = rs.getString("Area");

                System.out.printf("| %-14s | %-17s | %-17s |\n", template, city, area);
            }
            System.out.println("+----------------+-------------------+-------------------+");

        
            con.close();
        } 
        catch (Exception e) 
        {
            cls();
            hold();
            System.out.println("Error fetching client templates: " + e.getMessage());
            e.printStackTrace();  
        }
    }

    public void viewClientPayment() 
    {
        try 
        {
            Connection con = dbConnect();
            if (con == null) 
            {
                System.out.println("Connection to database failed!");
                return;
            }
    
            Statement stmt = con.createStatement();
    
            String query = "SELECT * FROM payment";
            ResultSet rs = stmt.executeQuery(query);
    
            System.out.println("Client Payment:");
            System.out.println("+--------------------+------------------+------------------+------------------+-------------------+-------------+");
            System.out.println("| Account No         | Name             | Template         | City             | Amount            | Date        |");
            System.out.println("+--------------------+------------------+------------------+------------------+-------------------+-------------+");
    
            while (rs.next()) 
            {
                String acc_no = rs.getString("Acc_no");
                String name = rs.getString("Name");
                String template = rs.getString("Template");
                String city = rs.getString("City");
                String amt = String.format("%.2f", rs.getDouble("Amount"));
                String date = rs.getString("Date");
                System.out.printf("| %-18s | %-16s | %-16s | %-16s | %-17s | %-11s |\n", acc_no, name, template, city, amt, date);
            }
            System.out.println("+--------------------+------------------+------------------+------------------+-------------------+-------------+");
    
            con.close();
        } 
        catch (Exception e) 
        {
            cls();
            hold();
            System.out.println("Error fetching client payment: " + e.getMessage());
            e.printStackTrace();
        }
    }   
    

    
    private Connection dbConnect() 
    {
        Connection con = null;
        try 
        {
            
            String url = "jdbc:mysql://localhost:3306/civilcontractormanagement";
            String user = "root";  
            String password = "";  

            
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!"); 
        } 
        catch (Exception e) 
        {
            System.out.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();  
        }
        return con;
    }

    public void showMenu() 
    {
        
        cls();
        hold();
        int choice = 0;

        
        while (choice != 4) 
        {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Client Details");
            System.out.println("2. View Client Template Selections");
            System.out.println("3. View Client Payment");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try 
            {
                
                choice = sc.nextInt();  
                switch (choice) 
                {
                    case 1:
                        cls();
                        System.out.println("Wait while will fetching the data...");
                        hold();
                        cls();
                        viewClientDetails();
                       break;
                    case 2:
                        cls();
                        System.out.println("Wait while will fetching the data...");
                        hold();
                        cls();
                        viewClientTemplates();
                        break;
                    case 3:
                        cls();
                        System.out.println("Wait while will fetching the data...");
                        hold();
                        cls();
                        viewClientPayment();
                        
                        break;
                    case 4:
                        cls();
                        System.out.println("Exiting...");
                        hold();
                        cls();
                    default:
                        System.out.println("Please enter a valid choice.");
                        break;
                }
                hold();
                
            } 
            catch (NumberFormatException e) 
            {
                
                System.out.println("Invalid input, please enter a number.");
                cls();
            }
        }
    }
}
