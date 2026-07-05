package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Attendance;
import util.DBConnection;

public class AttendanceDAO {

    public boolean addAttendance(Attendance attendance) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO attendance(employee_id, attendance_date, status) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, attendance.getEmployeeId());
            ps.setString(2, attendance.getAttendanceDate());
            ps.setString(3, attendance.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Attendance> getAllAttendance() {

        List<Attendance> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM attendance";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Attendance attendance = new Attendance();

                attendance.setAttendanceId(rs.getInt("attendance_id"));
                attendance.setEmployeeId(rs.getInt("employee_id"));
                attendance.setAttendanceDate(rs.getString("attendance_date"));
                attendance.setStatus(rs.getString("status"));

                list.add(attendance);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean updateAttendance(Attendance attendance) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE attendance SET employee_id=?, attendance_date=?, status=? WHERE attendance_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, attendance.getEmployeeId());
            ps.setString(2, attendance.getAttendanceDate());
            ps.setString(3, attendance.getStatus());
            ps.setInt(4, attendance.getAttendanceId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}