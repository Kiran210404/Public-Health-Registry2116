import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SplashPage extends JFrame {
    private ImageIcon logoIcon; // Declare logoIcon as a member variable

    public SplashPage() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setUndecorated(true); // Remove window decorations such as title bar

        // Create a panel for the splash content
        JPanel splashPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set background color
                setBackground(new Color(200, 250, 251)); // Light blue color

                // Set font and color for the tagline
                g.setFont(new Font("Italic", Font.BOLD, 18));
                g.setColor(new Color(255, 215, 0)); // White color
                // Draw the tagline on the panel
                g.drawString("Your Health, Our Responsibility", 150, 450);

                // Load the logo image
                try {
                    // Load the logo image
                    Image logoImage = ImageIO.read(getClass().getResource("doc2.png"));
                    // Draw the logo on the panel
                    g.drawImage(logoImage, 150, 150, 300, 300, null);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle the error if the image cannot be loaded
                }
            }
        };
        add(splashPanel);

        // Center the splash page on the screen
        setLocationRelativeTo(null);

        // Set a timer to close the splash page after 3 seconds
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the login page after the timer expires
                dispose(); // Close the splash page
                new Login().setVisible(true); // Open the login page
            }
        });
        timer.setRepeats(false); // Only run the timer once
        timer.start();
    }

    public static void main(String[] args) {
        // Create and display the splash page
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashPage().setVisible(true);
            }
        });
    }
}
