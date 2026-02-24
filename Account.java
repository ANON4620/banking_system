package banking_system;

public class Account {
    private long accountNumber;
    private String name;
    private double balance;

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

    public double getBalance() {
        return balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public boolean transfer(Account destination, double amount) {
        if (withdraw(amount)) {
            destination.deposit(amount);
            return true;
        }

        return false;
    }

    public void displayDetails() {
        System.out.println();
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
    }

}