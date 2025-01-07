package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainDashboard {

    public MainDashboard(String username) {
        JFrame frame = new JFrame("Employee Management System Dashboard");
        frame.setSize(1000, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Main panel with improved spacing and design
        JPanel mainPanel = new JPanel(new BorderLayout(30, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(30, 40, 70));

        // Header section
        JLabel headerLabel = new JLabel("Welcome, " + username, SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headerLabel.setForeground(new Color(235, 246, 255));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Button panel with a grid layout for improved alignment
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        String[] buttonNames = {"Add Employee", "View Employees", "Update Employee", "Remove Employee", "View Profile", "Logout"};
        JButton[] buttons = new JButton[buttonNames.length];

        for (int i = 0; i < buttonNames.length; i++) {
            buttons[i] = createStyledButton(buttonNames[i]);
            gbc.gridx = i % 3;
            gbc.gridy = i / 3;
            buttonPanel.add(buttons[i], gbc);
        }

        buttons[0].addActionListener(e -> new AddEmployee());
        buttons[1].addActionListener(e -> viewEmployee());
        buttons[2].addActionListener(e -> updateEmployee());
        buttons[3].addActionListener(e -> removeEmployee());
        buttons[4].addActionListener(e -> viewProfile(username));
        buttons[5].addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new LoginPage();
                frame.dispose();
            }
        });

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Footer with back button and copyright label
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> {
            new LoginPage();
            frame.dispose();
        });
        footerPanel.add(backButton, BorderLayout.WEST);

        JLabel footerLabel = new JLabel("Employee Management System © 2025", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        footerLabel.setForeground(Color.LIGHT_GRAY);
        footerPanel.add(footerLabel, BorderLayout.SOUTH);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBackground(new Color(72, 84, 96));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(72, 84, 96), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(92, 104, 116));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(57, 62, 70));
            }
        });
        return button;
    }

    private void viewEmployee() {
        // Enhanced UI for viewing employees (to be implemented)
    }

    private void updateEmployee() {
        // Enhanced UI for updating employees (to be implemented)
    }

    private void removeEmployee() {
        // Enhanced UI for removing employees (to be implemented)
    }

    private void viewProfile(String username) {
        JOptionPane.showMessageDialog(null, "Viewing profile for: " + username, "Profile", JOptionPane.INFORMATION_MESSAGE);
        // Enhanced UI for viewing profile
    }
}
