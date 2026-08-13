# 🏦 Secure Bank Management System

A console-based banking application built with **Java** to simulate basic banking operations while practicing object-oriented programming, authentication, transaction handling, file storage, and exception handling.

The project supports **Savings and Current accounts**, secure PIN storage using SHA-256 hashing, account locking after failed login attempts, money transfers, transaction history, and a simple admin portal.

---

## 📌 About the Project

I built this project to understand how a basic banking system can be structured using Java.

Instead of keeping everything inside one class, the application separates the **account models**, **banking logic**, **transactions**, and **main application flow** into different components.

The application runs completely through the command line and stores account information locally in a text file, so the data is available even after restarting the program.

---

## ✨ Features

### 👤 Account Management

* Open a new Savings Account
* Open a new Current Account
* Automatically generate account numbers
* Set a 4-digit PIN during account creation
* Store account information locally
* Automatically load existing accounts when the application starts

### 🔐 Authentication & Account Security

* Login using account number and PIN
* PINs are stored as **SHA-256 hashes** instead of plain text
* Maximum of 3 incorrect login attempts
* Account is automatically locked after 3 failed attempts
* Admin can unlock locked accounts

### 💰 Banking Operations

Once logged in, users can:

* Check account balance
* Deposit money
* Withdraw money
* Transfer money to another account
* View transaction history
* Logout securely

### 🏦 Savings Account

Savings accounts include:

* Normal deposits
* Withdrawals
* Insufficient balance validation
* ₹50,000 withdrawal limit per transaction

### 💼 Current Account

Current accounts include:

* Normal deposits
* Withdrawals
* ₹25,000 overdraft facility
* Overdraft limit validation

### 🔄 Money Transfers

Users can transfer money between accounts.

The system:

1. Checks whether both accounts exist
2. Prevents transfers to the same account
3. Validates the sender's available funds/overdraft
4. Deducts money from the sender
5. Adds money to the receiver
6. Records the transfer in both transaction histories

### 📜 Transaction History

Every important account operation is recorded, including:

* Account opening
* Deposits
* Withdrawals
* Transfers

Each transaction contains information such as:

* Date and time
* Transaction type
* Amount
* Balance after the transaction

### 👨‍💼 Admin Portal

The application includes a basic admin section that allows authorized administrators to:

* View a bank status report
* Check the total number of accounts
* Check the number of locked accounts
* View total liquidity
* Unlock locked accounts

---

## 🛠️ Tech Stack

| Technology                 | Purpose                                  |
| -------------------------- | ---------------------------------------- |
| **Java**                   | Core application development             |
| **OOP**                    | Account models and application structure |
| **SHA-256**                | PIN hashing                              |
| **Java Collections**       | Account management using `HashMap`       |
| **File I/O**               | Persistent local data storage            |
| **Exception Handling**     | Input validation and error handling      |
| **Java Time API**          | Transaction timestamps                   |
| **Command Line Interface** | User interaction                         |

---

## 🧱 Project Structure

```text
BankManagementSystem/
│
├── data/
│   └── accounts.txt
│
├── src/
│   ├── Main.java
│   │
│   ├── model/
│   │   ├── Account.java
│   │   ├── CurrentAccount.java
│   │   ├── SavingsAccount.java
│   │   └── Transaction.java
│   │
│   └── service/
│       └── BankManager.java
│
└── README.md
```

---

## 🧩 Architecture

The project is divided into three main parts.

### Model

The `model` package contains the main banking entities.

```text
Account
├── SavingsAccount
└── CurrentAccount

Transaction
```

`Account` is an abstract base class that contains common functionality such as:

* Account number
* Customer name
* Balance
* PIN hash
* Login status
* Failed login attempts
* Transaction history

`SavingsAccount` and `CurrentAccount` extend `Account` and implement their own withdrawal rules.

---

### Service

`BankManager` contains the main banking operations.

It handles:

* Account creation
* Authentication
* Transfers
* Saving account data
* Loading account data
* Admin reports
* Unlocking accounts

---

### Main Application

`Main.java` provides the command-line interface.

The main menu provides:

```text
1. Login
2. Open Account
3. Admin Portal
4. Exit
```

After login, users get access to:

```text
1. Balance
2. Deposit
3. Withdraw
4. Transfer
5. History
6. Logout
```

