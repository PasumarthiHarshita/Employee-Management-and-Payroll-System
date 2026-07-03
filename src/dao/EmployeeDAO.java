package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.Employee;
import util.DBConnection;

public class EmployeeDAO {

    public boolean addEmployee(Employee emp) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO employees(first_name,last_name,gender,email,phone,department,designation,joining_date,basic_salary,status) VALUES(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getGender());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setString(6, emp.getDepartment());
            ps.setString(7, emp.getDesignation());
            ps.setString(8, emp.getJoiningDate());
            ps.setDouble(9, emp.getBasicSalary());
            ps.setString(10, emp.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM employees";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee emp = new Employee();

                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender(rs.getString("gender"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setDepartment(rs.getString("department"));
                emp.setDesignation(rs.getString("designation"));
                emp.setJoiningDate(rs.getString("joining_date"));
                emp.setBasicSalary(rs.getDouble("basic_salary"));
                emp.setStatus(rs.getString("status"));

                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean updateEmployee(Employee emp) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE employees SET first_name=?, last_name=?, gender=?, email=?, phone=?, department=?, designation=?, joining_date=?, basic_salary=?, status=? WHERE employee_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getGender());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setString(6, emp.getDepartment());
            ps.setString(7, emp.getDesignation());
            ps.setString(8, emp.getJoiningDate());
            ps.setDouble(9, emp.getBasicSalary());
            ps.setString(10, emp.getStatus());
            ps.setInt(11, emp.getEmployeeId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteEmployee(int employeeId) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM employees WHERE employee_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, employeeId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public Employee searchEmployee(int employeeId) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM employees WHERE employee_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Employee emp = new Employee();

                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender(rs.getString("gender"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setDepartment(rs.getString("department"));
                emp.setDesignation(rs.getString("designation"));
                emp.setJoiningDate(rs.getString("joining_date"));
                emp.setBasicSalary(rs.getDouble("basic_salary"));
                emp.setStatus(rs.getString("status"));

                return emp;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}