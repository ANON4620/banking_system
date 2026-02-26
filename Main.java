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

        AccountNumberGenerator ang = new AccountNumberGenerator(1111);

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Balance");
            System.out.println("5. Transfer Money");
            System.out.println("6. Display Details");
            System.out.println("7. View Transaction History");
            System.out.println("99. Exit");
            System.out.print(">> ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    accountNumber = ang.generate();

                    sc.nextLine(); // To flush \n from input buffer

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    accounts.add(new Account(accountNumber, name, 0));

                    System.out.println("\nAccount created successfully.");
                    System.out.println("Account number: " + accountNumber);
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
                    account.addTransaction(accountNumber, account.getName(), amount, "DEPOSIT");

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
                        account.addTransaction(accountNumber, account.getName(), amount, "WITHDRAW");
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
                        sourceAccount.addTransaction(destinationAccountNumber, destinationAccount.getName(), amount, "SENT");
                        destinationAccount.addTransaction(sourceAccountNumber, sourceAccount.getName(), amount, "RECEIVED");

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

                    System.out.println();
                    account.displayDetails();
                    break;

                case 7:
                    System.out.print("Enter account number: ");
                    accountNumber = sc.nextLong();

                    account = findAccount(accountNumber);
                    if (account == null) {
                        System.out.println("\nAccount does not exist!");
                        break;
                    }

                    System.out.println();
                    account.displayTransactions();
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
