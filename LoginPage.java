package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage {

    public LoginPage() {
        // Create the main frame
        JFrame frame = new JFrame("Employee Management System Login");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Main panel with padding and elegant color scheme
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(30, 40, 70)); // Dark background

        // Header panel with refined branding
        JLabel headerLabel = new JLabel("Login to Employee Management System", JLabel.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headerLabel.setForeground(Color.WHITE);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Login form panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Better spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username label and field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        userLabel.setForeground(new Color(235, 246, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.anchor = GridBagConstraints.EAST;
        loginPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(22);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        loginPanel.add(userField, gbc);

        // Password label and field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        passLabel.setForeground(new Color(235, 246, 255));
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(22);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passField, gbc);

        // Checkbox for showing password
        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        showPassword.setOpaque(false);
        showPassword.setForeground(new Color(235, 246, 255));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        showPassword.addActionListener(e -> passField.setEchoChar(showPassword.isSelected() ? '\0' : '*'));
        loginPanel.add(showPassword, gbc);

        // Buttons panel with better margins and rounded buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Horizontal layout with 10px gap
        buttonPanel.setOpaque(false);

        // Back button
        JButton backButton = createRoundedButton("Back");
        backButton.setPreferredSize(new Dimension(120, 40)); // Set smaller button size
        backButton.setBackground(new Color(72, 84, 96));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the current login window
            new WelcomeScreen(); // Open the WelcomeScreen
        });
        buttonPanel.add(backButton);

        // Login button
        JButton loginButton = createRoundedButton("Login");
        loginButton.setPreferredSize(new Dimension(120, 40)); // Set smaller button size
        loginButton.setBackground(new Color(34, 167, 240)); // Modern blue shade
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (username.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                new MainDashboard(username);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials, try again.");
            }
        });
        buttonPanel.add(loginButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(buttonPanel, gbc);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Footer panel with a subtle forgot password link
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setForeground(new Color(72, 84, 96));
        forgotPasswordLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Password recovery not yet implemented.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        footerPanel.add(forgotPasswordLabel);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Reduced font size
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(20)); // Smaller rounded corners with 20px radius
        button.setContentAreaFilled(false);  // Prevent default background painting

        // Creating a custom panel to give the button a rounded background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(button.getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded background
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(72, 84, 96));  // Background color for the 'Back' button
        panel.setPreferredSize(new Dimension(120, 40)); // Set smaller button size

        // Add the button inside the panel
        panel.add(button, BorderLayout.CENTER);

        // Handle mouse events for hover effects
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(panel.getBackground().brighter()); // On hover brighten background
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(panel.getBackground().darker()); // On hover exit darken background
            }
        });

        return button;
    }

    // Custom RoundedBorder class
    private static class RoundedBorder implements javax.swing.border.Border {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(c.getForeground());
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    public static void main(String[] args) {
        new LoginPage(); // Create an instance of LoginPage on startup
    }
}
