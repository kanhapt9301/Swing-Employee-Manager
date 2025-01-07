package employee.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeOperations {
    EmployeeOperations(String action) {
        JFrame frame = new JFrame(action.toUpperCase() + " Employee");
        frame.setSize(600, 400);

        if (action.equals("add")) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField();
            JLabel ageLabel = new JLabel("Age:");
            JTextField ageField = new JTextField();
            JLabel deptLabel = new JLabel("Department:");
            JTextField deptField = new JTextField();

            JButton saveButton = new JButton("Save");

            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(ageLabel);
            panel.add(ageField);
            panel.add(deptLabel);
            panel.add(deptField);
            panel.add(new JLabel());
            panel.add(saveButton);

            saveButton.addActionListener(e -> {
                try (Connection con = ConnectionDB.connect()) {
                    String query = "INSERT INTO employees (name, age, department) VALUES (?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, nameField.getText());
                    ps.setInt(2, Integer.parseInt(ageField.getText()));
                    ps.setString(3, deptField.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Employee added successfully");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                    ex.printStackTrace();
                }
            });

            frame.add(panel);
        }
        // Similarly implement for "view", "update", and "remove" operations...

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Test the "add" operation
        new EmployeeOperations("add");
    }
}
