package ui;

import dao.AttendanceDAO;
import model.Attendance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AttendanceReport extends JFrame {

    JTable table;
    DefaultTableModel tableModel;

    public AttendanceReport() {

        setTitle("Attendance Report");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Attendance Report", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,24));
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();

        tableModel.setColumnIdentifiers(new String[]{
                "Attendance ID",
                "Employee ID",
                "Attendance Date",
                "Status"
        });

        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadAttendance();

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