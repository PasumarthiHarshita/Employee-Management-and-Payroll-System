package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_payroll";
    private static final String USER = "root";
    private static final String PASSWORD = "ranjiththashu@123";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}