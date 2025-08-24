package Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class PaymentMain 
{
    Scanner sc = new Scanner(System.in);

    void hold() 
    {
        try 
        {
            Thread.sleep(2000);
        } 
        catch (InterruptedException e) 
        {
            System.out.println(e);
        }
    }

    void cls() 
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void initiatePaymentProcess() 
    {
        cls();
        System.out.println("Hang on a moment...");
        hold();
        cls();
        DBConnect db = new DBConnect();
        Connection con = null;

        try 
        {
            con = db.dbConnect("civilcontractormanagement");

            System.out.print("Enter client name: ");
            String clientName = sc.nextLine();

            String accNo;
            while (true) 
            {
                System.out.print("Enter account number (12 digits): ");
                accNo = sc.nextLine();
                if (accNo.matches("\\d{12}")) 
                {
                    break;
                } 
                else 
                {
                    System.out.println("Invalid account number. It must be exactly 12 digits.");
                }
            }

            double finalBill = 0.0;

            Statement stmt = con.createStatement();
            String queryClientData = "SELECT Id, Name FROM client_data WHERE Name = '" + clientName + "'";
            ResultSet rsClientData = stmt.executeQuery(queryClientData);

            if (rsClientData.next()) 
            {
                int clientId = rsClientData.getInt("Id");
                String name = rsClientData.getString("Name");

                
                String queryTemplate = "SELECT t.Template, t.City, t.Area FROM client_templates t "
                        + "JOIN client_data d ON t.Id = d.Id WHERE d.Id = " + clientId;
                ResultSet rsTemplate = stmt.executeQuery(queryTemplate);

                if (rsTemplate.next()) 
                {
                    String template = rsTemplate.getString("Template");
                    String city = rsTemplate.getString("City");
                    String area = rsTemplate.getString("Area");

                    double baseRate = 10000000;
                    double areaCharge = Double.parseDouble(area) * 10000;
                    double gstBill = (baseRate + areaCharge)*0.18;
                    finalBill = gstBill + baseRate + areaCharge;

                    System.out.println("\n--- Bill Summary ---");
                    System.out.println("+------------------+------------------+----------------+------------------+");
                    System.out.println("| Name             | Template          | City           | Area            |");
                    System.out.println("+------------------+------------------+----------------+------------------+");
                    System.out.printf("| %-16s | %-17s | %-15s | %-17s |\n", name, template, city, area);
                    System.out.println("+------------------+------------------+----------------+------------------+");
                    System.out.println("Final Bill: " + formatIndianCurrency(finalBill));
                    System.out.println("Date: " + LocalDate.now());

                    recordPayment(name, finalBill, accNo, template, city, con);
                } 
                else 
                {
                    System.out.println("No data found in client_templates for the client Id: " + clientId);
                }
            } 
            else 
            {
                System.out.println("No data found in client_data for the client: " + clientName);
            }

        } 
        catch (SQLException e) 
        {
            System.out.println("Error processing payment: " + e.getMessage());
            e.printStackTrace();
        } 
        finally 
        {
            if (con != null) 
            {
                try 
                {
                    con.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private String formatIndianCurrency(double amount) 
    {
        String rupeeSymbol = "₹";
        if (amount >= 1e7) 
        {
            return rupeeSymbol + String.format("%.2f Cr", amount / 1e7);
        } 
        else if (amount >= 1e5) 
        {
            return rupeeSymbol + String.format("%.2f Lakh", amount / 1e5);
        } 
        else 
        {
            return rupeeSymbol + String.format("%.2f", amount);
        }
    }

    private void recordPayment(String clientName, double amount, String accNo, String template, String city, Connection con) 
    {
        try 
        {
            Statement stmt = con.createStatement();
            LocalDate currentDate = LocalDate.now();
            String insertPayment = "INSERT INTO Payment (Name, Acc_no, Amount, Date, Template, City) VALUES ('"
                    + clientName + "', '" + accNo + "', " + amount + ", '" + currentDate + "', '"
                    + template + "', '" + city + "')";
            stmt.executeUpdate(insertPayment);

            System.out.println("Payment of " + formatIndianCurrency(amount) + " has been recorded for "
                    + clientName + " with Acc_no " + accNo + " on " + currentDate
                    + ". Template: " + template + ", City: " + city);

        } 
        catch (SQLException e) 
        {
            System.out.println("Error inserting payment: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
