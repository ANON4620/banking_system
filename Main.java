package banking_system;

import java.util.Scanner;
import java.util.ArrayList;

class Main {

    static ArrayList<Account> accounts = new ArrayList<Account>();

    static Account findAccount(long accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

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

                    account = findAccount(accountNumber);
                    if (account != null) {
                        System.out.println("\nAccount already exists!");
                        break;
                    }

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    accounts.add(new Account(accountNumber, name, 0));

                    System.out.println("\nAccount created successfully.");
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = findAccount(accountNumber);
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

                    account.deposit(amount);

                    System.out.println("\nDeposit successful.");
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = findAccount(accountNumber);
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

                    if (account.withdraw(amount)) {
                        System.out.println("\nWithdraw successful.");
                    } else {
                        System.out.println("\nInsufficient balance!");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = findAccount(accountNumber);
                    if (account == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    System.out.println("\nFetched Balance: " + account.getBalance());
                    break;

                case 5:
                    System.out.print("Enter your account number: ");
                    long sourceAccountNumber = sc.nextLong();

                    Account sourceAccount = findAccount(sourceAccountNumber);
                    if (sourceAccount == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    System.out.print("Enter recipient account number: ");
                    long destinationAccountNumber = sc.nextLong();

                    Account destinationAccount = findAccount(destinationAccountNumber);
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

                    if (sourceAccount.transfer(destinationAccount, amount)) {
                        System.out.println("\nAccount transfer successful.");
                    } else {
                        System.out.println("\nInsufficient balance!");
                    }

                    break;

                case 6:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = findAccount(accountNumber);
                    if (account == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    account.displayDetails();
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
