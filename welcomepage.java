package Wellnesstracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class welcomepage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create an instance of the welcomepage frame and make it visible
					welcomepage frame = new welcomepage();
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
	public welcomepage() {
		// Set up the basic properties of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Create a button for logging in
		JButton btnwelcome = new JButton("Log In");
		btnwelcome.setForeground(new Color(0, 0, 0));
		btnwelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the login/registration page when the button is clicked
				logregis info = new logregis();
				logregis.main(null);
			}
		});
		btnwelcome.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnwelcome.setBounds(505, 574, 281, 75);
		contentPane.add(btnwelcome);
		
		// Create a label for the welcome message
		JLabel lblNewLabel_1 = new JLabel("Welcome To Your  Wellness Tracker");
		lblNewLabel_1.setFont(new Font("SignPainter", Font.BOLD, 45));
		lblNewLabel_1.setBounds(389, 53, 543, 83);
		contentPane.add(lblNewLabel_1);
		
		// Create a label for the background image
		JLabel backgroundimage = new JLabel("New label");
		backgroundimage.setBackground(new Color(255, 255, 240));
		backgroundimage.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/newwelcome.png"));
		backgroundimage.setBounds(0, 0, 1280, 692);
		contentPane.add(backgroundimage);
	}
}
