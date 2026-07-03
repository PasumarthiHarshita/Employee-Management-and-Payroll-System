package ui;
import dao.DepartmentDAO;
import model.Department;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DepartmentManagement extends JFrame {

    JTextField txtDepartmentName;
    JTextField txtDepartmentHead;
    JTextField txtLocation;

    JButton btnAdd;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnClear;

    JTable departmentTable;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    int selectedDepartmentId = -1;

    public DepartmentManagement() {

        setTitle("Department Management");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Department Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3,2,10,10));

        txtDepartmentName = new JTextField();
        txtDepartmentHead = new JTextField();
        txtLocation = new JTextField();

        formPanel.add(new JLabel("Department Name"));
        formPanel.add(txtDepartmentName);

        formPanel.add(new JLabel("Department Head"));
        formPanel.add(txtDepartmentHead);

        formPanel.add(new JLabel("Location"));
        formPanel.add(txtLocation);

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
        btnAdd.addActionListener(e -> {

            Department dept = new Department();

            dept.setDepartmentName(txtDepartmentName.getText());
            dept.setDepartmentHead(txtDepartmentHead.getText());
            dept.setLocation(txtLocation.getText());

            DepartmentDAO dao = new DepartmentDAO();

            if(dao.addDepartment(dept)){

                JOptionPane.showMessageDialog(this,"Department Added Successfully!");

                loadDepartments();

            }else{

                JOptionPane.showMessageDialog(this,"Failed!");

            }

        });

        add(buttonPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[]{
                "ID",
                "Department",
                "Department Head",
                "Location"
        });

        departmentTable = new JTable(tableModel);

        scrollPane = new JScrollPane(departmentTable);

        add(scrollPane, BorderLayout.EAST);
        loadDepartments();
        setVisible(true);
    }
    private void loadDepartments(){

        tableModel.setRowCount(0);

        DepartmentDAO dao = new DepartmentDAO();

        List<Department> list = dao.getAllDepartments();

        for(Department dept : list){

            tableModel.addRow(new Object[]{

                    dept.getDepartmentId(),
                    dept.getDepartmentName(),
                    dept.getDepartmentHead(),
                    dept.getLocation()

            });

        }

    }
}