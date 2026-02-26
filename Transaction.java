package banking_system;

public class Transaction {
    private long accountNumber;
    private String name;
    private double amount;
    private String type;

    Transaction(long accountNumber, String name, double amount, String type) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    long getAccountNumber() {
        return accountNumber;
    }

    String getName() {
        return name;
    }

    double getAmount() {
        return amount;
    }

    String getType() {
        return type;
    }

}