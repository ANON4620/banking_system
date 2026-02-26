package banking_system;

import java.util.ArrayList;

public class Account {
    private long accountNumber;
    private String name;
    private double balance;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    Account(long accountNumber, String name, double startingBalance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = startingBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(long accountNumber, String name, double amount, String type) {
        transactions.add(new Transaction(accountNumber, name, amount, type));
    }

    public void displayTransactions() {
        if (transactions.size() == 0) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.printf("%-20s %20s %10s %10s\n", "Account Number", "Account Holder Name", "Amount", "Type");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            String accountNumber = String.valueOf(transaction.getAccountNumber());
            String name = transaction.getName();
            String amount = String.valueOf(transaction.getAmount());
            String type = transaction.getType();

            System.out.printf("%-20s %20s %10s %10s\n", accountNumber, name, amount, type);
        }
    }

    public boolean transfer(Account destination, double amount) {
        if (withdraw(amount)) {
            destination.deposit(amount);
            return true;
        }

        return false;
    }

    public void displayDetails() {
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Name: " + getName());
        System.out.println("Balance: " + getBalance());
    }

}