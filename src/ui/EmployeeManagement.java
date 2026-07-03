package ui;

import dao.EmployeeDAO;
import model.Employee;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeManagement extends JFrame {

    // Labels
    JLabel lblTitle;
    JLabel lblFirstName, lblLastName, lblGender, lblEmail, lblPhone;
    JLabel lblDepartment, lblDesignation, lblJoiningDate, lblSalary, lblStatus;

    // Text Fields
    JTextField txtFirstName;
    JTextField txtLastName;
    JTextField txtEmail;
    JTextField txtPhone;
    JTextField txtDepartment;
    JTextField txtDesignation;
    JTextField txtJoiningDate;
    JTextField txtSalary;

    // Combo Boxes
    JComboBox<String> cmbGender;
    JComboBox<String> cmbStatus;

    // Buttons
    JButton btnAdd;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnSearch;
    JButton btnClear;

    // Table
    JTable employeeTable;
    DefaultTableModel tableModel;
    int selectedEmployeeId = -1;
    JScrollPane scrollPane;
    
    public EmployeeManagement() {

        setTitle("Employee Management System");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        lblTitle = new JLabel("Employee Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5,4,10,10));

        lblFirstName = new JLabel("First Name");
        txtFirstName = new JTextField();

        lblLastName = new JLabel("Last Name");
        txtLastName = new JTextField();

        lblGender = new JLabel("Gender");
        cmbGender = new JComboBox<>(new String[]{"Male","Female","Other"});

        lblEmail = new JLabel("Email");
        txtEmail = new JTextField();

        lblPhone = new JLabel("Phone");
        txtPhone = new JTextField();

        lblDepartment = new JLabel("Department");
        txtDepartment = new JTextField();

        lblDesignation = new JLabel("Designation");
        txtDesignation = new JTextField();

        lblJoiningDate = new JLabel("Joining Date");
        txtJoiningDate = new JTextField();

        lblSalary = new JLabel("Salary");
        txtSalary = new JTextField();

        lblStatus = new JLabel("Status");
        cmbStatus = new JComboBox<>(new String[]{"Active","Inactive"});

        formPanel.add(lblFirstName);
        formPanel.add(txtFirstName);

        formPanel.add(lblLastName);
        formPanel.add(txtLastName);

        formPanel.add(lblGender);
        formPanel.add(cmbGender);

        formPanel.add(lblEmail);
        formPanel.add(txtEmail);

        formPanel.add(lblPhone);
        formPanel.add(txtPhone);

        formPanel.add(lblDepartment);
        formPanel.add(txtDepartment);

        formPanel.add(lblDesignation);
        formPanel.add(txtDesignation);

        formPanel.add(lblJoiningDate);
        formPanel.add(txtJoiningDate);

        formPanel.add(lblSalary);
        formPanel.add(txtSalary);

        formPanel.add(lblStatus);
        formPanel.add(cmbStatus);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSearch = new JButton("Search");
        btnClear = new JButton("Clear");
        btnUpdate.addActionListener(e -> {

            if (selectedEmployeeId == -1) {
                JOptionPane.showMessageDialog(this, "Please select an employee first!");
                return;
            }

            try {

                Employee emp = new Employee();

                emp.setEmployeeId(selectedEmployeeId);
                emp.setFirstName(txtFirstName.getText());
                emp.setLastName(txtLastName.getText());
                emp.setGender(cmbGender.getSelectedItem().toString());
                emp.setEmail(txtEmail.getText());
                emp.setPhone(txtPhone.getText());
                emp.setDepartment(txtDepartment.getText());
                emp.setDesignation(txtDesignation.getText());
                emp.setJoiningDate(txtJoiningDate.getText());
                emp.setBasicSalary(Double.parseDouble(txtSalary.getText()));
                emp.setStatus(cmbStatus.getSelectedItem().toString());

                EmployeeDAO dao = new EmployeeDAO();

                if (dao.updateEmployee(emp)) {

                    JOptionPane.showMessageDialog(this, "Employee Updated Successfully!");

                    loadEmployees();

                } else {

                    JOptionPane.showMessageDialog(this, "Update Failed!");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Invalid Input!");

            }

        });
        btnDelete.addActionListener(e -> {

            if (selectedEmployeeId == -1) {
                JOptionPane.showMessageDialog(this, "Please select an employee first!");
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this employee?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                EmployeeDAO dao = new EmployeeDAO();

                if (dao.deleteEmployee(selectedEmployeeId)) {

                    JOptionPane.showMessageDialog(this, "Employee Deleted Successfully!");

                    loadEmployees();

                    selectedEmployeeId = -1;

                } else {

                    JOptionPane.showMessageDialog(this, "Delete Failed!");

                }

            }

        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnClear);

        add(buttonPanel, BorderLayout.SOUTH);
     // Create Table
        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[]{
                "ID",
                "First Name",
                "Last Name",
                "Gender",
                "Email",
                "Phone",
                "Department",
                "Designation",
                "Joining Date",
                "Salary",
                "Status"
        });

        employeeTable = new JTable(tableModel);

        scrollPane = new JScrollPane(employeeTable);

        add(scrollPane, BorderLayout.EAST);
        loadEmployees();
        employeeTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    int row = employeeTable.getSelectedRow();

                    if (row != -1) {
                    	selectedEmployeeId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

                        txtFirstName.setText(tableModel.getValueAt(row, 1).toString());
                        txtLastName.setText(tableModel.getValueAt(row, 2).toString());
                        cmbGender.setSelectedItem(tableModel.getValueAt(row, 3).toString());
                        txtEmail.setText(tableModel.getValueAt(row, 4).toString());
                        txtPhone.setText(tableModel.getValueAt(row, 5).toString());
                        txtDepartment.setText(tableModel.getValueAt(row, 6).toString());
                        txtDesignation.setText(tableModel.getValueAt(row, 7).toString());
                        txtJoiningDate.setText(tableModel.getValueAt(row, 8).toString());
                        txtSalary.setText(tableModel.getValueAt(row, 9).toString());
                        cmbStatus.setSelectedItem(tableModel.getValueAt(row, 10).toString());

                    }

                }

            }

        });
        setVisible(true);
    }
    private void loadEmployees() {

        tableModel.setRowCount(0);

        EmployeeDAO dao = new EmployeeDAO();

        List<Employee> employees = dao.getAllEmployees();

        for (Employee emp : employees) {

        	tableModel.addRow(new Object[]{
        	        emp.getEmployeeId(),
        	        emp.getFirstName(),
        	        emp.getLastName(),
        	        emp.getGender(),
        	        emp.getEmail(),
        	        emp.getPhone(),
        	        emp.getDepartment(),
        	        emp.getDesignation(),
        	        emp.getJoiningDate(),
        	        emp.getBasicSalary(),
        	        emp.getStatus()
        	});

        }

    }

}