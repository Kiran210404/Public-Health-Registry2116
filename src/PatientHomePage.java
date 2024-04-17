import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;

public class PatientHomePage extends JFrame {

    public PatientHomePage() {
        initComponents();
        centerFrame();
    }

    private void saveData(String firstName, String lastName, String age, String gender, String contactNumber, String email) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            // Prepare the SQL statement
            String query = "INSERT INTO PatientDetails (FirstName, LastName, Age, Gender, ContactNumber, Email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set parameters in the prepared statement
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, age);
            statement.setString(4, gender);
            statement.setString(5, contactNumber);
            statement.setString(6, email);

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

    private void saveMedicationDetails(String medicationName, String dosage, String frequency, Date startDate, Date endDate) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            // Prepare the SQL statement
            String query = "INSERT INTO MedicationHistory (MedicationName, Dosage, Frequency, StartDate, EndDate) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set parameters in the prepared statement
            statement.setString(1, medicationName);
            statement.setString(2, dosage);
            statement.setString(3, frequency);
            statement.setDate(4, new java.sql.Date(startDate.getTime()));
            statement.setDate(5, new java.sql.Date(endDate.getTime()));

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            // Check if data was successfully inserted
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Medication details saved successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save medication details.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void retrieveMedicationHistory(JTable medicationHistoryTable) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            // Prepare the SQL statement
            String query = "SELECT MedicationName, Dosage, Frequency, StartDate, EndDate FROM MedicationHistory";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Clear previous table data
            DefaultTableModel model = (DefaultTableModel) medicationHistoryTable.getModel();
            model.setRowCount(0);

            // Populate the table with medication history data
            while (resultSet.next()) {
                String medicationName = resultSet.getString("MedicationName");
                String dosage = resultSet.getString("Dosage");
                String frequency = resultSet.getString("Frequency");
                Date startDate = resultSet.getDate("StartDate");
                Date endDate = resultSet.getDate("EndDate");

                model.addRow(new Object[]{medicationName, dosage, frequency, startDate, endDate});
            }

            // Refresh the table to reflect changes
            medicationHistoryTable.repaint();

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Patient Home Page");
        setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(92, 90, 93, 171)); // Set background color

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(204, 50, 100)); // Set background color for tabs
        tabbedPane.setForeground(Color.BLACK);
        panel.add(tabbedPane, BorderLayout.CENTER);

        // Create panels for each tab

        JPanel homePanel = new JPanel();
        homePanel.setBackground(new Color(205, 204, 255));
        homePanel.setLayout(null); // Use absolute layout for custom positioning of components

        JLabel homeLabel = new JLabel("Welcome, Patient!");
        homeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        homeLabel.setBounds(300, 0, 200, 30);
        homePanel.add(homeLabel);

// Add related picture
        ImageIcon homeImage = new ImageIcon("patient_home_picture.png");
        JLabel homeImageLabel = new JLabel(homeImage);
        homeImageLabel.setBounds(350, 50, homeImage.getIconWidth(), homeImage.getIconHeight());
        homePanel.add(homeImageLabel);

