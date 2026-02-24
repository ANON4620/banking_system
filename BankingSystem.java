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

    static Account search(long accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.accountNumber == accountNumber) {
                return account;
            }
        }
        return null;
    }

    static void deposit(Account account, double amount) {
        account.balance += amount;
    }

    static boolean withdraw(Account account, double amount) {
        if (amount <= 0 || amount > account.balance) {
            return false;
        }

        account.balance -= amount;
        return true;
    }

    static double getBalance(Account account) {
        return account.balance;
    }

    static boolean transfer(Account source, Account destination, double amount) {
        if (withdraw(source, amount)) {
            deposit(destination, amount);
            return true;
        }

        return false;
    }

    static void displayDetails(Account account) {
        System.out.println();
        System.out.println("Account Number: " + account.accountNumber);
        System.out.println("Name: " + account.name);
        System.out.println("Balance: " + account.balance);
    }

}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long accountNumber;
        double amount;
        Account account;
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

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();
                    sc.nextLine();

                    account = Account.search(accountNumber);
                    if (account != null) {
                        System.out.println("\nAccount already exists!");
                        break;
                    }

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    new Account(accountNumber, name);

                    System.out.println("\nAccount created successfully.");
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = Account.search(accountNumber);
                    if (account == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("\nAmount must be greater than 0.");
                        break;
                    }

                    Account.deposit(account, amount);

                    System.out.println("\nDeposit successful.");
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = Account.search(accountNumber);
                    if (account == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("\nAmount must be greater than 0.");
                        break;
                    }

                    if (Account.withdraw(account, amount)) {
                        System.out.println("\nWithdraw successful.");
                    } else {
                        System.out.println("\nInsufficient balance!");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = Account.search(accountNumber);
                    if (account == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    System.out.println("\nFetched Balance: " + Account.getBalance(account));
                    break;

                case 5:
                    System.out.print("Enter your account number: ");
                    long sourceAccountNumber = sc.nextLong();

                    Account sourceAccount = Account.search(sourceAccountNumber);
                    if (sourceAccount == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    System.out.print("Enter recipient account number: ");
                    long destinationAccountNumber = sc.nextLong();

                    Account destinationAccount = Account.search(destinationAccountNumber);
                    if (destinationAccount == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("\nAmount must be greater than 0.");
                        break;
                    }

                    if (Account.transfer(sourceAccount, destinationAccount, amount)) {
                        System.out.println("\nAccount transfer successful.");
                    } else {
                        System.out.println("\nInsufficient balance!");
                    }

                    break;

                case 6:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = Account.search(accountNumber);
                    if (account == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    Account.displayDetails(account);
                    break;

                case 99:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }

            System.out.println();

        }
    }
}
