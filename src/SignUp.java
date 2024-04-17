import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends javax.swing.JFrame {
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton createAccountButton;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JLabel contactNumberLabel;
    private javax.swing.JTextField contactNumberField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JComboBox<String> userTypeComboBox;

    public SignUp() {
        initComponents();
        centerFrame(); // Call method to center the frame
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        confirmPasswordLabel = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        contactNumberLabel = new javax.swing.JLabel();
        contactNumberField = new javax.swing.JTextField();
        createAccountButton = new javax.swing.JButton();
        userTypeComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

//        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 28));
//        jLabel1.setText("Sign Up");
//        getContentPane().add(jLabel1);
//        jLabel1.setBounds(0, 0, 800, 50);

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        nameLabel.setForeground(new java.awt.Color(0, 0, 0));
        nameLabel.setText("Name:");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(50, 50, 80, 20);
        getContentPane().add(nameField);
        nameField.setBounds(180, 50, 180, 30);

        usernameLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        usernameLabel.setForeground(new java.awt.Color(0, 0, 0));
        usernameLabel.setText("Username:");
        getContentPane().add(usernameLabel);
        usernameLabel.setBounds(50, 100, 80, 20);
        getContentPane().add(usernameField);
        usernameField.setBounds(180, 100, 180, 30);

        passwordLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        passwordLabel.setForeground(new java.awt.Color(0, 0, 0));
        passwordLabel.setText("Password:");
        getContentPane().add(passwordLabel);
        passwordLabel.setBounds(50, 150, 80, 20);
        getContentPane().add(passwordField);
        passwordField.setBounds(180, 150, 180, 30);

        confirmPasswordLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        confirmPasswordLabel.setForeground(new java.awt.Color(0, 0, 0));
        confirmPasswordLabel.setText("Confirm Password:");
        getContentPane().add(confirmPasswordLabel);
        confirmPasswordLabel.setBounds(50, 200, 120, 20);
        getContentPane().add(confirmPasswordField);
        confirmPasswordField.setBounds(180, 200, 180, 30);

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        emailLabel.setForeground(new java.awt.Color(0, 0, 0));
        emailLabel.setText("Email:");
        getContentPane().add(emailLabel);
        emailLabel.setBounds(50, 250, 80, 20);
        getContentPane().add(emailField);
        emailField.setBounds(180, 250, 180, 30);

        contactNumberLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        contactNumberLabel.setForeground(new java.awt.Color(0, 0, 0));
        contactNumberLabel.setText("Contact Number:");
        getContentPane().add(contactNumberLabel);
        contactNumberLabel.setBounds(50, 300, 120, 20);
        getContentPane().add(contactNumberField);
        contactNumberField.setBounds(180, 300, 180, 30);

        userTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Doctor", "Patient", "Admin"}));
        userTypeComboBox.setBounds(180, 400, 180, 30);
        getContentPane().add(userTypeComboBox);

        createAccountButton.setBackground(new java.awt.Color(204, 0, 100));
        createAccountButton.setFont(new java.awt.Font("Tahoma", 1, 14));
        createAccountButton.setForeground(new java.awt.Color(255, 255, 255));
        createAccountButton.setText("Sign Up");
        getContentPane().add(createAccountButton);
        createAccountButton.setBounds(180, 450, 180, 40);

        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });

        // Set background color for the entire frame
        getContentPane().setBackground(new java.awt.Color(204, 204, 255));

        // Set title for the frame
        setTitle("SignUp");

        pack();
    }

    private void centerFrame() {
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private boolean isPasswordValid(String password) {
        // At least 8 characters
        if (password.length() < 8) {
            return false;
        }

        // At least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // At least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // At least one special character like @,#,$,%,^,&,*,+,-
        Pattern specialCharPattern = Pattern.compile("[@#$%^&*+-]");
        Matcher matcher = specialCharPattern.matcher(password);
        return matcher.find();
    }

    private boolean doPasswordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameAvailable(String username) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");
            String query = "SELECT * FROM UserCredentials WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next(); // If resultSet.next() returns false, it means username is available
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of exception
        }
    }

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {

        // Generate a unique 12-digit PHR ID
//        String phrId = generateUniquePHRId();

        // Display the PHR ID in a pop-up message
//        JOptionPane.showMessageDialog(null, "Your PHR ID is: " + phrId);

        String name = nameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String email = emailField.getText();
        String contactNumber = contactNumberField.getText();
        String userType = (String) userTypeComboBox.getSelectedItem();

        // Validate inputs
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || contactNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        // Check if passwords match
        if (!doPasswordsMatch(password, confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return;
        }

        // Check password complexity
        if (!isPasswordValid(password)) {
            JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters, one uppercase letter, one lowercase letter, and one special character.");
            return;
        }

        // Check if username is available
        if (!isUsernameAvailable(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists.");
            return;
        }

        // Store the user details in the database
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");
            String query = "INSERT INTO UserCredentials (Username, Password, Email, ContactNumber, UserType) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, contactNumber);
            statement.setString(5, userType);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Account created successfully!");

                // Redirect the user to the appropriate home page based on user type
                switch (userType) {
                    case "Doctor":
                        DoctorHomePage doctorHomePage = new DoctorHomePage();
                        doctorHomePage.setVisible(true);
                        break;
                    case "Patient":
                        PatientHomePage patientHomePage = new PatientHomePage();
                        patientHomePage.setVisible(true);
                        break;
                    case "Admin":
                        AdminHomePage adminHomePage = new AdminHomePage();
                        adminHomePage.setVisible(true);
//                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid user type.");
                        break;
                }

                dispose(); // Close the sign-up window
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }
}

