package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Payroll;
import util.DBConnection;

public class PayrollDAO {

    public boolean addPayroll(Payroll payroll) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO payroll(employee_id, employee_name, month, basic_salary, payment_date, payment_status) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, payroll.getEmployeeId());
            ps.setString(2, payroll.getEmployeeName());
            ps.setString(3, payroll.getMonth());
            ps.setDouble(4, payroll.getBasicSalary());
            ps.setString(5, payroll.getPaymentDate());
            ps.setString(6, payroll.getPaymentStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Payroll> getAllPayrolls() {

        List<Payroll> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM payroll";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Payroll payroll = new Payroll();

                payroll.setPayrollId(rs.getInt("payroll_id"));
                payroll.setEmployeeId(rs.getInt("employee_id"));
                payroll.setEmployeeName(rs.getString("employee_name"));
                payroll.setMonth(rs.getString("month"));
                payroll.setBasicSalary(rs.getDouble("basic_salary"));
                payroll.setPaymentDate(rs.getString("payment_date"));
                payroll.setPaymentStatus(rs.getString("payment_status"));

                list.add(payroll);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}