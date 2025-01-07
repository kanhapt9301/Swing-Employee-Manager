package employee.management.system;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db"; // Update with actual database
    private static final String USERNAME = "root"; // Update with actual username
    private static final String PASSWORD = "password"; // Update with actual password

    private static Connection connection;
    private static final Logger LOGGER = Logger.getLogger(ConnectionDB.class.getName());

    // Private constructor to prevent instantiation
    private ConnectionDB() {
    }

    // Method to establish a connection to the database
    public static Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                LOGGER.log(Level.INFO, "Database connection established.");
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Failed to connect to the database.", ex);
                JOptionPane.showMessageDialog(null,
                        "Database connection error: " + ex.getMessage(),
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return connection;
    }

    // Method to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                LOGGER.log(Level.INFO, "Database connection closed.");
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Error closing the database connection.", ex);
            }
        }
    }
}
