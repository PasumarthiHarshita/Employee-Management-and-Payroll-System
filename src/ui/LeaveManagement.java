package ui;

import dao.LeaveDAO;
import model.LeaveRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


import java.awt.event.KeyEvent;
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
    JScrollPane scrollPane;

    int selectedLeaveId = -1;

    public LeaveManagement() {

        setTitle("Leave Management");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Leave Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,24));
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

        
        btnAdd.setMnemonic(KeyEvent.VK_A);
        btnUpdate.setMnemonic(KeyEvent.VK_U);
        btnDelete.setMnemonic(KeyEvent.VK_D);
        btnClear.setMnemonic(KeyEvent.VK_C);
        
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

        scrollPane = new JScrollPane(leaveTable);

        add(scrollPane, BorderLayout.EAST);

        loadLeaves();
     // Row Selection
        leaveTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = leaveTable.getSelectedRow();

                if (row != -1) {

                    selectedLeaveId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

                    txtEmployeeId.setText(tableModel.getValueAt(row, 1).toString());
                    cmbLeaveType.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                    txtStartDate.setText(tableModel.getValueAt(row, 3).toString());
                    txtEndDate.setText(tableModel.getValueAt(row, 4).toString());
                    txtReason.setText(tableModel.getValueAt(row, 5).toString());
                    cmbStatus.setSelectedItem(tableModel.getValueAt(row, 6).toString());

                }

            }

        });

        // Add Leave
        btnAdd.addActionListener(e -> {

            try {
            	if (txtEmployeeId.getText().trim().isEmpty()) {

            	    JOptionPane.showMessageDialog(this, "Employee ID is required!");

            	    txtEmployeeId.requestFocus();

            	    return;

            	}
            	
            	try {

            	    Integer.parseInt(txtEmployeeId.getText());

            	} catch (NumberFormatException ex) {

            	    JOptionPane.showMessageDialog(this, "Employee ID must be a valid number!");

            	    txtEmployeeId.requestFocus();

            	    return;

            	}
            	
            	if (txtStartDate.getText().trim().isEmpty()) {

            	    JOptionPane.showMessageDialog(this, "Start Date is required!");

            	    txtStartDate.requestFocus();

            	    return;

            	}
            	
            	if (txtEndDate.getText().trim().isEmpty()) {

            	    JOptionPane.showMessageDialog(this, "End Date is required!");

            	    txtEndDate.requestFocus();

            	    return;

            	}
            	
            	if (txtReason.getText().trim().isEmpty()) {

            	    JOptionPane.showMessageDialog(this, "Reason is required!");

            	    txtReason.requestFocus();

            	    return;

            	}
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

        // Update Leave
        btnUpdate.addActionListener(e -> {

            if (selectedLeaveId == -1) {

                JOptionPane.showMessageDialog(this, "Please select a leave request!");

                return;

            }

            try {

                LeaveRequest leave = new LeaveRequest();

                leave.setLeaveId(selectedLeaveId);
                leave.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
                leave.setLeaveType(cmbLeaveType.getSelectedItem().toString());
                leave.setStartDate(txtStartDate.getText());
                leave.setEndDate(txtEndDate.getText());
                leave.setReason(txtReason.getText());
                leave.setStatus(cmbStatus.getSelectedItem().toString());

                LeaveDAO dao = new LeaveDAO();

                if (dao.updateLeave(leave)) {

                    JOptionPane.showMessageDialog(this, "Leave Updated Successfully!");

                    loadLeaves();

                } else {

                    JOptionPane.showMessageDialog(this, "Update Failed!");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Please enter valid data!");

            }

        });
        btnDelete.addActionListener(e -> {

            if (selectedLeaveId == -1) {

                JOptionPane.showMessageDialog(this, "Please select a leave request!");
                return;

            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this leave request?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                LeaveDAO dao = new LeaveDAO();

                if (dao.deleteLeave(selectedLeaveId)) {

                    JOptionPane.showMessageDialog(this, "Leave Deleted Successfully!");

                    loadLeaves();

                    txtEmployeeId.setText("");
                    txtStartDate.setText("");
                    txtEndDate.setText("");
                    txtReason.setText("");

                    cmbLeaveType.setSelectedIndex(0);
                    cmbStatus.setSelectedIndex(0);

                    leaveTable.clearSelection();
                    selectedLeaveId = -1;

                } else {

                    JOptionPane.showMessageDialog(this, "Delete Failed!");

                }

            }

        });
        btnClear.addActionListener(e -> {

            txtEmployeeId.setText("");
            txtStartDate.setText("");
            txtEndDate.setText("");
            txtReason.setText("");

            cmbLeaveType.setSelectedIndex(0);
            cmbStatus.setSelectedIndex(0);

            leaveTable.clearSelection();

            selectedLeaveId = -1;

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