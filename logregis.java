package Wellnesstracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class logregis extends JFrame {

    private JPanel contentPane;
    private JTextField userfield;
    private JPasswordField passwordField;
    private JButton btnexit;
    private JButton btnreset;
    private JButton btnenter;
    private JLabel generate;
    private JTextField entercode;
    private Random random;
    private JButton btncode;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create an instance of logregis frame and make it visible
                    logregis frame = new logregis();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public logregis() {
        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Username field
        userfield = new JTextField();
        userfield.setBounds(550, 153, 291, 44);
        contentPane.add(userfield);
        userfield.setColumns(10);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(550, 296, 291, 44);
        contentPane.add(passwordField);

        // Exit button
        btnexit = new JButton("Exit");
        btnexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the welcomepage when Exit button is clicked
                welcomepage info = new welcomepage();
                welcomepage.main(null);
            }
        });
        btnexit.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
        btnexit.setBounds(968, 111, 258, 63);
        contentPane.add(btnexit);

        // Reset button
        btnreset = new JButton("Reset");
        btnreset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Reset text fields when Reset button is clicked
                passwordField.setText(null);
                userfield.setText(null);
                entercode.setText(null);
            }
        });
        btnreset.setFont(new Font("Malayalam Sangam MN", Font.BOLD, 21));
        btnreset.setBounds(968, 274, 258, 63);
        contentPane.add(btnreset);

        // Login button
        btnenter = new JButton("Login");
        btnenter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get entered details
                String password = passwordField.getText();
                String username = userfield.getText();
                String enteredCode = entercode.getText(); // Get the entered code
                String generatedText = generate.getText();

                System.out.println("Entered Code: " + enteredCode);
                System.out.println("Generated Code: " + generatedText);

                if (generatedText.equals("4 - Digit Code")) {
                    // Display an error message if code is not generated
                    JOptionPane.showMessageDialog(null,
                            "Invalid Login Details Login error  + Please generate a code first.", "Login error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (password.contains("king") && username.contains("one") && enteredCode.equals(generatedText)) {
                    // If login is successful, reset text fields and open the dashboard
                    passwordField.setText(null);
                    userfield.setText(null);
                    entercode.setText(null);
                    dashboard info = new dashboard();
                    dashboard.main(null);
                } else {
                    // Display an error message for invalid login details
                    JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnenter.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
        btnenter.setBounds(968, 447, 258, 63);
        contentPane.add(btnenter);

        // Enter code field
        entercode = new JTextField();
        entercode.setColumns(10);
        entercode.setBounds(550, 536, 291, 34);
        contentPane.add(entercode);

        // JLabel that changes to a 4-digit code
        generate = new JLabel("4 - Digit Code");
        generate.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
        generate.setBounds(374, 536, 140, 34);
        contentPane.add(generate);

        // Button to generate a random code
        btncode = new JButton("Get Code");
        btncode.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
        btncode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Generate a random number between 1000 and 9999
                int randomNum = getRandom1().nextInt(9000) + 1000;
                // Set the label to display the random number
                generate.setText(Integer.toString(randomNum));
            }
        });
        btncode.setBounds(589, 438, 209, 44);
        contentPane.add(btncode);

        // Background label
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
        lblNewLabel.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/loginpage background.png"));
        lblNewLabel.setBounds(0, 0, 1280, 692);
        contentPane.add(lblNewLabel);
    }

    // Method to get a new Random instance
    public Random getRandom1() {
        return new Random();
    }

    // Method to set the Random instance
    public void setRandom(Random random) {
        this.random = random;
    }
}
