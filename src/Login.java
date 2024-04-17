import javax.swing.*;
import java.sql.*;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        centerFrame(); // Call method to center the frame
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        pass1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton2ActionPerformed = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600); // Set the size of the frame

        jPanel1.setLayout(null);
        jPanel2.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204)); // Change background color here
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36));
        jLabel1.setText("Welcome!");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(305, 305, 305)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(343, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 800, 110);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

//        jLabel2.setFont(new java.awt.Font("Arial", 1, 18));
//        jLabel2.setText("Login");
//        jLabel2.setBounds(0, -10, 380, 20); // Set position (x, y, width, height)

        jLabel3.setText("Enter Username:");

        jLabel4.setText("Enter Password:");

        usernameField.setText("Username");
        usernameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameFieldMouseClicked(evt);
            }
        });

        pass1.setText("Password");
        pass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pass1MouseClicked(evt);
            }
        });
        pass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 0, 100));
        jButton1.setText("login");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Don't have an Account?");

        jButton2ActionPerformed.setBackground(new java.awt.Color(204, 0, 100));
        jButton2ActionPerformed.setText("SignUp");
        jButton2ActionPerformed.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2ActionPerformed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                                        .addComponent(pass1)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton2ActionPerformed, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
                                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(pass1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jButton2ActionPerformed, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 110, 800, 390);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 600); // Adjusted to fit the entire page
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Get the entered username and password
        String username = usernameField.getText();
        String password = new String(pass1.getPassword());

        // Validate if username and password are provided
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both username and password.");
            return;
        }

        // Authenticate the user
        String userType = authenticateUser(username, password);

        // Open respective home page based on user type
        if (userType != null) {
            switch (userType) {
                case "Admin":
                    // Open admin home page
                    AdminHomePage adminHomePage = new AdminHomePage();
                    adminHomePage.setVisible(true);
                    dispose(); // Close the login window
                    break;
                case "Doctor":
                    // Open doctor home page
                    DoctorHomePage doctorHomePage = new DoctorHomePage();
                    doctorHomePage.setVisible(true);
                    dispose(); // Close the login window
                    break;
                case "Patient":
                    // Open patient home page
                    PatientHomePage patientHomePage = new PatientHomePage();
                    patientHomePage.setVisible(true);
                    dispose(); // Close the login window
                    break;
                default:
                    // Invalid user type or authentication failed
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Login failed.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials. Login failed.");
        }
    }

    private void pass1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        SignUp signUpWindow = new SignUp();
        signUpWindow.setVisible(true);
        dispose(); //
        // This method should handle sign up action
    }

    private void usernameFieldMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        usernameField.setText("");
    }

    private void pass1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        pass1.setText("");
    }

    private String authenticateUser(String username, String password) {
        // Database connection details
        String url = "jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE";
        String user = "SYSTEM";
        String pass = "Jk2122";

        // Initialize connection and prepared statement
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            // SQL query to check the user type
            String query = "SELECT UserType FROM UserCredentials WHERE Username = ? AND Password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // Check if the result set contains any rows
            if (resultSet.next()) {
                // Return the user type
                return resultSet.getString("UserType");
            } else {
                // No such account found
                return null;
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return null;
        }
    }

    private void centerFrame() {
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2ActionPerformed;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField pass1;
    private javax.swing.JTextField usernameField;

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
