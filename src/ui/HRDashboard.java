package ui;

import javax.swing.*;

public class HRDashboard extends JFrame {

    public HRDashboard() {

        setTitle("HR Dashboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome HR", SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}