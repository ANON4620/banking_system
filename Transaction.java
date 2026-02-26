package banking_system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private long accountNumber;
    private String name;
    private double amount;
    private String type;
    private String dateAndTime;

    Transaction(long accountNumber, String name, double amount, String type) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = amount;
        this.type = type;
        LocalDateTime dateAndTime = LocalDateTime.now();
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateAndTime = dateAndTime.format(dtFormat);
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

    String getDateAndTime() {
        return dateAndTime;
    }

}