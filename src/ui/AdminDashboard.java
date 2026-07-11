package ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4,2,20,20));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JLabel title = new JLabel("Employee Management System", SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 28));

        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        add(title, BorderLayout.NORTH);
        JButton btnEmployee = new JButton("Employee Management");
        JButton btnDepartment = new JButton("Department Management");
        JButton btnAttendance = new JButton("Attendance Management");
        JButton btnLeave = new JButton("Leave Management");
        JButton btnPayroll = new JButton("Payroll Management");
        JButton btnReports = new JButton("Reports");
        JButton btnExit = new JButton("Exit");
        
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        btnEmployee.setFont(buttonFont);
        btnDepartment.setFont(buttonFont);
        btnAttendance.setFont(buttonFont);
        btnLeave.setFont(buttonFont);
        btnPayroll.setFont(buttonFont);
        btnReports.setFont(buttonFont);
        btnExit.setFont(buttonFont);
        
        
        btnEmployee.addActionListener(e -> {
            new EmployeeManagement();
            dispose();
        });

        btnDepartment.addActionListener(e -> {
            new DepartmentManagement();
            dispose();
        });
        
        
        btnAttendance.addActionListener(e -> {

            new AttendanceManagement();

            dispose();

        });
        
        btnLeave.addActionListener(e -> {

            new LeaveManagement();

            dispose();

        });
        
        btnPayroll.addActionListener(e -> {

            new PayrollManagement();

            dispose();

        });
        btnReports.addActionListener(e -> {

            new ReportsDashboard();

        });
        
        btnExit.addActionListener(e -> {

            System.exit(0);

        });
        panel.add(btnEmployee);
        panel.add(btnDepartment);
        panel.add(btnAttendance);
        panel.add(btnLeave);
        panel.add(btnPayroll);
        panel.add(btnReports);
        panel.add(btnExit);
        add(panel);

        setVisible(true);
    }
}