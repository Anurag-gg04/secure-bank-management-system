package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String timestamp;
    private final String type;
    private final double amount;
    private final double balanceAfter;

    public Transaction(String type, double amount, double balanceAfter) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public Transaction(String timestamp, String type, double amount, double balanceAfter) {
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public String toFileString() {
        return timestamp + "," + type + "," + amount + "," + balanceAfter;
    }

    @Override
    public String toString() {
        return String.format("%s | %-10s | ₹%-8.2f | Bal: ₹%.2f", timestamp, type, amount, balanceAfter);
    }
}