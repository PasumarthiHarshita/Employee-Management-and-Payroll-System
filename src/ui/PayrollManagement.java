package ui;

import dao.PayrollDAO;
import model.Payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PayrollManagement extends JFrame {

    JTextField txtEmployeeId;
    JTextField txtEmployeeName;
    JTextField txtMonth;
    JTextField txtSalary;
    JTextField txtPaymentDate;

    JComboBox<String> cmbStatus;

    JButton btnAdd;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnClear;

    JTable payrollTable;
    DefaultTableModel tableModel;

    int selectedPayrollId = -1;

    public PayrollManagement() {

        setTitle("Payroll Management");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Payroll Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,24));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6,2,10,10));

        txtEmployeeId = new JTextField();
        txtEmployeeName = new JTextField();
        txtMonth = new JTextField();
        txtSalary = new JTextField();
        txtPaymentDate = new JTextField();

        cmbStatus = new JComboBox<>(new String[]{
                "Paid",
                "Pending"
        });

        formPanel.add(new JLabel("Employee ID"));
        formPanel.add(txtEmployeeId);

        formPanel.add(new JLabel("Employee Name"));
        formPanel.add(txtEmployeeName);

        formPanel.add(new JLabel("Month"));
        formPanel.add(txtMonth);

        formPanel.add(new JLabel("Basic Salary"));
        formPanel.add(txtSalary);

        formPanel.add(new JLabel("Payment Date"));
        formPanel.add(txtPaymentDate);

        formPanel.add(new JLabel("Payment Status"));
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
                "Employee Name",
                "Month",
                "Salary",
                "Payment Date",
                "Status"
        });

        payrollTable = new JTable(tableModel);

        add(new JScrollPane(payrollTable), BorderLayout.EAST);

        loadPayrolls();
        payrollTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = payrollTable.getSelectedRow();

                if (row != -1) {

                    selectedPayrollId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

                    txtEmployeeId.setText(tableModel.getValueAt(row, 1).toString());
                    txtEmployeeName.setText(tableModel.getValueAt(row, 2).toString());
                    txtMonth.setText(tableModel.getValueAt(row, 3).toString());
                    txtSalary.setText(tableModel.getValueAt(row, 4).toString());
                    txtPaymentDate.setText(tableModel.getValueAt(row, 5).toString());
                    cmbStatus.setSelectedItem(tableModel.getValueAt(row, 6).toString());

                }

            }

        });
        btnAdd.addActionListener(e -> {

            try {

                Payroll payroll = new Payroll();

                payroll.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
                payroll.setEmployeeName(txtEmployeeName.getText());
                payroll.setMonth(txtMonth.getText());
                payroll.setBasicSalary(Double.parseDouble(txtSalary.getText()));
                payroll.setPaymentDate(txtPaymentDate.getText());
                payroll.setPaymentStatus(cmbStatus.getSelectedItem().toString());

                PayrollDAO dao = new PayrollDAO();

                if (dao.addPayroll(payroll)) {

                    JOptionPane.showMessageDialog(this, "Payroll Added Successfully!");

                    loadPayrolls();

                } else {

                    JOptionPane.showMessageDialog(this, "Failed to Add Payroll!");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Please enter valid data!");

            }

        });
        btnUpdate.addActionListener(e -> {

            if (selectedPayrollId == -1) {

                JOptionPane.showMessageDialog(this, "Please select a payroll record!");
                return;

            }

            try {

                Payroll payroll = new Payroll();

                payroll.setPayrollId(selectedPayrollId);
                payroll.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
                payroll.setEmployeeName(txtEmployeeName.getText());
                payroll.setMonth(txtMonth.getText());
                payroll.setBasicSalary(Double.parseDouble(txtSalary.getText()));
                payroll.setPaymentDate(txtPaymentDate.getText());
                payroll.setPaymentStatus(cmbStatus.getSelectedItem().toString());

                PayrollDAO dao = new PayrollDAO();

                if (dao.updatePayroll(payroll)) {

                    JOptionPane.showMessageDialog(this, "Payroll Updated Successfully!");

                    loadPayrolls();

                } else {

                    JOptionPane.showMessageDialog(this, "Update Failed!");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Please enter valid data!");

            }

        });
        setVisible(true);
    }

    private void loadPayrolls() {

        tableModel.setRowCount(0);

        PayrollDAO dao = new PayrollDAO();

        List<Payroll> list = dao.getAllPayrolls();

        for (Payroll payroll : list) {

            tableModel.addRow(new Object[]{

                    payroll.getPayrollId(),
                    payroll.getEmployeeId(),
                    payroll.getEmployeeName(),
                    payroll.getMonth(),
                    payroll.getBasicSalary(),
                    payroll.getPaymentDate(),
                    payroll.getPaymentStatus()

            });

        }

    }
}