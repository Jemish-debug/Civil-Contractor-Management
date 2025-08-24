package Sign_in;

import java.util.Scanner;

public class ContractorSignIn 
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
    public void contractorSignIn() 
    {
        cls();
        System.out.println("Hang on a moment...");
        hold();
        cls();
        System.out.print("Enter contractor username: ");
        String username = sc.nextLine();
        System.out.print("Enter contractor password: ");
        String password = sc.nextLine();
        SignInDB signInDB = new SignInDB();
        
        
        boolean loginSuccess = signInDB.checkLogin("contractor_data", username, password);
        
        
        if (loginSuccess) 
        {
            System.out.println("Contractor sign-in successful!");
        } 
        else 
        {
            System.out.println("Incorrect contractor credentials. Please try again.");
            cls();
            hold();
            contractorSignIn();
        }
    }
}
