package ui;

import javax.swing.*;
import java.awt.*;
import dao.EmployeeDAO;
import model.Employee;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
public class EmployeeManagement extends JFrame {

    JTextField txtFirstName;
    JTextField txtLastName;
    JComboBox<String> cmbGender;
    JTextField txtEmail;
    JTextField txtPhone;
    JTextField txtDepartment;
    JTextField txtDesignation;
    JTextField txtJoiningDate;
    JTextField txtSalary;
    JComboBox<String> cmbStatus;
    JTable employeeTable;
    DefaultTableModel tableModel;
    JButton btnAdd;

    public EmployeeManagement() {

        setTitle("Employee Management");

        setSize(600,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(11,2,10,10));

        txtFirstName = new JTextField();

        txtLastName = new JTextField();

        cmbGender = new JComboBox<>(new String[]{"Male","Female","Other"});

        txtEmail = new JTextField();

        txtPhone = new JTextField();

        txtDepartment = new JTextField();

        txtDesignation = new JTextField();

        txtJoiningDate = new JTextField();

        txtSalary = new JTextField();

        cmbStatus = new JComboBox<>(new String[]{"Active","Inactive"});

        btnAdd = new JButton("Add Employee");
        btnAdd.addActionListener(e -> {

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

            if (dao.addEmployee(emp)) {
                JOptionPane.showMessageDialog(this, "Employee Added Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Add Employee!");
            }
        });
        add(new JLabel("First Name"));
        add(txtFirstName);

        add(new JLabel("Last Name"));
        add(txtLastName);

        add(new JLabel("Gender"));
        add(cmbGender);

        add(new JLabel("Email"));
        add(txtEmail);

        add(new JLabel("Phone"));
        add(txtPhone);

        add(new JLabel("Department"));
        add(txtDepartment);

        add(new JLabel("Designation"));
        add(txtDesignation);

        add(new JLabel("Joining Date (YYYY-MM-DD)"));
        add(txtJoiningDate);

        add(new JLabel("Basic Salary"));
        add(txtSalary);

        add(new JLabel("Status"));
        add(cmbStatus);

        add(new JLabel());

        add(btnAdd);

        setVisible(true);

    }

}