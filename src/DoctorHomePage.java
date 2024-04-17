import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorHomePage extends JFrame {

    public DoctorHomePage() {
        initComponents();
        centerFrame();
    }

    private void saveFeedback(String feedback) {
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            // Create a PreparedStatement with the INSERT query for feedback
            String query = "INSERT INTO Feedback (feedback_text) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the feedback parameter in the PreparedStatement
            statement.setString(1, feedback);

            // Execute the INSERT query
            int rowsInserted = statement.executeUpdate();

            // Check if the feedback was successfully inserted
            if (rowsInserted > 0) {
                System.out.println("Feedback saved successfully!");
            } else {
                System.out.println("Failed to save feedback.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveData(String firstName, String lastName, String specialization, String age, String nationality, String gender, String contactNumber, String email) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            // Prepare the SQL statement
            String query = "INSERT INTO DoctorDetails (FirstName, LastName, Specialization, Age, Nationality, Gender, ContactNumber, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set parameters in the prepared statement
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, specialization);
            statement.setString(4, age);
            statement.setString(5, nationality);
            statement.setString(6, gender);
            statement.setString(7, contactNumber);
            statement.setString(8, email);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            // Check if data was successfully inserted
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data saved successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save data.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void saveComplaint(String complaint) {
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            // Create a PreparedStatement with the INSERT query
            String query = "INSERT INTO Report (complaint) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the complaint parameter in the PreparedStatement
            statement.setString(1, complaint);

            // Execute the INSERT query
            int rowsInserted = statement.executeUpdate();

            // Check if the complaint was successfully inserted
            if (rowsInserted > 0) {
                System.out.println("Complaint saved successfully!");
            } else {
                System.out.println("Failed to save complaint.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class ProfileStatus {
        boolean profileCreated = false;
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Doctor Home Page");
        setSize(800, 600);
//        boolean profileCreated = false;

        ProfileStatus profileStatus = new ProfileStatus();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(92, 90, 93, 171)); // Set background color

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(204, 50, 100)); // Set background color for tabs
        tabbedPane.setForeground(Color.BLACK);
        panel.add(tabbedPane, BorderLayout.CENTER);

        // Create an "Update Profile" panel
        JPanel updateProfilePanel = new JPanel();
        updateProfilePanel.setBackground(new Color(205, 204, 240));
        updateProfilePanel.setLayout(null); // Use absolute layout for custom positioning of components

        JLabel updateProfileLabel = new JLabel("Update Profile");
        updateProfileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        updateProfileLabel.setBounds(350, 0, 200, 30);
        updateProfilePanel.add(updateProfileLabel);

        // Create panels for each tab

        JPanel homePanel = new JPanel();
        homePanel.setBackground(new Color(205, 204, 240));
        homePanel.setLayout(null); // Use absolute layout for custom positioning of components

        // Add components to the home panel
        JLabel welcomeLabel = new JLabel("Welcome to Doctor Registry!");
        // Set font and alignment for the welcome label
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Add welcome label to the center of the panel
        homePanel.add(welcomeLabel, BorderLayout.CENTER);
        // Load and display an image related to healthcare
        ImageIcon healthcareImage = new ImageIcon("healthcare_image.jpg");
        JLabel imageLabel = new JLabel(healthcareImage);
        // Add image label to the bottom of the panel
        homePanel.add(imageLabel, BorderLayout.SOUTH);

        tabbedPane.addTab("Home", homePanel);

        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(205, 204, 240));
        profilePanel.setLayout(null); // Use absolute layout for custom positioning of components

        JLabel profileLabel = new JLabel("Create Profile");
        profileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        profileLabel.setBounds(350, 0, 200, 30);
        profilePanel.add(profileLabel);

        // Input fields for profile details
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 50, 100, 30);
        profilePanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(150, 50, 200, 20);
        profilePanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 100, 100, 30);
        profilePanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 100, 200, 20);
        profilePanel.add(lastNameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 150, 100, 30);
        profilePanel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(150, 150, 200, 20);
        profilePanel.add(ageField);

        JLabel SpecializationLabel = new JLabel("Specialization:");
        SpecializationLabel.setBounds(50, 200, 100, 30);
        profilePanel.add(SpecializationLabel);

        JTextField SpecializationField = new JTextField();
        SpecializationField.setBounds(150, 250, 200, 20);
        profilePanel.add(SpecializationField);

        JTextField ContactField = new JTextField();
        ContactField.setBounds(150, 200, 200, 20);
        profilePanel.add(ContactField);

        JLabel ContactLabel = new JLabel("Contact:");
        ContactLabel.setBounds(50, 250, 100, 30);
        profilePanel.add(ContactLabel);

        // Gender combo box
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 300, 100, 30);
        profilePanel.add(genderLabel);

        String[] genders = {"Male", "Female", "Others"}; // Define the options for the gender combo box
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(150, 300, 200, 30);
        profilePanel.add(genderComboBox);

        JTextField EmailField = new JTextField();
        EmailField.setBounds(150, 350, 200, 20);
        profilePanel.add(EmailField);

        JLabel NationalityLabel = new JLabel("Nationality:");
        NationalityLabel.setBounds(50, 400, 100, 30);
        profilePanel.add(NationalityLabel);

        JTextField NationalityField = new JTextField();
        NationalityField.setBounds(150, 400, 200, 20);
        profilePanel.add(NationalityField);

        JLabel EmailLabel = new JLabel("E-mail:");
        EmailLabel.setBounds(50, 350, 100, 30);
        profilePanel.add(EmailLabel);

        // Submit button
        JButton submitDetailsButton = new JButton("Submit");
        submitDetailsButton.setBounds(200, 500, 100, 30); // Adjust position and size as needed
        profilePanel.add(submitDetailsButton);

