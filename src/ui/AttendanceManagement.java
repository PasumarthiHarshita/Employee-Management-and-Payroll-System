package ui;

import dao.AttendanceDAO;
import model.Attendance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AttendanceManagement extends JFrame {

    JTextField txtEmployeeId;
    JTextField txtAttendanceDate;
    JComboBox<String> cmbStatus;

    JButton btnAdd;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnClear;

    JTable attendanceTable;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    int selectedAttendanceId = -1;

    public AttendanceManagement() {

        setTitle("Attendance Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Attendance Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        txtEmployeeId = new JTextField();
        txtAttendanceDate = new JTextField();

        cmbStatus = new JComboBox<>(new String[]{
                "Present",
                "Absent",
                "Leave"
        });

        formPanel.add(new JLabel("Employee ID"));
        formPanel.add(txtEmployeeId);

        formPanel.add(new JLabel("Attendance Date"));
        formPanel.add(txtAttendanceDate);

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
                "Attendance ID",
                "Employee ID",
                "Attendance Date",
                "Status"
        });

        attendanceTable = new JTable(tableModel);

        scrollPane = new JScrollPane(attendanceTable);

        add(scrollPane, BorderLayout.EAST);

        // Load Attendance
        loadAttendance();

        // Table Row Selection
        attendanceTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = attendanceTable.getSelectedRow();

                if (row != -1) {

                    selectedAttendanceId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

                    txtEmployeeId.setText(tableModel.getValueAt(row, 1).toString());
                    txtAttendanceDate.setText(tableModel.getValueAt(row, 2).toString());
                    cmbStatus.setSelectedItem(tableModel.getValueAt(row, 3).toString());

                }

            }

        });

        // Add Attendance
        btnAdd.addActionListener(e -> {

            try {

                Attendance attendance = new Attendance();

                attendance.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
                attendance.setAttendanceDate(txtAttendanceDate.getText());
                attendance.setStatus(cmbStatus.getSelectedItem().toString());

                AttendanceDAO dao = new AttendanceDAO();

                if (dao.addAttendance(attendance)) {

                    JOptionPane.showMessageDialog(this, "Attendance Added Successfully!");

                    loadAttendance();

                } else {

                    JOptionPane.showMessageDialog(this, "Failed to Add Attendance!");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Please enter valid data!");

            }

        });

        // Update Attendance
        btnUpdate.addActionListener(e -> {

            if (selectedAttendanceId == -1) {

                JOptionPane.showMessageDialog(this, "Please select an attendance record!");

                return;

            }

            try {

                Attendance attendance = new Attendance();

                attendance.setAttendanceId(selectedAttendanceId);
                attendance.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
                attendance.setAttendanceDate(txtAttendanceDate.getText());
                attendance.setStatus(cmbStatus.getSelectedItem().toString());

                AttendanceDAO dao = new AttendanceDAO();

                if (dao.updateAttendance(attendance)) {

                    JOptionPane.showMessageDialog(this, "Attendance Updated Successfully!");

                    loadAttendance();

                } else {

                    JOptionPane.showMessageDialog(this, "Update Failed!");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Please enter valid data!");

            }

        });
        
        btnDelete.addActionListener(e -> {

            if (selectedAttendanceId == -1) {

                JOptionPane.showMessageDialog(this, "Please select an attendance record!");

                return;

            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this attendance record?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                AttendanceDAO dao = new AttendanceDAO();

                if (dao.deleteAttendance(selectedAttendanceId)) {

                    JOptionPane.showMessageDialog(this, "Attendance Deleted Successfully!");

                    loadAttendance();

                    txtEmployeeId.setText("");
                    txtAttendanceDate.setText("");
                    cmbStatus.setSelectedIndex(0);

                    selectedAttendanceId = -1;

                } else {

                    JOptionPane.showMessageDialog(this, "Delete Failed!");

                }

            }

        });
        
        btnClear.addActionListener(e -> {

            txtEmployeeId.setText("");
            txtAttendanceDate.setText("");

            cmbStatus.setSelectedIndex(0);

            attendanceTable.clearSelection();

            selectedAttendanceId = -1;

        });

        setVisible(true);
    }

    private void loadAttendance() {

        tableModel.setRowCount(0);

        AttendanceDAO dao = new AttendanceDAO();

        List<Attendance> list = dao.getAllAttendance();

        for (Attendance attendance : list) {

            tableModel.addRow(new Object[]{

                    attendance.getAttendanceId(),
                    attendance.getEmployeeId(),
                    attendance.getAttendanceDate(),
                    attendance.getStatus()

            });

        }

    }
}