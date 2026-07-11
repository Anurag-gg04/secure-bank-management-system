package service;

import model.*;
import java.io.*;
import java.util.*;

public class BankManager {
    private final Map<Integer, Account> accounts = new HashMap<>();
    private final String DATA_FILE = "data/accounts.txt";
    private int nextAccNo = 1001;

    public BankManager() {
        load();
        if (!accounts.isEmpty()) {
            nextAccNo = Collections.max(accounts.keySet()) + 1;
        }
    }

    public Account openAccount(String type, String name, double deposit, String pin) {
        if (!pin.matches("\\d{4}"))
            throw new IllegalArgumentException("PIN must be 4 digits");
        if (deposit <= 0)
            throw new IllegalArgumentException("Initial deposit must be positive");

        Account acc;
        if (type.equalsIgnoreCase("S")) {
            acc = new SavingsAccount(nextAccNo++, name, deposit, pin);
        } else if (type.equalsIgnoreCase("C")) {
            acc = new CurrentAccount(nextAccNo++, name, deposit, pin);
        } else {
            throw new IllegalArgumentException("Invalid type. Use 'S' for Savings or 'C' for Current");
        }

        accounts.put(acc.getAccountNo(), acc);
        save();
        return acc;
    }

    public Account authenticate(int accNo, String pin) {
        Account acc = accounts.get(accNo);
        if (acc == null)
            throw new IllegalArgumentException("Account not found");
        if (acc.isLocked())
            throw new IllegalStateException("Account is locked due to too many failed attempts");

        if (acc.login(pin)) {
            save();
            return acc;
        } else {
            save();
            if (acc.isLocked())
                throw new IllegalStateException("Account locked! 3 incorrect attempts");
            throw new IllegalArgumentException("Wrong PIN. Attempts left: " + (3 - acc.getFailCount()));
        }
    }

    public void transfer(int fromNo, int toNo, double amount) {
        if (fromNo == toNo)
            throw new IllegalArgumentException("Cannot transfer to the same account");
        Account sender = accounts.get(fromNo);
        Account receiver = accounts.get(toNo);

        if (sender == null || receiver == null)
            throw new IllegalArgumentException("Target account not found");

        sender.debit(amount);
        receiver.credit(amount);

        // Adjust labels for accuracy in the ledger
        List<Transaction> sHist = sender.getHistory();
        List<Transaction> rHist = receiver.getHistory();
        sHist.remove(sHist.size() - 1);
        rHist.remove(rHist.size() - 1);

        sender.loadTx(new Transaction("TRF OUT -> " + toNo, amount, sender.getBalance()));
        receiver.loadTx(new Transaction("TRF IN <- " + fromNo, amount, receiver.getBalance()));

        save();
    }

    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Account acc : accounts.values()) {
                pw.printf("%s|%d|%s|%.2f|%s|%b|%d%n",
                        acc.getType(), acc.getAccountNo(), acc.getName(),
                        acc.getBalance(), acc.getPinHash(), acc.isLocked(), acc.getFailCount());

                for (Transaction tx : acc.getHistory()) {
                    pw.println("TX|" + tx.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not save data - " + e.getMessage());
        }
    }

    private void load() {
        File file = new File(DATA_FILE);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Account curr = null;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals("TX") && curr != null) {
                    String[] t = parts[1].split(",");
                    curr.loadTx(new Transaction(t[0], t[1], Double.parseDouble(t[2]), Double.parseDouble(t[3])));
                } else if (parts.length >= 7) {
                    int no = Integer.parseInt(parts[1]);
                    String name = parts[2];
                    double bal = Double.parseDouble(parts[3]);
                    String hash = parts[4];
                    boolean locked = Boolean.parseBoolean(parts[5]);
                    int fails = Integer.parseInt(parts[6]);

                    curr = parts[0].equals("SAVINGS")
                            ? new SavingsAccount(no, name, bal, hash, locked, fails)
                            : new CurrentAccount(no, name, bal, hash, locked, fails);
                    accounts.put(no, curr);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not load previous data. Starting fresh.");
        }
    }

    public void printAdminReport(String pass) {
        if (!"admin123".equals(pass)) {
            System.out.println("Wrong admin password.");
            return;
        }
        double total = 0;
        int locked = 0;
        for (Account a : accounts.values()) {
            total += a.getBalance();
            if (a.isLocked())
                locked++;
        }
        System.out.println("\n--- Bank Status Report ---");
        System.out.println("Total Accounts: " + accounts.size());
        System.out.println("Locked Accounts: " + locked);
        System.out.printf("Total Liquidity: ₹%.2f%n", total);
    }

    public void unlockByAdmin(int accNo, String pass) {
        if (!"admin123".equals(pass)) {
            System.out.println("Unauthorized.");
            return;
        }
        Account a = accounts.get(accNo);
        if (a != null) {
            a.unlock();
            save();
            System.out.println("Account " + accNo + " unlocked.");
        } else {
            System.out.println("Account not found.");
        }
    }
}