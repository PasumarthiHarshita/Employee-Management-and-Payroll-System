package ui;

import javax.swing.*;

public class EmployeeDashboard extends JFrame {

    public EmployeeDashboard() {

        setTitle("Employee Dashboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome Employee", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}