# 🏦 Core Java Banking Engine & Transaction Ledger

A robust **console-based banking application** developed using **Core Java (JDK 8+)**. This project demonstrates real-world software engineering principles by combining **Object-Oriented Programming (OOP)**, **efficient data structures**, **secure PIN hashing**, **persistent file storage**, and **transaction management** without relying on external frameworks or databases.

---

## 📖 Overview

The Bank Management System simulates the core functionalities of a banking application through a clean, menu-driven console interface. It enables users to create accounts, securely authenticate using PINs, perform financial transactions, and maintain persistent account records using flat-file storage.

The project emphasizes software engineering best practices such as modular architecture, encapsulation, secure authentication, exception handling, and efficient data retrieval.

---

## 🚀 Features

### 👤 Account Management
- Create Savings and Current accounts
- Unique account number generation
- Secure customer authentication
- View account details

### 💳 Banking Operations
- Deposit money
- Withdraw money
- Transfer funds between accounts
- Balance inquiry
- Transaction history

### 🔒 Security
- PIN authentication
- MD5 PIN hashing using `MessageDigest`
- Plain-text PINs are never stored
- Automatic account lock after **3 incorrect login attempts**
- Administrator account unlock support

### ⚡ Performance
- Uses **HashMap<Integer, Account>**
- Average **O(1)** account lookup
- Fast authentication and fund transfers

### 💾 Data Persistence
- Stores all accounts locally
- Automatically saves data after every transaction
- Loads data when the application starts
- Uses **BufferedReader**, **PrintWriter**, and **Try-With-Resources**

### 📋 Transaction Logging
- Records deposits
- Records withdrawals
- Records transfers
- Timestamped transaction history

---

# 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java (JDK 8+) | Programming Language |
| OOP | Software Design |
| HashMap | Fast Account Lookup |
| File Handling | Persistent Storage |
| BufferedReader | Read Data |
| PrintWriter | Save Data |
| Exception Handling | Error Management |
| MessageDigest (MD5) | PIN Hashing |

---

# 📂 Project Structure

```
BankManagementSystem/
│
├── data/
│   └── accounts.txt              # Persistent account database
│
├── src/
│   ├── model/
│   │   ├── Account.java          # Abstract account model
│   │   ├── SavingsAccount.java   # Savings account logic
│   │   ├── CurrentAccount.java   # Current account logic
│   │   └── Transaction.java      # Transaction entity
│   │
│   ├── service/
│   │   └── BankManager.java      # Core banking engine
│   │
│   └── Main.java                 # Application entry point
│
└── README.md
```

---

# ⚙️ System Workflow

```
Start Application
        │
        ▼
 Load Account Data
        │
        ▼
 Main Menu
        │
 ┌──────┼────────┐
 │      │        │
 ▼      ▼        ▼
Login Create  Admin
Account Portal
 │
 ▼
Authentication
 │
 ▼
Customer Dashboard
 │
 ├── Deposit
 ├── Withdraw
 ├── Transfer
 ├── Balance Inquiry
 ├── Transaction History
 └── Logout
```

---

# 🔒 Security Features

- MD5 PIN hashing
- Secure login authentication
- Three failed login attempt protection
- Automatic account locking
- Input validation
- Exception handling
- Persistent account security

---

# ⚡ Performance

| Operation | Data Structure | Average Complexity |
|-----------|---------------|-------------------|
| Login | HashMap | O(1) |
| Deposit | HashMap | O(1) |
| Withdraw | HashMap | O(1) |
| Transfer | HashMap | O(1) |
| Balance Inquiry | HashMap | O(1) |

---

# 💾 Data Storage

Account information is stored in

```
data/accounts.txt
```

Stored information includes:

- Account Number
- Customer Name
- Account Type
- PIN Hash
- Current Balance
- Failed Login Attempts
- Lock Status
- Transaction History

---

# 📚 Java Concepts Demonstrated

- Classes & Objects
- Encapsulation
- Abstraction
- Inheritance
- Polymorphism
- Abstract Classes
- Method Overriding
- Java Collections Framework
- HashMap
- ArrayList
- File Handling
- Exception Handling
- Try-With-Resources
- Date & Time API
- MD5 Hashing
- Package Organization

---

# ▶️ Getting Started

## Clone the repository

```bash
git clone https://github.com/Anurag-gg04/BankManagementSystem.git
```

---

## Open the project

Open the project using:

- IntelliJ IDEA
- Eclipse
- VS Code

---

## Compile

```bash
javac src/**/*.java
```

---

## Run

```bash
java Main
```

---

# 📷 Sample Output

```text
========== BANK MANAGEMENT SYSTEM ==========

1. Create Account
2. Login
3. Admin Portal
4. Exit

Enter Choice: 1

Enter Name : Anurag
Account Type : Savings
Initial Deposit : 5000

----------------------------------------
Account Created Successfully!
----------------------------------------

Account Number : 1001
Current Balance : ₹5000.00
```

---

# 🎯 Future Enhancements

- JavaFX GUI
- MySQL Database
- JDBC Integration
- BCrypt Password Hashing
- Interest Calculation
- Loan Management
- ATM Simulation
- Email Notifications
- Mobile Banking APIs
- Spring Boot REST API

---

# 👨‍💻 Author

**Anurag Tomar**

🎓 B.Tech Computer Science Engineering

- GitHub: https://github.com/Anurag-gg04

---

# ⭐ Show Your Support

If you found this project useful, consider giving it a **⭐ Star** on GitHub.

Contributions, suggestions, and improvements are always welcome!

---

## 📄 License

This project is open-source and available under the **MIT License**.
