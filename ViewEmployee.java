package employee.management.system;

import javax.swing.*;
import java.awt.*;

public class ViewEmployee {
    public ViewEmployee() {
        // Frame for viewing employee details
        JFrame viewFrame = new JFrame("View Employees");
        viewFrame.setSize(600, 400);
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel to hold employee details
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // For demonstration, you can display a simple label or table here
        JLabel label = new JLabel("Employee details will be displayed here.");
        panel.add(label);

        // Add panel to frame
        viewFrame.add(panel);
        viewFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewEmployee::new); // Start the ViewEmployee window
    }
}
