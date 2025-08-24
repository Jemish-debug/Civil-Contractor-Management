import java.util.Scanner;

class CivilContractorManagement 
{
    Scanner sc = new Scanner(System.in);
    Login.ClientInputValidation client = new Login.ClientInputValidation();
    Login.ContractorInputValidation contractor = new Login.ContractorInputValidation();
    Sign_in.ClientSignIn clientSignIn = new Sign_in.ClientSignIn();
    Sign_in.ContractorSignIn contractorSignIn = new Sign_in.ContractorSignIn();
    TemplateSelection.Template template = new TemplateSelection.Template();
    Admin.Admin admin = new Admin.Admin();
    Payment.PaymentMain pay = new Payment.PaymentMain();

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
    public void client() 
    {
        int choice = -1;
       
        System.out.println("1.) Login");
        System.out.println("2.) Sign-in");
        System.out.println("3.) Exit");
        System.out.print("Please select one of the above: ");
        try 
        {
            choice = sc.nextInt();
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid input, please enter a number.");
            
        }
        switch (choice) 
        {
            case 1:
                System.out.println("Client Login");
                client.clientDetailsInput();
                clientSignIn.clientSignIn();
                template.selectTemplate();
                pay.initiatePaymentProcess();
                break;
            case 2:
                System.out.println("Client Sign In");
                clientSignIn.clientSignIn();
                template.selectTemplate();
                pay.initiatePaymentProcess();
                break;
            case 3:
                System.out.println("Thank you for coming");
                System.exit(0);
            default:
                System.out.println("Please enter a valid choice");
                break;
        }
        
    }

    public void contractor() 
    {
        int choice = -1;
        System.out.println("1.) Login");
        System.out.println("2.) Sign-in");
        System.out.println("3.) Exit");
        System.out.print("Please select one of the above: ");
        
        
        try 
        {
            choice = sc.nextInt();
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid input, please enter a number.");
            
        }
        switch (choice) 
        {
            case 1:
                System.out.println("Contractor Login");
                contractor.contractorDetailsInput();
                contractorSignIn.contractorSignIn();
                admin.showMenu();
                
                break;
            case 2:
                System.out.println("Contractor Sign In");
                contractorSignIn.contractorSignIn();
                admin.showMenu();
                break;
            case 3:
                System.out.println("Thank you for coming");
               
                System.exit(0);
            default:
                System.out.println("Please enter a valid choice");
                break;
        }
    }
    
    public void ultimate() 
    {
        int choice;
        System.out.println("---Welcome to our app---");
        System.out.println("1.) Client");
        System.out.println("2.) Contractor");
        System.out.println("3.) Exit");
        System.out.println("Please select anyone of the above: ");
        choice = sc.nextInt();
        switch (choice) 
        {
            case 1:
                cls();
                System.out.println("Hang on a moment...");
                hold();
                cls();
                System.out.println("--Welcome to Client Side--");
                client();
                break;
            case 2:
                cls();
                System.out.println("Hang on a moment...");
                hold();
                cls();
                System.out.println("--Welcome to Contractor Side--");
                contractor();
                break;
            case 3:
                System.out.println("Thank you for coming");
                System.exit(0);
            default:
                System.out.println("Please enter a valid choice");
                break;
        }
    }

    public void startScreen()
    {
        cls();
        hold();
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("|             WELCOME TO CIVIL CONTRACTOR MANAGEMENT              |");
        System.out.println("+-----------------------------------------------------------------+");
        hold();
    }
    public void endScreen()
    {
        cls();
        hold();
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("|                    THANK YOU FOR COMING!!!                      |");
        System.out.println("+-----------------------------------------------------------------+");
        hold();
        cls();
        System.out.println("EXITING SITE...");
        hold();
        System.exit(0);
    }
}
