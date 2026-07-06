package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.LeaveRequest;
import util.DBConnection;

public class LeaveDAO {

    public boolean addLeave(LeaveRequest leave) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO leave_requests(employee_id, leave_type, start_date, end_date, reason, status) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, leave.getEmployeeId());
            ps.setString(2, leave.getLeaveType());
            ps.setString(3, leave.getStartDate());
            ps.setString(4, leave.getEndDate());
            ps.setString(5, leave.getReason());
            ps.setString(6, leave.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<LeaveRequest> getAllLeaves() {

        List<LeaveRequest> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM leave_requests";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LeaveRequest leave = new LeaveRequest();

                leave.setLeaveId(rs.getInt("leave_id"));
                leave.setEmployeeId(rs.getInt("employee_id"));
                leave.setLeaveType(rs.getString("leave_type"));
                leave.setStartDate(rs.getString("start_date"));
                leave.setEndDate(rs.getString("end_date"));
                leave.setReason(rs.getString("reason"));
                leave.setStatus(rs.getString("status"));

                list.add(leave);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean updateLeave(LeaveRequest leave) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE leave_requests SET employee_id=?, leave_type=?, start_date=?, end_date=?, reason=?, status=? WHERE leave_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, leave.getEmployeeId());
            ps.setString(2, leave.getLeaveType());
            ps.setString(3, leave.getStartDate());
            ps.setString(4, leave.getEndDate());
            ps.setString(5, leave.getReason());
            ps.setString(6, leave.getStatus());
            ps.setInt(7, leave.getLeaveId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}