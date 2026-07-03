package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import util.DBConnection;

public class DepartmentDAO {

    public boolean addDepartment(Department dept) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO departments(department_name, department_head, location) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dept.getDepartmentName());
            ps.setString(2, dept.getDepartmentHead());
            ps.setString(3, dept.getLocation());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Department> getAllDepartments() {

        List<Department> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM departments";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Department dept = new Department();

                dept.setDepartmentId(rs.getInt("department_id"));
                dept.setDepartmentName(rs.getString("department_name"));
                dept.setDepartmentHead(rs.getString("department_head"));
                dept.setLocation(rs.getString("location"));

                list.add(dept);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

}