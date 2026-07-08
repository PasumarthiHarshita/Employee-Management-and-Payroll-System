package ui;

import dao.LeaveDAO;
import model.LeaveRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LeaveReport extends JFrame {

    JTable table;
    DefaultTableModel tableModel;

    public LeaveReport() {

        setTitle("Leave Report");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Leave Report", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,24));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[]{
                "Leave ID",
                "Employee ID",
                "Leave Type",
                "Start Date",
                "End Date",
                "Reason",
                "Status"
        });

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadLeaves();

        setVisible(true);
    }

    private void loadLeaves() {

        tableModel.setRowCount(0);

        LeaveDAO dao = new LeaveDAO();

        List<LeaveRequest> list = dao.getAllLeaves();

        for (LeaveRequest leave : list) {

            tableModel.addRow(new Object[]{

                    leave.getLeaveId(),
                    leave.getEmployeeId(),
                    leave.getLeaveType(),
                    leave.getStartDate(),
                    leave.getEndDate(),
                    leave.getReason(),
                    leave.getStatus()

            });

        }
    }
}