// Add ActionListener to the submit button
        submitDetailsButton.addActionListener(e -> {
            // Retrieve data from input fields
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
//            String education = educationField.getText();
            String specialization = SpecializationField.getText();
            String age = ageField.getText();
            String nationality = NationalityField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String contactNumber = ContactField.getText();
            String email = EmailField.getText();

            // Call a method to save the data in the database
            saveData(firstName, lastName, specialization, age, nationality, gender, contactNumber, email);

            // Set profileCreated flag to true
            profileStatus.profileCreated = true;

            tabbedPane.addTab("Update Profile", updateProfilePanel); // Add Update Profile tab
            tabbedPane.setSelectedIndex(0); // Switch to Home tab
        });

        // Add "Update Profile" tab only if the profile is created
        if (profileStatus.profileCreated) {
            tabbedPane.addTab("Update Profile", updateProfilePanel);
        }
// Method to save data in the database


        // Add related picture
        ImageIcon profileImage = new ImageIcon("profile_picture.png");
        JLabel profileImageLabel = new JLabel(profileImage);
        profileImageLabel.setBounds(300, 50, profileImage.getIconWidth(), profileImage.getIconHeight());
        profilePanel.add(profileImageLabel);

        tabbedPane.addTab("Create Profile", profilePanel);

        JPanel aboutPanel = new JPanel();
        aboutPanel.setBackground(new Color(205, 204, 240));
        aboutPanel.setLayout(null);

        JLabel aboutLabel = new JLabel("About");
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 18));
        aboutLabel.setBounds(350, 0, 200, 30);
        aboutPanel.add(aboutLabel);

        // Add related picture
        ImageIcon aboutImage = new ImageIcon("about_picture.png");
        JLabel aboutImageLabel = new JLabel(aboutImage);
        aboutImageLabel.setBounds(300, 50, aboutImage.getIconWidth(), aboutImage.getIconHeight());
        aboutPanel.add(aboutImageLabel);

        // Add information and facilities provided by the registry
        JLabel infoLabel = new JLabel("<html><p align='center'>Welcome to our Registry!<br>Here are some of the services we provide to doctors:</p></html>");
        infoLabel.setBounds(200, 50, 600, 50);
        aboutPanel.add(infoLabel);

        JLabel service1Label = new JLabel("<html>&#8226; Electronic Health Records (EHR)<br>&#8226; Online Appointment Scheduling<br>&#8226; Prescription Management</html>");
        service1Label.setBounds(200, 100, 600, 70);
        aboutPanel.add(service1Label);

        JLabel service2Label = new JLabel("<html>&#8226; Telemedicine Services<br>&#8226; Patient Monitoring Tools<br>&#8226; Clinical Decision Support</html>");
        service2Label.setBounds(200, 180, 600, 70);
        aboutPanel.add(service2Label);


        tabbedPane.addTab("About", aboutPanel);

        JPanel supportPanel = new JPanel();
        supportPanel.setBackground(new Color(205, 204, 240));
        supportPanel.setLayout(new BoxLayout(supportPanel, BoxLayout.X_AXIS)); // Use BoxLayout for vertical stacking

// Create a panel to hold the Contact Us and Report buttons vertically
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 0, 10)); // Use GridLayout for evenly spaced buttons
        buttonsPanel.setBackground(new Color(205, 204, 240)); // Set background color

// Create Contact Us button
        JButton contactUsButton = new JButton("Contact Us");
        contactUsButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size
        contactUsButton.setBackground(new Color(100, 100, 200)); // Set background color
        contactUsButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        contactUsButton.setPreferredSize(new Dimension(10, 10)); // Set preferred size
        contactUsButton.addActionListener(e -> {
            // Display admin contact details and email ID
            JOptionPane.showMessageDialog(null, "Admin Contact Details:\nPhone: 7249857712\nEmail: PHR2116@gmail.com");
        });
        buttonsPanel.add(contactUsButton);

        // Create a logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setBounds(650, 20, 100, 30);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform logout action
                dispose(); // Close the current DoctorHomePage frame
                new Login().setVisible(true); // Open the LoginPage frame
            }
        });
        getContentPane().add(logoutButton);
