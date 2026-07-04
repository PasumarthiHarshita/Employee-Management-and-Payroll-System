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
        btnUpdate.addActionListener(e -> {

            if (selectedDepartmentId == -1) {

                JOptionPane.showMessageDialog(this, "Select a department first!");

                return;

            }

            Department dept = new Department();

            dept.setDepartmentId(selectedDepartmentId);
            dept.setDepartmentName(txtDepartmentName.getText());
            dept.setDepartmentHead(txtDepartmentHead.getText());
            dept.setLocation(txtLocation.getText());

            DepartmentDAO dao = new DepartmentDAO();

            if (dao.updateDepartment(dept)) {

                JOptionPane.showMessageDialog(this, "Department Updated Successfully!");

                loadDepartments();

            } else {

                JOptionPane.showMessageDialog(this, "Update Failed!");

            }

        });
        btnDelete.addActionListener(e -> {

            if (selectedDepartmentId == -1) {

                JOptionPane.showMessageDialog(this, "Please select a department first!");

                return;

            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this department?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                DepartmentDAO dao = new DepartmentDAO();

                if (dao.deleteDepartment(selectedDepartmentId)) {

                    JOptionPane.showMessageDialog(this, "Department Deleted Successfully!");

                    loadDepartments();

                    txtDepartmentName.setText("");
                    txtDepartmentHead.setText("");
                    txtLocation.setText("");

                    selectedDepartmentId = -1;

                } else {

                    JOptionPane.showMessageDialog(this, "Delete Failed!");

                }

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
        loadDepartments();

        departmentTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = departmentTable.getSelectedRow();

                if (row != -1) {

                    selectedDepartmentId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

                    txtDepartmentName.setText(tableModel.getValueAt(row, 1).toString());
                    txtDepartmentHead.setText(tableModel.getValueAt(row, 2).toString());
                    txtLocation.setText(tableModel.getValueAt(row, 3).toString());

                }

            }

        });

        setVisible(true);
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