// Add information and features provided by the registry to patients
        JLabel infoLabel = new JLabel("<html><p align='center'>Welcome to our Patient Portal!<br>Here are some of the features we offer:</p></html>");
        infoLabel.setBounds(100, 150, 600, 50);
        homePanel.add(infoLabel);

        JLabel feature1Label = new JLabel("<html>&#8226; View and Manage Medical Records<br>&#8226; Schedule Appointments Online<br>&#8226; Receive Prescription Refills</html>");
        feature1Label.setBounds(100, 220, 600, 70);
        homePanel.add(feature1Label);

        JLabel feature2Label = new JLabel("<html>&#8226; Communicate with Healthcare Providers<br>&#8226; Access Telemedicine Services<br>&#8226; Track Health Metrics</html>");
        feature2Label.setBounds(100, 300, 600, 70);
        homePanel.add(feature2Label);

        tabbedPane.addTab("Home", homePanel);

        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(205, 204, 255));
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

        // Gender combo box
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 200, 100, 30);
        profilePanel.add(genderLabel);

        String[] genders = {"Male", "Female", "Others"}; // Define the options for the gender combo box
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(150, 200, 200, 30);
        profilePanel.add(genderComboBox);

        JTextField contactField = new JTextField();
        contactField.setBounds(150, 250, 200, 20);
        profilePanel.add(contactField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(50, 250, 100, 30);
        profilePanel.add(contactLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 300, 200, 20);
        profilePanel.add(emailField);

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setBounds(50, 300, 100, 30);
        profilePanel.add(emailLabel);

        // Date of Birth label
        JLabel DOBLabel = new JLabel("DOB:");
        DOBLabel.setBounds(50, 350, 100, 30);
        profilePanel.add(DOBLabel);

        // Date of Birth field using JDatePicker
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl DOBField = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        DOBField.setBounds(150, 350, 200, 30);
        profilePanel.add(DOBField);

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

        // Submit button
        JButton submitDetailsButton = new JButton("Submit");
        submitDetailsButton.setBounds(200, 450, 100, 30); // Adjust position and size as needed
        profilePanel.add(submitDetailsButton);

        // Add ActionListener to the submit button
        submitDetailsButton.addActionListener(e -> {
            // Retrieve data from input fields
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String age = ageField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String contactNumber = contactField.getText();
            String email = emailField.getText();

            // Call a method to save the data in the database
            saveData(firstName, lastName, age, gender, contactNumber, email);
        });

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(205, 204, 255));
        searchPanel.setLayout(null); // Use absolute layout for custom positioning of components

        JLabel searchLabel = new JLabel("Search Doctors");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 18));
        searchLabel.setBounds(320, 0, 200, 30);
        searchPanel.add(searchLabel);

        JLabel searchByNameLabel = new JLabel("Search by Name:");
        searchByNameLabel.setBounds(50, 50, 120, 30);
        searchPanel.add(searchByNameLabel);

        JTextField searchByNameField = new JTextField();
        searchByNameField.setBounds(180, 50, 200, 20);
        searchPanel.add(searchByNameField);

        JLabel searchBySpecializationLabel = new JLabel("Search by Specialization:");
        searchBySpecializationLabel.setBounds(50, 100, 150, 30);
        searchPanel.add(searchBySpecializationLabel);

        JTextField searchBySpecializationField = new JTextField();
        searchBySpecializationField.setBounds(200, 100, 200, 20);
        searchPanel.add(searchBySpecializationField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 150, 100, 30);
        searchPanel.add(searchButton);

        // Table to display search results
        JTable searchResultTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(searchResultTable);
        scrollPane.setBounds(50, 200, 700, 200);
        searchPanel.add(scrollPane);

        searchButton.addActionListener(e -> {
            String name = searchByNameField.getText().trim();
            String specialization = searchBySpecializationField.getText().trim();
            searchDoctors(name, specialization, searchResultTable);
        });

        tabbedPane.addTab("Search Doctors", searchPanel);

        // Medication History Panel
        JPanel medicationPanel = new JPanel();
        medicationPanel.setBackground(new Color(205, 204, 255));
        medicationPanel.setLayout(null); // Use absolute layout for custom positioning of components

        JLabel medicationLabel = new JLabel("Medication History");
        medicationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        medicationLabel.setBounds(300, 0, 200, 30);
        medicationPanel.add(medicationLabel);

        // Add input fields for medication details
        // Add a button to save medication history
        // Display medication history in a table format
        // Add input fields for medication details
        JLabel medicationNameLabel = new JLabel("Medication Name:");
        medicationNameLabel.setBounds(50, 50, 150, 30);
        medicationPanel.add(medicationNameLabel);

        JTextField medicationNameField = new JTextField();
        medicationNameField.setBounds(200, 50, 200, 20);
        medicationPanel.add(medicationNameField);

        JLabel dosageLabel = new JLabel("Dosage:");
        dosageLabel.setBounds(50, 100, 100, 30);
        medicationPanel.add(dosageLabel);

        JTextField dosageField = new JTextField();
        dosageField.setBounds(200, 100, 200, 20);
        medicationPanel.add(dosageField);

        JLabel frequencyLabel = new JLabel("Frequency:");
        frequencyLabel.setBounds(50, 150, 100, 30);
        medicationPanel.add(frequencyLabel);

        JTextField frequencyField = new JTextField();
        frequencyField.setBounds(200, 150, 200, 20);
        medicationPanel.add(frequencyField);

        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(50, 200, 100, 30);
        medicationPanel.add(startDateLabel);

        UtilDateModel startDateModel = new UtilDateModel();
        Properties startDateProperties = new Properties();
        startDateProperties.put("text.today", "Today");
        startDateProperties.put("text.month", "Month");
        startDateProperties.put("text.year", "Year");
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startDateModel, startDateProperties);
        JDatePickerImpl startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        startDatePicker.setBounds(200, 200, 200, 30);
        medicationPanel.add(startDatePicker);

        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(50, 250, 100, 30);
        medicationPanel.add(endDateLabel);

        UtilDateModel endDateModel = new UtilDateModel();
        Properties endDateProperties = new Properties();
        endDateProperties.put("text.today", "Today");
        endDateProperties.put("text.month", "Month");
        endDateProperties.put("text.year", "Year");
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateModel, endDateProperties);
        JDatePickerImpl endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
        endDatePicker.setBounds(200, 250, 200, 30);
        medicationPanel.add(endDatePicker);

// Button to add medication history details to the database
        JButton addMedicationButton = new JButton("Add Medication");
        addMedicationButton.setBounds(150, 300, 150, 30);
        medicationPanel.add(addMedicationButton);