// Create Report button
        JButton reportButton = new JButton("Report");
        reportButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size
        reportButton.setBackground(new Color(102, 204, 255)); // Set background color
        reportButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        reportButton.addActionListener(e -> {
            // Logic for reporting issues
            // Create a text area for entering complaints
            JTextArea complaintTextArea = new JTextArea();
            complaintTextArea.setLineWrap(true); // Enable line wrapping
            complaintTextArea.setWrapStyleWord(true); // Wrap at word boundaries

            // Create a button for submitting the complaint
            JButton submitButton = new JButton("Submit Complaint");
            submitButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font size
            submitButton.setBackground(new Color(102, 204, 255)); // Set background color

            // Create a panel to hold the complaint text area and submit button vertically
            JPanel complaintPanel = new JPanel();
            complaintPanel.setLayout(new BorderLayout());
            complaintPanel.setBackground(new Color(205, 204, 240)); // Set background color
            complaintPanel.add(new JScrollPane(complaintTextArea), BorderLayout.CENTER); // Add text area with scroll pane
            complaintPanel.add(submitButton, BorderLayout.SOUTH); // Add submit button at the bottom

            // Show input dialog with complaint panel
            int option = JOptionPane.showOptionDialog(null, complaintPanel, "Report an Issue", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (option == JOptionPane.OK_OPTION) {
                // Retrieve complaint text from the text area
                String complaint = complaintTextArea.getText().trim();

                // Check if the complaint is not empty
                if (!complaint.isEmpty()) {
                    // Save the complaint in the database
                    saveComplaint(complaint);

                    // Show a confirmation message
                    JOptionPane.showMessageDialog(null, "Complaint submitted successfully!");
                } else {
                    // Show an error message if the complaint is empty
                    JOptionPane.showMessageDialog(null, "Please enter your complaint.");
                }
            }
        });
        buttonsPanel.add(reportButton);

// Add buttons panel to the supportPanel
        supportPanel.add(buttonsPanel, BorderLayout.CENTER);


//        JLabel supportLabel = new JLabel("Support");
//        supportLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        supportLabel.setBounds(350, 0, 200, 30);
//        supportPanel.add(supportLabel);

        // Add related picture
        ImageIcon supportImage = new ImageIcon("hospital.jpg");
        JLabel supportImageLabel = new JLabel(supportImage);
        supportImageLabel.setBounds(300, 50, supportImage.getIconWidth(), supportImage.getIconHeight());
        supportPanel.add(supportImageLabel);

        tabbedPane.addTab("Support", supportPanel);

        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setBackground(new Color(205, 204, 240));
        feedbackPanel.setLayout(null);

        JLabel feedbackLabel = new JLabel("Feedback");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 18));
        feedbackLabel.setBounds(350, 0, 200, 30);
        feedbackPanel.add(feedbackLabel);

        ImageIcon feedbackImage = new ImageIcon("hospital.jpg");
        JLabel feedbackImageLabel = new JLabel(feedbackImage);
        feedbackImageLabel.setBounds(300, 50, feedbackImage.getIconWidth(), feedbackImage.getIconHeight());
        feedbackPanel.add(feedbackImageLabel);
        JLabel feedbackTextLabel = new JLabel("Your Feedback:");
        feedbackTextLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        feedbackTextLabel.setBounds(200, 150, 150, 30);
        feedbackPanel.add(feedbackTextLabel);
        JTextArea feedbackTextArea = new JTextArea();
        feedbackTextArea.setLineWrap(true);
        feedbackTextArea.setWrapStyleWord(true);
        JScrollPane feedbackScrollPane = new JScrollPane(feedbackTextArea);
        feedbackScrollPane.setBounds(200, 200, 400, 200);
        feedbackPanel.add(feedbackScrollPane);
        JButton submitFeedbackButton = new JButton("Submit Feedback");
        submitFeedbackButton.setFont(new Font("Arial", Font.PLAIN, 16));
        submitFeedbackButton.setBounds(300, 450, 200, 30);
        submitFeedbackButton.addActionListener(e -> {
            String feedbackText = feedbackTextArea.getText().trim();
            if (!feedbackText.isEmpty()) {
                saveFeedback(feedbackText);
                JOptionPane.showMessageDialog(null, "Thank you for your feedback!");
                feedbackTextArea.setText(""); // Clear the text area after submission
            } else {
                JOptionPane.showMessageDialog(null, "Please enter your feedback.");
            }
        });
        feedbackPanel.add(submitFeedbackButton);
        tabbedPane.addTab("Feedback", feedbackPanel);

        getContentPane().add(panel);
    }


    private void centerFrame() {
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorHomePage().setVisible(true);
            }
        });
    }
}
