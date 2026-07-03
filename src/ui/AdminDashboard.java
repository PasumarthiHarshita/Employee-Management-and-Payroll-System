package ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2,1,20,20));

        JButton btnEmployee = new JButton("Employee Management");
        JButton btnDepartment = new JButton("Department Management");

        btnEmployee.addActionListener(e -> {
            new EmployeeManagement();
            dispose();
        });

        btnDepartment.addActionListener(e -> {
            new DepartmentManagement();
            dispose();
        });

        panel.add(btnEmployee);
        panel.add(btnDepartment);

        add(panel);

        setVisible(true);
    }
}