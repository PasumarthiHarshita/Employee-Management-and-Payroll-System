package ui;

import dao.EmployeeDAO;
import model.Employee;

import javax.swing.*;
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
        btnAdd.addActionListener(e -> {

            try {

                Employee emp = new Employee();

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

                if(dao.addEmployee(emp)){

                    JOptionPane.showMessageDialog(this,"Employee Added Successfully!");

                    loadEmployees();

                }else{

                    JOptionPane.showMessageDialog(this,"Failed to Add Employee!");

                }

            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,"Invalid Input!");

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
                "Department",
                "Designation",
                "Salary",
                "Status"
        });

        employeeTable = new JTable(tableModel);

        scrollPane = new JScrollPane(employeeTable);

        add(scrollPane, BorderLayout.EAST);
        loadEmployees();
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
                    emp.getDepartment(),
                    emp.getDesignation(),
                    emp.getBasicSalary(),
                    emp.getStatus()

            });

        }

    }

}