import java.util.Scanner;
import java.util.ArrayList;

class Account {
    private long accountNumber;
    private String name;
    private double balance;

    private static ArrayList<Account> accounts;
    static {
        accounts = new ArrayList<Account>();
    }

    Account(long accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = 0;
        accounts.add(this);
    }

    static boolean exist(long accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).accountNumber == accountNumber) {
                return true;
            }
        }
        return false;
    }

    static void deposit(long accountNumber, double amount) {
        int i;
        for (i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).accountNumber == accountNumber) {
                break;
            }
        }
        accounts.get(i).balance += amount;
    }

    static boolean withdraw(long accountNumber, double amount) {
        int i;
        for (i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).accountNumber == accountNumber) {
                break;
            }
        }

        if (amount > accounts.get(i).balance) {
            return false;
        }

        accounts.get(i).balance -= amount;
        return true;
    }

    static double getBalance(long accountNumber) {
        int i;
        for (i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).accountNumber == accountNumber) {
                break;
            }
        }
        return accounts.get(i).balance;
    }

    static void transfer(long source, long destination, double amount) {
        withdraw(source, amount);
        deposit(destination, amount);
    }

    static void displayDetails(long accountNumber) {
        int i;
        for (i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).accountNumber == accountNumber) {
                break;
            }
        }

        System.out.println("Account Number: " + accounts.get(i).accountNumber);
        System.out.println("Name: " + accounts.get(i).name);
        System.out.println("Balance: " + accounts.get(i).balance);
    }

}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. Display Details");
            System.out.println("99. Exit");
            System.out.print(">> ");
            int choice = sc.nextInt();
            long accountNumber;
            double amount;
            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();
                    sc.nextLine();

                    if (Account.exist(accountNumber)) {
                        System.out.println("Account already exists!");
                        break;
                    }

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    new Account(accountNumber, name);

                    System.out.println("Account created successfully.");
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    if (!Account.exist(accountNumber)) {
                        System.out.println("Account does not exist!");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Amount must be greater than 0.");
                        break;
                    }

                    Account.deposit(accountNumber, amount);

                    System.out.println("Deposit successful.");
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    if (!Account.exist(accountNumber)) {
                        System.out.println("Account does not exist!");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Amount must be greater than 0.");
                        break;
                    }

                    if (Account.withdraw(accountNumber, amount)) {
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    if (!Account.exist(accountNumber)) {
                        System.out.println("Account does not exist!");
                        break;
                    }

                    System.out.println("Fetched Balance: " + Account.getBalance(accountNumber));
                    break;

                case 5:
                    System.out.print("Enter your account number: ");
                    long source = sc.nextLong();

                    if (!Account.exist(source)) {
                        System.out.println("Account does not exist!");
                        break;
                    }

                    System.out.print("Enter recipient account number: ");
                    long destination = sc.nextLong();

                    if (!Account.exist(destination)) {
                        System.out.println("Account does not exist!");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Amount must be greater than 0.");
                        break;
                    }

                    Account.transfer(source, destination, amount);

                    System.out.println("Account transfer successful.");
                    break;

                case 6:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    if (!Account.exist(accountNumber)) {
                        System.out.println("Account does not exist!");
                        break;
                    }

                    Account.displayDetails(accountNumber);
                    break;

                case 99:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }

            System.out.println();
        }
    }
}
