package ui;

import javax.swing.*;
import java.awt.*;

public class ReportsDashboard extends JFrame {

    public ReportsDashboard() {

        setTitle("Reports");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2,2,20,20));

        JButton btnEmployeeReport = new JButton("Employee Report");
        JButton btnAttendanceReport = new JButton("Attendance Report");
        JButton btnLeaveReport = new JButton("Leave Report");
        JButton btnPayrollReport = new JButton("Payroll Report");

        btnEmployeeReport.addActionListener(e -> new EmployeeReport());

        btnAttendanceReport.addActionListener(e -> new AttendanceReport());

        btnLeaveReport.addActionListener(e -> new LeaveReport());

        btnPayrollReport.addActionListener(e -> new PayrollReport());

        panel.add(btnEmployeeReport);
        panel.add(btnAttendanceReport);
        panel.add(btnLeaveReport);
        panel.add(btnPayrollReport);

        add(panel);

        setVisible(true);
    }
}