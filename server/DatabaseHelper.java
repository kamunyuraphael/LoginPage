package server;
import java.sql.*;

public class DatabaseHelper {
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/auth_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void initializeDatabase() {
        // 1. MySQL syntax uses auto increment instead of SQLite's primary mechanism
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "username VARCHAR(255) UNIQUE NOT NULL,"
                + "password VARCHAR(255) NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL); // Execute the SQL statement to create the users table
        } catch (SQLException e) {
            System.out.println("MySQL Database initialization error: " + e.getMessage());
        }
    }

    public static boolean registerUser(String username, String password) {
        String insertSQL = "INSERT INTO users (username, password) VALUES (?, ?);"; // SQL statement to insert a new user

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(insertSQL)) { // Use PreparedStatement to prevent SQL injection and handle parameters
            pstmt.setString(1, username); // Set the username parameter
            pstmt.setString(2, password); // Set the password parameter
            pstmt.executeUpdate(); // Execute the insert statement
            return true; // Registration successful
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry error code for MySQL
                System.out.println("Username already exists: " + username);
            } else {
                System.out.println("MySQL Registration error: " + e.getMessage());
            }
            return false; // Registration fails if username violates unique constraint or other SQL error occurs
        }
    }

    public static boolean validateUser(String username, String password) {
        String querySQL = "SELECT * FROM users WHERE username = ? AND password = ?;"; // SQL statement to validate user login

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setString(1, username); // Set the username parameter
            pstmt.setString(2, password); // Set the password parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query and get the result set
            return rs.next(); // Return true if a matching user is found, false otherwise
        } catch (SQLException e) {
            System.out.println("MySQL Login validation error: " + e.getMessage());
            return false; // Login validation fails if an SQL error occurs
        }
    }
}