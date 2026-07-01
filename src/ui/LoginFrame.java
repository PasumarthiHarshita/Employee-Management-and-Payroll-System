package ui;
import model.User;
import javax.swing.*;
import java.awt.*;
import dao.UserDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.AdminDashboard;
import ui.HRDashboard;
import ui.EmployeeDashboard;
public class LoginFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginFrame() {

        setTitle("Employee Management and Payroll System");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                UserDAO dao = new UserDAO();

                User user = dao.login(username, password);

                if (user != null) {

                    JOptionPane.showMessageDialog(null, "Login Successful");

                    dispose();   // Close Login Window

                    if (user.getRole().equals("ADMIN")) {

                        new AdminDashboard();

                    } else if (user.getRole().equals("HR")) {

                        new HRDashboard();

                    } else {

                        new EmployeeDashboard();

                    }

                } else {

                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");

                }
            }
        });
        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);

        setVisible(true);
    }
}