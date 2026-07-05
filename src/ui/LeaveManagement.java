package ui;

import dao.LeaveDAO;
import model.LeaveRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LeaveManagement extends JFrame {

    JTextField txtEmployeeId;
    JTextField txtStartDate;
    JTextField txtEndDate;
    JTextField txtReason;

    JComboBox<String> cmbLeaveType;
    JComboBox<String> cmbStatus;

    JButton btnAdd;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnClear;

    JTable leaveTable;
    DefaultTableModel tableModel;

    int selectedLeaveId = -1;

    public LeaveManagement() {

        setTitle("Leave Management");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Leave Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6,2,10,10));

        txtEmployeeId = new JTextField();

        cmbLeaveType = new JComboBox<>(new String[]{
                "Sick Leave",
                "Casual Leave",
                "Annual Leave"
        });

        txtStartDate = new JTextField();
        txtEndDate = new JTextField();
        txtReason = new JTextField();

        cmbStatus = new JComboBox<>(new String[]{
                "Pending",
                "Approved",
                "Rejected"
        });

        formPanel.add(new JLabel("Employee ID"));
        formPanel.add(txtEmployeeId);

        formPanel.add(new JLabel("Leave Type"));
        formPanel.add(cmbLeaveType);

        formPanel.add(new JLabel("Start Date"));
        formPanel.add(txtStartDate);

        formPanel.add(new JLabel("End Date"));
        formPanel.add(txtEndDate);

        formPanel.add(new JLabel("Reason"));
        formPanel.add(txtReason);

        formPanel.add(new JLabel("Status"));
        formPanel.add(cmbStatus);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        add(buttonPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[]{
                "ID",
                "Employee ID",
                "Leave Type",
                "Start Date",
                "End Date",
                "Reason",
                "Status"
        });

        leaveTable = new JTable(tableModel);

        add(new JScrollPane(leaveTable), BorderLayout.EAST);

        loadLeaves();
        btnAdd.addActionListener(e -> {

            try {

                LeaveRequest leave = new LeaveRequest();

                leave.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
                leave.setLeaveType(cmbLeaveType.getSelectedItem().toString());
                leave.setStartDate(txtStartDate.getText());
                leave.setEndDate(txtEndDate.getText());
                leave.setReason(txtReason.getText());
                leave.setStatus(cmbStatus.getSelectedItem().toString());

                LeaveDAO dao = new LeaveDAO();

                if (dao.addLeave(leave)) {

                    JOptionPane.showMessageDialog(this, "Leave Request Added Successfully!");

                    loadLeaves();

                } else {

                    JOptionPane.showMessageDialog(this, "Failed to Add Leave Request!");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Please enter valid data!");

            }

        });
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