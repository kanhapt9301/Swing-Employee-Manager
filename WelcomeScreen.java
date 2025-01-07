package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen {

    public WelcomeScreen() {
        JFrame frame = new JFrame("Employee Management System");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Main panel with modern design
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(30, 40, 70));

        JLabel headerLabel = new JLabel("Employee Management System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Centers the text horizontally
        headerLabel.setVerticalAlignment(SwingConstants.CENTER);    // Centers the text vertically
        mainPanel.add(headerLabel, BorderLayout.CENTER);  // Add to CENTER instead of NORTH for full centering



        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);

        JButton getStartedButton = createStyledButton("Get Started");
        JButton aboutButton = createStyledButton("About System");
        JButton exitButton = createStyledButton("Exit");

        getStartedButton.addActionListener(e -> {
            new LoginPage(); // Assume LoginPage is implemented
            frame.dispose();
        });

        aboutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Employee Management System v2.0\nFeatures:\n- Employee Information Management\n- Attendance and Payroll Management\n- Performance Tracking and Reports\n- Role-based Access", "About", JOptionPane.INFORMATION_MESSAGE);
        });

        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(getStartedButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(92, 104, 116));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(72, 84, 96));
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeScreen::new); // Start in event-dispatching thread
    }
}