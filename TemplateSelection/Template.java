package TemplateSelection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

interface Initial 
{
    double MIN_AREA = 250;
    double MAX_AREA_1 = 3000;
    double MAX_AREA_2 = 10000;
    double MAX_AREA_3 = 80000;
    double MAX_AREA_4 = 217800;
    void selectTemplate();
}

class MainLevel 
{
    void levelOne() {
        System.out.println("1 BHK");
    }

    void levelTwo() {
        System.out.println("2 BHK");
    }

    void levelThree() {
        System.out.println("3 BHK");
    }

    void levelFour() {
        System.out.println("4 BHK");
    }

    void levelFive() {
        System.out.println("Penthouse (5 BHK)");
    }

    void levelSix() {
        System.out.println("Duplex (4MBHK)");
    }

    void levelSeven() {
        System.out.println("Triplex (5MBHK + Storeroom)");
    }

    void levelEight() {
        System.out.println("Mansion");
    }

    void levelNine() {
        System.out.println("Farmhouse");
    }
}

public class Template extends MainLevel implements Initial 
{
    private double area;
    private String template, city;
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
    
    public double setTemplate() 
    {
        cls();
        System.out.print("Enter your area in sq. feet: ");
        area = sc.nextDouble();
        return area;
    }

    public void getTemplate() 
    {
        sc.nextLine();
        System.out.print("Select a template from above: ");
        template = sc.nextLine();
        System.out.print("Enter your city: ");
        city = sc.next(); 
        System.out.println("You have selected: " + template);
        System.out.println("Your city is: " + city);
        System.out.println("Your area is " + area + " sq. ft.");
    }
    
    public void selectTemplate() 
    {
        cls();
        hold();
        System.out.println("Time to select a template.....");
        hold();

        double area = setTemplate();
        if (area < 0) 
        {
            cls();
            System.out.println("Enter a valid area!!!");
            setTemplate();
            selectTemplate();
            return;
        }

        if (area >= MIN_AREA && area <= MAX_AREA_1) 
        {
            if (area <= 400) 
            {
                cls();
                System.out.println("Provided Suitable Template is:");
                levelOne();
                getTemplate();
            } 
            else if (area <= 600) 
            {
                cls();
                System.out.println("Provided and Suitable Templates are:");
                levelOne();
                levelTwo();
                getTemplate();
            } 
            else if (area <= 900) 
            {
                cls();
                System.out.println("Provided and Suitable Templates are:");
                levelTwo();
                levelThree();
                getTemplate();
            } 
            else if (area <= 1100) 
            {
                System.out.println("Provided and Suitable Templates are:");
                levelTwo();
                levelThree();
                levelFour();
                getTemplate();
            } 
            else if (area <= 1500) 
            {
                cls();
                System.out.println("Provided and Suitable Templates are:");
                levelTwo();
                levelThree();
                levelFour();
                levelFive();
                getTemplate();
            } else if (area <= MAX_AREA_1) 
            {
                cls();
                System.out.println("Provided and Suitable Templates are:");
                levelThree();
                levelFour();
                levelFive();
                levelSix();
                getTemplate();
            }
            
        } 
        else if (area <= 5000) 
        {
            cls();
            System.out.println("Contact Builder for further assistance.");
        } 
        else if (area < MAX_AREA_2) 
        {
            cls();
            System.out.println("Provided and Suitable Template is:");
            levelEight();
            getTemplate();
        } 
        else if (area < MAX_AREA_3) 
        {
            cls();
            System.out.println("Provided and Suitable Templates are:");
            levelEight();
            levelNine();
            getTemplate();
        } 
        else 
        {
            cls();
            System.out.println("Contact Builder for further assistance.");
        }
        insertDataIntoDatabase();
        
    }

    
    public void insertDataIntoDatabase() 
    {
        String jdbcUrl = "jdbc:mysql://localhost/civilcontractormanagement";
        String username = "root";
        String password = "";

        String insertSQL = "INSERT INTO client_templates (template, city, area) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) 
        {

            pstmt.setString(1, template);
            pstmt.setString(2, city);
            pstmt.setDouble(3, area);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully!");
        } 
        catch (SQLException e) 
        {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}
