package banking_system;

public class Transaction {
    public long accountNumber;
    public String name;
    public double amount;
    public String type;

    Transaction(long accountNumber, String name, double amount, String type) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = amount;
        this.type = type;
    }
}