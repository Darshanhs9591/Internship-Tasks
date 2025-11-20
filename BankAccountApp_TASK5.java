import java.util.ArrayList;
import java.util.Scanner;

class Account {

    private String accountNumber;
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactions = new ArrayList<>();

    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        transactions.add("Account created with initial balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive!");
            return;
        }
        balance += amount;
        transactions.add("Deposited: " + amount + " | New Balance: " + balance);
        System.out.println("Deposit successful!");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        balance -= amount;
        transactions.add("Withdrawn: " + amount + " | New Balance: " + balance);
        System.out.println("Withdrawal successful!");
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String t : transactions) {
                System.out.println(t);
            }
        }
        System.out.println("---------------------------\n");
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
               "\nAccount Holder: " + accountHolder +
               "\nCurrent Balance: " + balance;
    }
}

public class BankAccountApp {

    private static Scanner sc = new Scanner(System.in);
    private static Account account;

    public static void main(String[] args) {

        System.out.println("=== Create Your Bank Account ===");
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double bal = Double.parseDouble(sc.nextLine());

        account = new Account(accNo, name, bal);

        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> depositMoney();
                case 2 -> withdrawMoney();
                case 3 -> System.out.println("Current Balance: " + account.getBalance());
                case 4 -> account.printTransactionHistory();
                case 5 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

    }

    private static void showMenu() {
        System.out.println("\n=== BANK MENU ===");
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(sc.nextLine());
        account.deposit(amount);
    }

    private static void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(sc.nextLine());
        account.withdraw(amount);
    }
}
