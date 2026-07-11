package model;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private final int accountNo;
    private String name;
    protected double balance;
    private String pinHash;
    private boolean locked;
    private int failCount;
    private final List<Transaction> history = new ArrayList<>();

    public Account(int accountNo, String name, double balance, String rawPin) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
        this.pinHash = hash(rawPin);
        addTx("OPENING", balance);
    }

    public Account(int accountNo, String name, double balance, String pinHash, boolean locked, int failCount) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
        this.pinHash = pinHash;
        this.locked = locked;
        this.failCount = failCount;
    }

    protected static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }

    public boolean login(String inputPin) {
        if (locked)
            return false;
        if (pinHash.equals(hash(inputPin))) {
            failCount = 0;
            return true;
        } else {
            failCount++;
            if (failCount >= 3)
                locked = true;
            return false;
        }
    }

    public void credit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        this.balance += amount;
        addTx("DEPOSIT", amount);
    }

    public abstract void debit(double amount);

    public abstract String getType();

    protected void addTx(String type, double amount) {
        history.add(new Transaction(type, amount, this.balance));
    }

    public void loadTx(Transaction tx) {
        history.add(tx);
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getPinHash() {
        return pinHash;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getFailCount() {
        return failCount;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public void unlock() {
        this.locked = false;
        this.failCount = 0;
    }
}