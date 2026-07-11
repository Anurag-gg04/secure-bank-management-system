package model;

public class SavingsAccount extends Account {
    private static final double LIMIT = 50000.0;

    public SavingsAccount(int accountNo, String name, double balance, String rawPin) {
        super(accountNo, name, balance, rawPin);
    }

    public SavingsAccount(int accountNo, String name, double balance, String pinHash, boolean locked, int failCount) {
        super(accountNo, name, balance, pinHash, locked, failCount);
    }

    @Override
    public void debit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        if (amount > LIMIT)
            throw new IllegalArgumentException("Daily limit is ₹" + LIMIT);
        if (amount > balance)
            throw new IllegalStateException("Insufficient balance");
        this.balance -= amount;
        addTx("WITHDRAW", amount);
    }

    @Override
    public String getType() {
        return "SAVINGS";
    }
}