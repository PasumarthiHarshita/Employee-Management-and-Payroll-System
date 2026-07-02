package ui;

import javax.swing.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnEmployee = new JButton("Employee Management");

        btnEmployee.addActionListener(e -> {
            new EmployeeManagement();
            dispose();
        });

        add(btnEmployee);
     

        setVisible(true);
    }
}