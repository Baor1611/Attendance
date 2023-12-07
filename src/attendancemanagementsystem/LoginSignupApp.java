package attendancemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginSignupApp {
    JFrame frame;
    private JTextField loginUsernameField, signupUsernameField;
    private JPasswordField loginPasswordField, signupPasswordField, confirmPasswordField;


    public LoginSignupApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        JPanel loginPanel = new JPanel();
        frame.getContentPane().add(loginPanel, "loginPanel");
        loginPanel.setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblLogin.setBounds(194, 25, 46, 14);
        loginPanel.add(lblLogin);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(68, 70, 66, 14);
        loginPanel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(68, 118, 66, 14);
        loginPanel.add(lblPassword);

        loginUsernameField = new JTextField();
        loginUsernameField.setBounds(164, 67, 137, 20);
        loginPanel.add(loginUsernameField);
        loginUsernameField.setColumns(10);

        loginPasswordField = new JPasswordField();
        loginPasswordField.setBounds(164, 115, 137, 20);
        loginPanel.add(loginPasswordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener((ActionEvent e) -> {
            String username = loginUsernameField.getText();
            String password = new String(loginPasswordField.getPassword());
            // Implement login functionality here
            // Check credentials against the database
            // For simplicity, let's just print them for now
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        });
        btnLogin.setBounds(164, 166, 89, 23);
        loginPanel.add(btnLogin);

        JPanel signupPanel = new JPanel();
        frame.getContentPane().add(signupPanel, "signupPanel");
        signupPanel.setLayout(null);

        JLabel lblSignup = new JLabel("Sign Up");
        lblSignup.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSignup.setBounds(183, 22, 79, 20);
        signupPanel.add(lblSignup);

        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setBounds(71, 67, 66, 14);
        signupPanel.add(lblNewLabel);

        JLabel lblPassword_1 = new JLabel("Password:");
        lblPassword_1.setBounds(71, 108, 66, 14);
        signupPanel.add(lblPassword_1);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setBounds(71, 147, 114, 14);
        signupPanel.add(lblConfirmPassword);

        signupUsernameField = new JTextField();
        signupUsernameField.setBounds(204, 64, 137, 20);
        signupPanel.add(signupUsernameField);
        signupUsernameField.setColumns(10);

        signupPasswordField = new JPasswordField();
        signupPasswordField.setBounds(204, 105, 137, 20);
        signupPanel.add(signupPasswordField);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(204, 144, 137, 20);
        signupPanel.add(confirmPasswordField);

        JButton btnSignup = new JButton("Sign Up");
        btnSignup.addActionListener((ActionEvent e) -> {
            String username = signupUsernameField.getText();
            String password = new String(signupPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Implement signup functionality here
            // Insert user data into the database
            insertUserData(username, password);
            
            // For simplicity, let's just print the data for now
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        });
        btnSignup.setBounds(204, 185, 89, 23);
        signupPanel.add(btnSignup);

        JButton btnGoToLogin = new JButton("Go to Login");
        btnGoToLogin.addActionListener((ActionEvent e) -> {
            CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
            cardLayout.show(frame.getContentPane(), "loginPanel");
        });
        btnGoToLogin.setBounds(10, 227, 114, 23);
        signupPanel.add(btnGoToLogin);

        JButton btnGoToSignup = new JButton("Go to Sign Up");
        btnGoToSignup.addActionListener((ActionEvent e) -> {
            CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
            cardLayout.show(frame.getContentPane(), "signupPanel");
        });
        btnGoToSignup.setBounds(297, 227, 127, 23);
        loginPanel.add(btnGoToSignup);
    }

    private void insertUserData(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error occurred while signing up.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}