---

## 🔐 Security Implementation

The project includes a few security-focused concepts.

### PIN Hashing

PINs are not stored directly.

The application hashes the PIN using SHA-256:

```java
MessageDigest md = MessageDigest.getInstance("SHA-256");
```

The resulting hash is stored instead of the original PIN.

### Failed Login Protection

Every incorrect PIN increases the failed-attempt counter.

After three failed attempts:

```text
Account Locked
```

The account must then be unlocked through the admin portal.

### Input Validation

The application validates important inputs such as:

* PIN format
* Deposit amount
* Withdrawal amount
* Account number
* Account type
* Transfer destination
* Account balance
* Overdraft limit

---

## 💾 Data Persistence

The project does not use a database.

Instead, account information and transaction history are stored locally in:

```text
data/accounts.txt
```

When the application starts:

```text
Application
     ↓
BankManager
     ↓
Load accounts.txt
     ↓
Recreate account objects
     ↓
Ready for login
```

When important operations are completed, the application saves the updated information back to the file.

---

## 💻 How to Run

### Requirements

You need:

* **JDK 17+**
* IntelliJ IDEA, VS Code, Eclipse, or another Java IDE
* Command Prompt / Terminal

### 1. Clone the repository

```bash
git clone https://github.com/Anurag-gg04/secure-bank-management-system.git
```

### 2. Open the project

Open the project in your preferred Java IDE.

Make sure the project structure looks like:

```text
src/
├── Main.java
├── model/
└── service/

data/
└── accounts.txt
```

### 3. Run the application

Run:

```text
src/Main.java
```

The application will start with:

```text
--- Bank Management System ---

1. Login
2. Open Account
3. Admin Portal
4. Exit
```

---

## 🧪 Example Workflow

### Create an Account

Choose:

```text
2. Open Account
```

Then enter:

```text
Account Type: S
Full Name: Anurag
Initial Deposit: ₹10000
PIN: 1234
```

The application generates an account number automatically.

---

### Login

Use:

```text
Account No: 1001
PIN: 1234
```

After successful authentication, the account dashboard appears.

---

### Deposit

```text
Amount to deposit: ₹5000
```

The balance and transaction history are updated automatically.

---

### Transfer

Enter the destination account:

```text
Target Account No: 1002
Amount to transfer: ₹2000
```

The sender and receiver transaction histories are updated accordingly.

---

## 📊 Key OOP Concepts Used

This project was also designed to practice core Java and OOP concepts.

### Encapsulation

Account information is kept inside the `Account` class and accessed through methods such as:

```java
getBalance()
getAccountNo()
getName()
```

### Inheritance

Both account types inherit from the abstract `Account` class:

```text
Account
   │
   ├── SavingsAccount
   │
   └── CurrentAccount
```

### Abstraction

`Account` defines common banking behavior while leaving withdrawal rules to the individual account types.

```java
public abstract void debit(double amount);
```

### Polymorphism

`SavingsAccount` and `CurrentAccount` provide different implementations of `debit()`.

This allows the bank manager to work with the common `Account` type while each account follows its own rules.

### Collections

A `HashMap` is used to manage accounts:

```java
Map<Integer, Account> accounts = new HashMap<>();
```

This allows accounts to be accessed efficiently using their account numbers.

---

## ⚠️ Important Note

This project is intended for **learning and demonstration purposes** and should not be used as a real banking application.

Although it demonstrates concepts such as PIN hashing and account locking, a production banking system would require much stronger security, including:

* Secure password hashing such as Argon2/bcrypt
* Proper key management
* Database transactions
* Encryption at rest and in transit
* Role-based access control
* Secure authentication
* Audit logging
* Input sanitization
* Secure secrets management
* Concurrency control

The current project also uses a local text file for persistence and contains a simple hardcoded admin password, so it should not be treated as production-grade banking software.

---

## 👨‍💻 Author

### Anurag Tomar

B.Tech — Computer Science & Engineering

**GitHub:**
https://github.com/Anurag-gg04

---

## 📄 License

This project is intended for educational and personal use.

Feel free to modify and extend it for learning purposes.

---

<div align="center">

### ⭐ If you found this project useful, consider giving the repository a star.

**Built with Java ☕**

</div>
