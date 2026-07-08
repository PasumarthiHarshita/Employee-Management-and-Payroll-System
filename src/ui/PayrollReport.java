package ui;

import dao.PayrollDAO;
import model.Payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PayrollReport extends JFrame {

    JTable table;
    DefaultTableModel tableModel;

    public PayrollReport() {

        setTitle("Payroll Report");
        setSize(1000,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Payroll Report", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,24));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[]{
                "Payroll ID",
                "Employee ID",
                "Employee Name",
                "Month",
                "Basic Salary",
                "Payment Date",
                "Status"
        });

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadPayrolls();

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