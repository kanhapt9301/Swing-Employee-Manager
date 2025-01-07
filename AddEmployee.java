package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddEmployee {

    public AddEmployee() {
        JFrame frame = new JFrame("Add Employee");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        mainPanel.setBackground(new Color(30, 40, 70));

        JLabel headerLabel = new JLabel("Add Employee", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        headerLabel.setForeground(new Color(235, 246, 255));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = createStyledLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel ageLabel = createStyledLabel("Age:");
        JTextField ageField = new JTextField(20);

        JLabel deptLabel = createStyledLabel("Department:");
        String[] departments = {"Select Department", "HR", "Finance", "IT", "Sales"};
        JComboBox<String> deptComboBox = new JComboBox<>(departments);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(ageLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(deptLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(deptComboBox, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton saveButton = createStyledButton("Save");
        JButton clearButton = createStyledButton("Clear");
        JButton backButton = createStyledButton("Back");

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> handleSave(nameField, ageField, deptComboBox, frame));
        clearButton.addActionListener(e -> {
            nameField.setText("");
            ageField.setText("");
            deptComboBox.setSelectedIndex(0);
        });
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainDashboard("user");
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(72, 84, 96));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(92, 104, 116), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(72, 84, 96));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(72, 84, 96));
            }
        });
        return button;
    }

    private void handleSave(JTextField nameField, JTextField ageField, JComboBox<String> deptComboBox, JFrame frame) {
        try {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String department = (String) deptComboBox.getSelectedItem();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int age = Integer.parseInt(ageText);
            if (age <= 0) {
                throw new NumberFormatException("Age must be a positive number.");
            }

            if (department.equals("Select Department")) {
                JOptionPane.showMessageDialog(frame, "Please select a valid department.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Connection connection = ConnectionDB.connect();
            String query = "INSERT INTO employees (name, age, department) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, department);
            preparedStatement.executeUpdate();
            connection.close();

            JOptionPane.showMessageDialog(frame, "Employee added successfully!");

            nameField.setText("");
            ageField.setText("");
            deptComboBox.setSelectedIndex(0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid age input. Please enter a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
