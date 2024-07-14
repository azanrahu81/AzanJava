import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal");
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args)throws Exception 
    {
        Scanner scanner = new Scanner(System.in);

        // Create a new account
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();

        BankAccount account = new BankAccount(accountNumber, accountHolderName);
        System.out.println("Account created successfully!");

        // Log transactions to a file
        File f = new File("D://Filling01//BankTransactions.txt");

        if(f.createNewFile())
        {
            System.out.println("Create File:"+f.getName());
        }
        else
        {
            System.out.println("Fill is already exists");
        }

        try 
        {
            PrintStream out = new PrintStream(f);

            while (true)
             {
                System.out.println("\nBank Management System");
                System.out.println("1. Deposit Money");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) 
                {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        out.println("Deposited: " + depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        out.println("Withdrawn: " + withdrawAmount);
                        break;
                    case 3:
                        double balance = account.getBalance();
                        System.out.println("Current balance: " + balance);
                        out.println("Checked balance: " + balance);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        out.println("Exited the system");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
