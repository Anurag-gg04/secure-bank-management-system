import model.Account;
import model.Transaction;
import service.BankManager;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final BankManager mgr = new BankManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Login");
            System.out.println("2. Open Account");
            System.out.println("3. Admin Portal");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        loginMenu();
                        break;
                    case "2":
                        openMenu();
                        break;
                    case "3":
                        adminMenu();
                        break;
                    case "4":
                        mgr.save();
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void loginMenu() {
        System.out.print("Account No: ");
        int no = Integer.parseInt(sc.nextLine().trim());
        System.out.print("PIN: ");
        String pin = sc.nextLine().trim();

        Account acc = mgr.authenticate(no, pin);
        System.out.println("Welcome back, " + acc.getName() + "!");

        while (true) {
            System.out.println("\n--- Dashboard (" + acc.getType() + ") ---");
            System.out.println("1. Balance  |  2. Deposit  |  3. Withdraw");
            System.out.println("4. Transfer |  5. History  |  6. Logout");
            System.out.print("Option: ");

            try {
                switch (sc.nextLine().trim()) {
                    case "1":
                        System.out.printf("Current Balance: ₹%.2f%n", acc.getBalance());
                        break;
                    case "2":
                        System.out.print("Amount to deposit: ₹");
                        acc.credit(Double.parseDouble(sc.nextLine().trim()));
                        mgr.save();
                        System.out.println("Deposit successful.");
                        break;
                    case "3":
                        System.out.print("Amount to withdraw: ₹");
                        acc.debit(Double.parseDouble(sc.nextLine().trim()));
                        mgr.save();
                        System.out.println("Please take your cash.");
                        break;
                    case "4":
                        System.out.print("Target Account No: ");
                        int target = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Amount to transfer: ₹");
                        double amt = Double.parseDouble(sc.nextLine().trim());
                        mgr.transfer(acc.getAccountNo(), target, amt);
                        System.out.println("Transfer completed.");
                        break;
                    case "5":
                        System.out.println("\n--- Recent Transactions ---");
                        for (Transaction tx : acc.getHistory()) {
                            System.out.println(tx);
                        }
                        break;
                    case "6":
                        mgr.save();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Failed: " + e.getMessage());
            }
        }
    }

    private static void openMenu() {
        System.out.print("Account Type (S for Savings, C for Current): ");
        String type = sc.nextLine().trim();
        System.out.print("Full Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Initial Deposit: ₹");
        double dep = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Set 4-digit PIN: ");
        String pin = sc.nextLine().trim();

        Account acc = mgr.openAccount(type, name, dep, pin);
        System.out.println("Account created successfully! Your Account Number is: " + acc.getAccountNo());
    }

    private static void adminMenu() {
        System.out.print("Admin Password: ");
        String pass = sc.nextLine().trim();

        System.out.println("1. View Report  |  2. Unlock Account");
        System.out.print("Choice: ");
        String c = sc.nextLine().trim();

        if (c.equals("1")) {
            mgr.printAdminReport(pass);
        } else if (c.equals("2")) {
            System.out.print("Account No to unlock: ");
            mgr.unlockByAdmin(Integer.parseInt(sc.nextLine().trim()), pass);
        } else {
            System.out.println("Invalid option.");
        }
    }
}