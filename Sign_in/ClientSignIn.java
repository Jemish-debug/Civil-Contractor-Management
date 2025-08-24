package Sign_in;

import java.util.Scanner;

public class ClientSignIn 
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
    public void clientSignIn()  
    {
        cls();
        System.out.println("Hang on a moment...");
        hold();
        cls();
        System.out.print("Enter client username: ");
        String username = sc.nextLine();

        System.out.print("Enter client password: ");
        String password = sc.nextLine();
        
        
        SignInDB signInDB = new SignInDB();
        
        
        boolean loginSuccess = signInDB.checkLogin("client_data", username, password);
        
        
        if (loginSuccess) 
        {
            System.out.println("Client sign-in successful!");
        } 
        else 
        {
            System.out.println("Incorrect client credentials. Please try again.");
        }

    }
}
