package model;

public class CurrentAccount extends Account {
    private static final double OVERDRAFT = 25000.0;

    public CurrentAccount(int accountNo, String name, double balance, String rawPin) {
        super(accountNo, name, balance, rawPin);
    }

    public CurrentAccount(int accountNo, String name, double balance, String pinHash, boolean locked, int failCount) {
        super(accountNo, name, balance, pinHash, locked, failCount);
    }

    @Override
    public void debit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (amount > (balance + OVERDRAFT)) {
            throw new IllegalStateException("Overdraft limit exceeded. Max available: ₹" + (balance + OVERDRAFT));
        }
        this.balance -= amount;
        addTx("WITHDRAW", amount);
    }

    @Override
    public String getType() { return "CURRENT"; }
}