// Button to retrieve medication history from the database
        JButton retrieveMedicationButton = new JButton("Retrieve Medication History");
        retrieveMedicationButton.setBounds(350, 300, 200, 30);
        medicationPanel.add(retrieveMedicationButton);

// Table to display medication history
        JTable medicationHistoryTable = new JTable();
        JScrollPane medicationScrollPane = new JScrollPane(medicationHistoryTable);
        medicationScrollPane.setBounds(50, 350, 700, 200);
        medicationPanel.add(medicationScrollPane);

// ActionListener for adding medication details to the database
        addMedicationButton.addActionListener(e -> {
            String medicationName = medicationNameField.getText();
            String dosage = dosageField.getText();
            String frequency = frequencyField.getText();
            Date startDate = (Date) startDatePicker.getModel().getValue();
            Date endDate = (Date) endDatePicker.getModel().getValue();

            // Call method to save medication details in the database
            saveMedicationDetails(medicationName, dosage, frequency, startDate, endDate);
        });

// ActionListener for retrieving medication history from the database
        retrieveMedicationButton.addActionListener(e -> {
            // Call method to retrieve medication history from the database and display in the table
            retrieveMedicationHistory(medicationHistoryTable);
        });


        tabbedPane.addTab("Medication History", medicationPanel);

        // Add related picture
        ImageIcon profileImage = new ImageIcon("hospital.jpg");
        JLabel profileImageLabel = new JLabel(profileImage);
        profileImageLabel.setBounds(300, 50, profileImage.getIconWidth(), profileImage.getIconHeight());
        profilePanel.add(profileImageLabel);

        tabbedPane.addTab("Profile", profilePanel);

//        JPanel appointmentsPanel = new JPanel();
//        appointmentsPanel.setBackground(new Color(205, 204, 255));
//        appointmentsPanel.setLayout(null); // Use absolute layout for custom positioning of components
//
//        JLabel appointmentsLabel = new JLabel("Appointments");
//        appointmentsLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        appointmentsLabel.setBounds(320, 0, 200, 30);
//        appointmentsPanel.add(appointmentsLabel);
//
//        // Add related picture
//        ImageIcon appointmentsImage = new ImageIcon("appointments_picture.png");
//        JLabel appointmentsImageLabel = new JLabel(appointmentsImage);
//        appointmentsImageLabel.setBounds(300, 50, appointmentsImage.getIconWidth(), appointmentsImage.getIconHeight());
//        appointmentsPanel.add(appointmentsImageLabel);
//
//        tabbedPane.addTab("Appointments", appointmentsPanel);

        JPanel supportPanel = new JPanel();
        supportPanel.setBackground(new Color(205, 204, 255));
        supportPanel.setLayout(null); // Use absolute layout for custom positioning of components

        JLabel supportLabel = new JLabel("Support");
        supportLabel.setFont(new Font("Arial", Font.BOLD, 18));
        supportLabel.setBounds(350, 0, 200, 30);
        supportPanel.add(supportLabel);

        // Add related picture
        ImageIcon supportImage = new ImageIcon("support_picture.png");
        JLabel supportImageLabel = new JLabel(supportImage);
        supportImageLabel.setBounds(300, 50, supportImage.getIconWidth(), supportImage.getIconHeight());
        supportPanel.add(supportImageLabel);

        tabbedPane.addTab("Support", supportPanel);

        getContentPane().add(panel);


    }

    private void searchDoctors(String firstname, String specialization, JTable DoctorDetails) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            String query = "SELECT firstname, ContactNumber, Email, Specialization FROM DoctorDetails WHERE firstname LIKE ? OR Specialization LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + firstname + "%");
            statement.setString(2, "%" + specialization + "%");

            ResultSet resultSet = statement.executeQuery();

            // Debug: Print out retrieved data
            while (resultSet.next()) {
                System.out.println("Doctor Name: " + resultSet.getString("firstname"));
                System.out.println("Contact Number: " + resultSet.getString("ContactNumber"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println("Specialization: " + resultSet.getString("Specialization"));
            }

            // Clear previous table data
            DefaultTableModel model = (DefaultTableModel) DoctorDetails.getModel();
            model.setRowCount(0);

            // Populate the table with search results
            while (resultSet.next()) {
                String doctorName = resultSet.getString("firstname"); // Corrected column name
                String contactNumber = resultSet.getString("ContactNumber");
                String email = resultSet.getString("Email");
                String doctorSpecialization = resultSet.getString("Specialization");

                model.addRow(new Object[]{doctorName, contactNumber, email, doctorSpecialization});
            }

            // Refresh the table to reflect changes
            DoctorDetails.repaint();

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print stack trace for debugging
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


    private void centerFrame() {
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientHomePage().setVisible(true);
            }
        });
    }
}
