package ui;

import dao.EmployeeDAO;
import model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeReport extends JFrame {

    JTable table;
    DefaultTableModel tableModel;

    public EmployeeReport() {

        setTitle("Employee Report");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Employee Report", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,24));
        add(title, BorderLayout.NORTH);

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

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

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