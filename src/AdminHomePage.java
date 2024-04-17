import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class AdminHomePage extends JFrame {

    private JList<String> doctorsJList; // Declare as class-level variable
    private JList<String> patientsJList; // Declare as class-level variable

    public AdminHomePage() {
        initComponents();
        centerFrame();
    }

    private void saveData(String firstName, String lastName, String age, String gender, String contactNumber, String email) {
        // Method to save data in the database
    }

    // Method to fetch all registered patients from the database
    private ArrayList<String> getAllPatients() {
        ArrayList<String> patients = new ArrayList<>();
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute query to retrieve all patients
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PatientDetails");

            // Iterate over the result set and add patient names to the list
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                patients.add(firstName + " " + lastName);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return patients;
    }

    // Method to fetch all registered doctors from the database
    private ArrayList<String> getAllDoctors() {
        ArrayList<String> doctors = new ArrayList<>();
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@LAPTOP-GTQENUUF:1521:XE", "SYSTEM", "Jk2122");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute query to retrieve all doctors
            ResultSet resultSet = statement.executeQuery("SELECT * FROM DoctorDetails");

            // Iterate over the result set and add doctor names to the list
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                doctors.add(firstName + " " + lastName);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return doctors;
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Home Page");
        setSize(800, 600);

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

        tabbedPane.addTab("Home", homePanel);

        JPanel manageDoctorsPanel = new JPanel();
        manageDoctorsPanel.setBackground(new Color(205, 204, 255));
        manageDoctorsPanel.setLayout(null); // Use absolute layout for custom positioning of components

        JLabel manageDoctorsLabel = new JLabel("Manage Doctors");
        manageDoctorsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        manageDoctorsLabel.setBounds(320, 0, 200, 30);
        manageDoctorsPanel.add(manageDoctorsLabel);

        // Add related picture
        ImageIcon manageDoctorsImage = new ImageIcon("manage_doctors_picture.png");
        JLabel manageDoctorsImageLabel = new JLabel(manageDoctorsImage);
        manageDoctorsImageLabel.setBounds(300, 50, manageDoctorsImage.getIconWidth(), manageDoctorsImage.getIconHeight());
        manageDoctorsPanel.add(manageDoctorsImageLabel);

        // Add button to request data retrieval
        JButton retrieveDoctorsButton = new JButton("Retrieve Doctors");
        retrieveDoctorsButton.setBounds(50, 50, 150, 30);
        retrieveDoctorsButton.addActionListener(e -> {
            // Perform data retrieval again to refresh the list
            ArrayList<String> refreshedDoctorsList = getAllDoctors();
            doctorsJList.setListData(refreshedDoctorsList.toArray(new String[0]));
        });
        manageDoctorsPanel.add(retrieveDoctorsButton);
        tabbedPane.addTab("Manage Doctors", manageDoctorsPanel);

        // Retrieve all doctors and display them in a list
        ArrayList<String> doctorsList = getAllDoctors();
        JList<String> doctorsJList = new JList<>(doctorsList.toArray(new String[0]));
        JScrollPane doctorsScrollPane = new JScrollPane(doctorsJList);
        manageDoctorsPanel.add(doctorsScrollPane, BorderLayout.CENTER);

        JPanel managePatientsPanel = new JPanel();
        managePatientsPanel.setBackground(new Color(205, 204, 255));
        managePatientsPanel.setLayout(null); // Use absolute layout for custom positioning of components

        JLabel managePatientsLabel = new JLabel("Manage Patients");
        managePatientsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        managePatientsLabel.setBounds(320, 0, 200, 30);
        managePatientsPanel.add(managePatientsLabel);

        // Add related picture
        ImageIcon managePatientsImage = new ImageIcon("manage_patients_picture.png");
        JLabel managePatientsImageLabel = new JLabel(managePatientsImage);
        managePatientsImageLabel.setBounds(300, 50, managePatientsImage.getIconWidth(), managePatientsImage.getIconHeight());
        managePatientsPanel.add(managePatientsImageLabel);

        // Add button to request data retrieval
        JButton retrievePatientsButton = new JButton("Retrieve Patients");
        retrievePatientsButton.setBounds(50, 50, 150, 30);
        retrievePatientsButton.addActionListener(e -> {
            // Perform data retrieval again to refresh the list
            ArrayList<String> refreshedPatientsList = getAllPatients();
            patientsJList.setListData(refreshedPatientsList.toArray(new String[0]));
        });
        managePatientsPanel.add(retrievePatientsButton);

        tabbedPane.addTab("Manage Patients", managePatientsPanel);

        // Retrieve all patients and display them in a list
        ArrayList<String> patientsList = getAllPatients();
        JList<String> patientsJList = new JList<>(patientsList.toArray(new String[0]));
        JScrollPane patientsScrollPane = new JScrollPane(patientsJList);
        managePatientsPanel.add(patientsScrollPane, BorderLayout.CENTER);

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

    private void centerFrame() {
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHomePage().setVisible(true);
            }
        });
    }
}
