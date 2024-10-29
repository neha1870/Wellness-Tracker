package Wellnesstracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.mail.PasswordAuthentication;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class goalsetting extends JFrame {

	private JPanel contentPane;
	private JTextField datesearched;
	private JTextField moodentry;
	private JTextField usertext;
	private JTextArea area1;
	private JTextArea goaltask;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					goalsetting frame = new goalsetting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public goalsetting() {
		email();
		loadTable();
	}
	
	
public void loadTable() { 
		
		Connection conn2 = null;
		
		try {
			conn2 = DatabaseManager.getConnection();
			
			PreparedStatement pstmt2 = conn2.prepareStatement("SELECT * FROM reflection;");
			ResultSet rs2 = pstmt2.executeQuery();
			
	       
			// table.setModel((TableModel) DatabaseManager.buildTableModel(rs));
	        
			DefaultTableModel tableModel = DatabaseManager.buildTableModel(rs2);
	        pstmt2.close();
					
		}catch(Exception e) { 
			e.printStackTrace();
		}
	
	}
	
public void loadTable1() { 
	
	Connection conn4 = null;
	
	try {
		conn4= DatabaseManager.getConnection();
		
		PreparedStatement pstmt4 = conn4.prepareStatement("SELECT * FROM journal;");
		ResultSet rs4 = pstmt4.executeQuery();
		
       
		// table.setModel((TableModel) DatabaseManager.buildTableModel(rs));
        
		DefaultTableModel tableModel = DatabaseManager.buildTableModel(rs4);
        pstmt4.close();
				
	}catch(Exception e) { 
		e.printStackTrace();
	}

}
	
	
public void loadTable2() { 
	
	Connection conn4 = null;
	
	try {
		conn4 = DatabaseManager.getConnection();
		
		PreparedStatement pstmt4 = conn4.prepareStatement("SELECT * FROM journal;");
		ResultSet rs4 = pstmt4.executeQuery();
		
       
		// table.setModel((TableModel) DatabaseManager.buildTableModel(rs));
        
		DefaultTableModel tableModel = DatabaseManager.buildTableModel(rs4);
        pstmt4.close();
				
	}catch(Exception e) { 
		e.printStackTrace();
	}

}
	
	

	
	/**
	 * Create the frame.
	 */
	private void email() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnadd = new JButton("GENERATE DOC");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				area1.setText("************** Weekly Check-In: *****************\n");
				
				        // Clear the existing text in the JTextArea
 
				        // Get text from text fields and append it to the JTextArea
				        String goals = goaltask.getText();
				        String progress = moodentry.getText();
				        String reflection = usertext.getText();
				        String date = datesearched.getText();
				     
				        
				        try {
				            Connection conn2 = DatabaseManager.getConnection();
				            String insertQuery = "INSERT INTO reflection (goals, progress, reflection_text, date) VALUES (?, ?, ?, ?)";
				            PreparedStatement pstmt2 = conn2.prepareStatement(insertQuery);
				            pstmt2.setString(1, goals);
				            pstmt2.setString(2, progress);
				            pstmt2.setString(3, reflection);
				            pstmt2.setString(4, date);
				            
				            
				            
				           
				            pstmt2.executeUpdate();
				            pstmt2.close();
				            loadTable(); // Reload the table to show the new data
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
				        
				        // Append the data to the JTextArea
				        area1.append("Goals: " + goals + "\n");
				        area1.append("Progress: " + progress + "\n");
				        area1.append("Reflection: " + reflection + "\n");
				        area1.append("Date: " + date + "\n");
				    }
				});

		

				
		
		btnadd.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnadd.setBounds(489, 166, 214, 51);
		contentPane.add(btnadd);
		
		JButton btnupdate = new JButton("RESET");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goaltask.setText(""); // Clear goals text field
		        moodentry.setText("");   // Clear progress text field
		        usertext.setText(""); // Clear reflection text field
		        datesearched.setText(""); // Clear date text field

		        // Clear the JTextArea
		        area1.setText("");
			}
		});
		btnupdate.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 20));
		btnupdate.setBounds(489, 310, 214, 51);
		contentPane.add(btnupdate);
		
		JButton btnemail = new JButton("SEND EMAIL");
		btnemail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String goals = goaltask.getText();
			        String progress = moodentry.getText();
			        String reflection = usertext.getText();
			        String date = datesearched.getText();

			        // Create the email message
			        String emailMessage = "Goals: " + goals + "\n"
			            + "Progress: " + progress + "\n"
			            + "Reflection: " + reflection + "\n"
			            + "Date: " + date + "\n";

			        // Recipient's email ID needs to be mentioned.
			        String to = "sikhakollu.neha@gmail.com";

			        // Sender's email ID needs to be mentioned
			        final String from = "trackerwellness@gmail.com";

			        String host = "smtp.gmail.com";

			        // Get system properties
			        Properties properties = System.getProperties();

			        // Setup mail server
			        properties.put("mail.smtp.host", host);
			        properties.put("mail.smtp.port", "465");
			        properties.put("mail.smtp.ssl.enable", "true");
			        properties.put("mail.smtp.auth", "true");

			        // Get the Session object.// and pass username and password
			        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
			                return new PasswordAuthentication(from, "enbf aefc avmg erbl");
			            }
			        });

			        // Used to debug SMTP issues
			        session.setDebug(true);

			        try {
			            // Create a default MimeMessage object.
			            MimeMessage message = new MimeMessage(session);

			            // Set From: header field of the header.
			            message.setFrom(new InternetAddress(from));

			            // Set To: header field of the header.
			            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			            // Set Subject: header field
			            message.setSubject("Weekly Check-In");

			            // Set the actual message
			            message.setText(emailMessage);

			            System.out.println("Sending...");
			            // Send message
			            Transport.send(message);
			            System.out.println("Sent message successfully....");
			        } catch (MessagingException mex) {
			            mex.printStackTrace();
			        }
			    }
			});
        
		
		btnemail.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnemail.setBounds(489, 468, 214, 51);
		contentPane.add(btnemail);
		
		JLabel lblNewLabel_1 = new JLabel("Journal entry");
		lblNewLabel_1.setFont(new Font("SignPainter", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(173, 221, 118, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mood");
		lblNewLabel_1_1.setFont(new Font("SignPainter", Font.PLAIN, 21));
		lblNewLabel_1_1.setBounds(203, 335, 58, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Reflection / meeting notes");
		lblNewLabel_1_1_1.setFont(new Font("SignPainter", Font.PLAIN, 21));
		lblNewLabel_1_1_1.setBounds(144, 437, 172, 26);
		contentPane.add(lblNewLabel_1_1_1);
		

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1_1.setFont(new Font("SignPainter", Font.PLAIN, 21));
		lblNewLabel_1_1_1_1.setBounds(119, 124, 50, 26);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		
		datesearched = new JTextField();
		datesearched.setBounds(201, 120, 250, 34);
		contentPane.add(datesearched);
		datesearched.setColumns(10);
		
		moodentry = new JTextField();
		moodentry.setColumns(10);
		moodentry.setBounds(103, 373, 250, 34);
		contentPane.add(moodentry);
		
		usertext = new JTextField();
		usertext.setColumns(10);
		usertext.setBounds(103, 475, 250, 34);
		contentPane.add(usertext);
		
		area1 = new JTextArea(); // Remove the data type declaration here
		area1.setFont(new Font("Futura", Font.ITALIC, 14));
		area1.setBounds(762, 195, 488, 212);
		contentPane.add(area1);
				
			

				
				
				JButton btnlookupdate = new JButton("date searched");
				btnlookupdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String taskdate = datesearched.getText();
						
						Connection conn3 = null;
						  PreparedStatement pstmt3 = null;
					        ResultSet rs3 = null;
						
					        try {
					         
					        conn3 = DatabaseManager.getConnection();
					        String sql = "SELECT  journalentry  FROM journal WHERE date= ?";
				            pstmt3 = conn3.prepareStatement(sql);
				            pstmt3.setString(1, taskdate);

					        
				            rs3 = pstmt3.executeQuery(); 
			
				            if (rs3.next()) {
				                String journalentry = rs3.getString("journalentry");
				               goaltask.setText( goaltask.getText()+" "+ journalentry); // Populate textField_1 with the task
				            } else {
				            	goaltask.setText(" not found"); // Task not found message
				            }
				        } catch (SQLException ex) {
				            ex.printStackTrace();
				        } finally {
				            // Close the resources
				            try {
				                if (rs3 != null) {
				                    rs3.close();
				                }
				                if (pstmt3 != null) {
				                    pstmt3.close();
				                }
				                if (conn3 != null) {
				                    conn3.close();
				                }
				            } catch (SQLException ex) {
				                ex.printStackTrace();
				            }
				        }
					    
							
							Connection conn4 = null;
							  PreparedStatement pstmt4 = null;
						        ResultSet rs4 = null;
							
						        try {
						         
						        conn4 = DatabaseManager.getConnection();
						        String sql = "SELECT mood FROM journal WHERE date= ?";
					            pstmt4 = conn4.prepareStatement(sql);
					            pstmt4.setString(1, taskdate);

						        
					            rs4 = pstmt4.executeQuery(); 
				
					            if (rs4.next()) {
					                String mood1= rs4.getString("mood");
					               moodentry.setText(mood1); // Populate textField_1 with the task
					            } else {
					            	moodentry.setText("mood for today  not found"); // Task not found message
					            }
					        } catch (SQLException ex) {
					            ex.printStackTrace();
					        } finally {
					            // Close the resources
					            try {
					                if (rs4 != null) {
					                    rs4.close();
					                }
					                if (pstmt4 != null) {
					                    pstmt4.close();
					                }
					                if (conn4 != null) {
					                    conn4.close();
					                }
					            } catch (SQLException ex) {
					                ex.printStackTrace();
					            }
					        }       
					        
					        
					        
					        
					        
				    }
				});
					              
				btnlookupdate.setBounds(157, 166, 117, 35);
				contentPane.add(btnlookupdate);
				
				
				
				JButton btnNewButton_2 = new JButton("Task List");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dashboard info = new dashboard();
						dashboard.main(null);
					}
				});
				btnNewButton_2.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
				btnNewButton_2.setBounds(59, 17, 180, 57);
				contentPane.add(btnNewButton_2);
				
				JButton btnNewButton_2_1 = new JButton("Habit Tracker");
				btnNewButton_2_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						habittracker info = new habittracker();
						habittracker.main(null);
					}
				});
				btnNewButton_2_1.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
				btnNewButton_2_1.setBounds(420, 17, 180, 57);
				contentPane.add(btnNewButton_2_1);
				
				JButton btnNewButton_2_2 = new JButton("Goal Setting");
				btnNewButton_2_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						diaryentry info = new diaryentry();
						diaryentry.main(null);
					}
				});
				btnNewButton_2_2.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
				btnNewButton_2_2.setBounds(760, 17, 180, 57);
				contentPane.add(btnNewButton_2_2);
				
				JButton btnNewButton_2_3 = new JButton("Email/Reflection");
				btnNewButton_2_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						goalsetting info = new goalsetting();
						goalsetting.main(null);
					}
				});
				btnNewButton_2_3.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
				btnNewButton_2_3.setBounds(1082, 17, 180, 57);
				contentPane.add(btnNewButton_2_3);
				
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(71, 259, 312, 64);
				contentPane.add(scrollPane);
			
				
				goaltask = new JTextArea();
				scrollPane.setRowHeaderView(goaltask);
				
				
				JLabel tasks = new JLabel("background");
				tasks.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/reflection.png"));
				tasks.setBounds(0, 6, 1280, 680);
				contentPane.add(tasks);
	}
}

