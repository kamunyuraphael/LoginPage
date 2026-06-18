# Java Swing Authentication System

A lightweight, secure, and modern Sign-In / Log-In application built using Java Swing. This project demonstrates core desktop software engineering concepts, specifically **Event-Driven Programming (Actions & Events)** and relational data management using local **MySQL Database Connectivity (JDBC)**.

---

## 🚀 Key Features

* **Event-Driven Architecture**: Uses Java `ActionListener` structures to intercept mouse inputs and process structural validation routines.
* **Relational Database Storage**: Full persistence tier integration mapping data structures into local MySQL schemas.
* **SQL Injection Prevention**: Implements parameterized `PreparedStatement` boundaries to secure user-input processing.
* **Modernized Look & Feel**: Formatted using the native Java Nimbus theme engine paired with a flat, padded card UI layout.

---

## 📂 Project Architecture

```text
application/
├── mysql-connector-j-9.x.x.jar     # JDBC Database Driver binary
├── README.md                       # Documentation guide file
├── server/
│   └── DatabaseHelper.java          # Relational CRUD handling operations
└── ui/
    └── src/
        └── loginPage.java           # Graphical frontend window component
```

---

## 🛠️ Prerequisites & Setup

### 1. Database Configuration
Ensure your local MySQL service instance is active on your host machine, then run the snippet below inside your terminal runner or workbench to spin up your logical storage container:

```sql
CREATE DATABASE auth_db;
```

### 2. Driver Dependency Link
Download the official **MySQL Connector/J** (`.jar` bundle) from Oracle's web catalog. Place that file directly under your `application/` root workspace folder, ensuring its filename matches the compile parameters outlined below (e.g., `mysql-connector-j-9.x.x.jar`).

---

## 💻 How to Compile and Run

Because this project organizes distinct application responsibilities into isolated packages (`server` and `ui.src`), compilation parameters must execute explicitly out of your root workspace folder.

Open your system terminal, target the root folder, and execute the following sequential instructions:

```bash
# Step 1: Navigate to the core application folder root
cd "e:/projects/JavaPrograms/class_project/application"

# Step 2: Compile the backend helper and frontend component files simultaneously (use your downloaded .jar file
javac -cp ".;mysql-connector-j-8.0.33.jar" server/DatabaseHelper.java ui/src/loginPage.java

# Step 3: Launch the compiled package bytecode application instance
java -cp ".;mysql-connector-j-8.0.33.jar" ui.src.loginPage
```

*Note: If compiling from Linux or macOS bash systems, replace the Windows semicolon path delimiter (`;`) with a standard colon flag (`:`).*

---

## 📑 Core Concepts Applied

### Event Handling
The user interface implements structural listeners mapping raw screen interactions directly into logic flows:
* **Event Source**: Elements like `JButton` items fire signals upon click events.
* **Event Listener**: Implementing `ActionListener` routes interactive flows directly into an isolated `actionPerformed(ActionEvent e)` logic controller block.

### Database Persistence
* **Data Integration**: Leverages JDBC (`DriverManager`) to bridge structural object values seamlessly over active local network ports.
* **Constraints Enforcement**: Employs structural database schema properties like `UNIQUE` and `PRIMARY KEY` flags to filter out duplicate registrations cleanly.

