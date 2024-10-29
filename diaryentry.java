package Wellnesstracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class diaryentry extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textareajournal;
	private JTextField entrydate;
	private JTextField moodtoday;
	private File selectedImageFile;

	private List<String> imagePaths = new ArrayList<>();
    private JButton btnNewButton_1;
    private JButton btnNewButton_1_1;
    private JButton btnNewButton_1_2;
    private JButton btnNewButton_1_3;
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					diaryentry frame = new diaryentry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	   public diaryentry() {
	        diary();
	        loadTable();
	     

	        // Add a WindowListener to handle window closing
	        
	             
	     // Set up the table selection listener to handle events when a table row is selected
	        setupTableSelectionListener();

	        try {
	            // Read all lines from the "imagePaths.txt" file and store them in a list
	            List<String> lines = Files.readAllLines(new File("imagePaths.txt").toPath());
	            
	            // Add all the read image paths to the existing list of image paths
	            imagePaths.addAll(lines);
	            
	            // Check if the list of image paths is not empty
	            if (!imagePaths.isEmpty()) {
	                // Get the last image path from the list
	                String lastImagePath = imagePaths.get(imagePaths.size() - 1);
	                
	                // Create an ImageIcon using the last image path
	                ImageIcon imageIcon = new ImageIcon(lastImagePath);
	                
	                // Set the ImageIcon to be the icon for button btnNewButton_1
	                btnNewButton_1.setIcon(imageIcon);
	                
	                // Set the ImageIcon to be the icon for button btnNewButton_1_1
	                btnNewButton_1_1.setIcon(imageIcon);
	                
	                // Set the ImageIcon to be the icon for button btnNewButton_1_2
	                btnNewButton_1_2.setIcon(imageIcon);
	                
	                // Set the ImageIcon to be the icon for button btnNewButton_1_3
	                btnNewButton_1_3.setIcon(imageIcon);
	            }
	        } catch (IOException ex) {
	            // Handle IOException by printing the stack trace
	            ex.printStackTrace();
	        }

	        }
	        
	    

	
	private void setupTableSelectionListener() {
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent e) {
	            if (!e.getValueIsAdjusting()) {
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow != -1) {
	                    String selectedDate = (String) table.getValueAt(selectedRow, 0);

	                    // Query the database to get all the data for the selected date
	                    try {
	                        Connection conn6 = DatabaseManager.getConnection();
	                        String selectQuery = "SELECT * FROM journal WHERE date = ?";
	                        PreparedStatement pstmt6 = conn6.prepareStatement(selectQuery);
	                        pstmt6.setString(1, selectedDate);
	                        ResultSet rs6 = pstmt6.executeQuery();

	                        if (rs6.next()) {
	                            // Populate the text fields with the data
	                            textareajournal.setText(rs6.getString("journalentry"));
	                            moodtoday.setText(String.valueOf(rs6.getInt("mood")));
	                            entrydate.setText(rs6.getString("date"));
	                            
	                        }

	                        pstmt6.close();
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                }
	            }
	        }
	    });
	}

	                      
	
public void loadTable() { 
		
		Connection conn6 = null;
		
		try {
	        // Get a connection to the database
	        conn6 = DatabaseManager.getConnection();

	        // Prepare a SQL statement to select all data from the 'journal' table
	        PreparedStatement pstmt6 = conn6.prepareStatement("SELECT * FROM journal;");
	        // Execute the SQL query and get the result set
	        ResultSet rs6 = pstmt6.executeQuery();

	        // Create a new table model for the JTable
	        DefaultTableModel tableModel = new DefaultTableModel();
	        // Add a column named "date" to the table model
	        tableModel.addColumn("date");

	        // Loop through the result set and add each date to the table model
	        while (rs6.next()) {
	            String date = rs6.getString("date");
	            tableModel.addRow(new Object[] { date });
	        }

	        // Set the JTable's model to the newly created table model
	        table.setModel(tableModel);
	       

	        
	        pstmt6.close();
					
		}catch(Exception e) { 
			e.printStackTrace();
		}
	
	}



//Add a ListSelectionListener to the table


	/**
	 * Create the frame.
	 */
	private void diary() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		  
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setupTableSelectionListener();
				
				
				
			}
		});
		scrollPane1.setBounds(1103, 155, 154, 493);
		contentPane.add(scrollPane1);
		
		table = new JTable();
		scrollPane1.setViewportView(table);
	
		
		JButton btnadd = new JButton("ADD");
		btnadd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String journalentry = textareajournal.getText();
		        String mood = moodtoday.getText();
		        String date = entrydate.getText();

		        // Validate that mood is a number between 0 and 10
		        try {
		            int moodValue = Integer.parseInt(mood);
		            if (moodValue < 0 || moodValue > 10) {
		                JOptionPane.showMessageDialog(null, "Please enter a mood value between 0 and 10.", "Invalid Mood", JOptionPane.WARNING_MESSAGE);
		                return; // Exit the method without proceeding to database insertion
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Please enter a valid numeric mood value.", "Invalid Mood", JOptionPane.WARNING_MESSAGE);
		            return; // Exit the method without proceeding to database insertion
		        }

		        // Validate the date format (YYYY-MM-DD)
		        if (!isValidDateFormat(date)) {
		            JOptionPane.showMessageDialog(null, "Please enter the date in the format YYYY-MM-DD.", "Invalid Date Format", JOptionPane.WARNING_MESSAGE);
		            return; // Exit the method without proceeding to database insertion
		        }

		        try {
		            Connection conn6 = DatabaseManager.getConnection();
		            String insertQuery = "INSERT INTO journal (journalentry, mood, date) VALUES (?, ?, ?)";
		            PreparedStatement pstmt6 = conn6.prepareStatement(insertQuery);
		            pstmt6.setString(1, journalentry);
		            pstmt6.setString(2, mood);
		            pstmt6.setString(3, date);
		            pstmt6.executeUpdate();
		            pstmt6.close();
		            loadTable(); // Reload the table to show the new data
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }

		    // Helper method to check if the date has a valid format (YYYY-MM-DD)
		    private boolean isValidDateFormat(String date) {
		        // Regular expression to match the pattern YYYY-MM-DD
		        String dateFormatPattern = "\\d{4}-\\d{2}-\\d{2}";
		        return date.matches(dateFormatPattern);
		    }
		});

		btnadd.setBounds(846, 604, 131, 54);
		contentPane.add(btnadd);
		
		
		
		JButton btndelete = new JButton("DELETE");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int selectedRow = table.getSelectedRow();
				 if (selectedRow != -1) {
					 String journal_id = table.getValueAt(selectedRow, 0).toString();
					 try {
		                    Connection conn6 = DatabaseManager.getConnection();
		                    String deleteQuery = "DELETE FROM journal WHERE journal_id = ?";
		                    PreparedStatement pstmt6 = conn6.prepareStatement(deleteQuery);
		                    pstmt6.setString(1, journal_id);
		                    pstmt6.executeUpdate();
		                    pstmt6.close();
		                    loadTable(); // Reload the table to reflect the changes
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                }
		            }
			}
		});
		btndelete.setBounds(757, 513, 138, 54);
		contentPane.add(btndelete);
		
		JButton btnupdate = new JButton("UPDATE");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int selectedRow = table.getSelectedRow();
			        if (selectedRow != -1) {
			            String journalid = table.getValueAt(selectedRow, 0).toString();
			            String newjournalentry = textareajournal.getText();
			            String newmood = moodtoday.getText();
			            String newdate = entrydate.getText();

			            try {
			                Connection conn6 = DatabaseManager.getConnection();
			                String updateQuery = "UPDATE journal SET journalentry = ?, mood = ?, date = ? WHERE journal_id = ?";
			                PreparedStatement pstmt6 = conn6.prepareStatement(updateQuery);

							pstmt6.setString(1, newjournalentry);
			                pstmt6.setString(2, newmood);
			                pstmt6.setString(3, newdate);
			                pstmt6.setString(4, journalid);
			                pstmt6.executeUpdate();
			                pstmt6.close();
			                loadTable(); // Reload the table to reflect the changes
			            } catch (Exception ex) {
			                ex.printStackTrace();
			            }
			}
			}
		});
		btnupdate.setBounds(931, 513, 131, 54);
		contentPane.add(btnupdate);
		
		JLabel lblNewLabel_1 = new JLabel("What Went well today ?");
		lblNewLabel_1.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		lblNewLabel_1.setBounds(800, 149, 221, 30);
		contentPane.add(lblNewLabel_1);
		
		textareajournal = new JTextField();
		textareajournal.setBounds(757, 185, 305, 174);
		contentPane.add(textareajournal);
		textareajournal.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mood on a scale of 1 - 10");
		lblNewLabel_1_1.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		lblNewLabel_1_1.setBounds(757, 429, 229, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date of entry ");
		lblNewLabel_1_2.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		lblNewLabel_1_2.setBounds(757, 371, 131, 30);
		contentPane.add(lblNewLabel_1_2);
		
		
		JButton btnNewButton_2 = new JButton("Task List");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dashboard info = new dashboard();
				dashboard.main(null);
			}
		});
		btnNewButton_2.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnNewButton_2.setBounds(59, 16, 180, 57);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Habit Tracker");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habittracker info = new habittracker();
				habittracker.main(null);
			}
		});
		btnNewButton_2_1.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnNewButton_2_1.setBounds(418, 16, 180, 57);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Goal Setting");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diaryentry info = new diaryentry();
				diaryentry.main(null);
			}
		});
		btnNewButton_2_2.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnNewButton_2_2.setBounds(761, 16, 180, 57);
		contentPane.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_3 = new JButton("Email/Reflection");
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goalsetting info = new goalsetting();
				goalsetting.main(null);
			}
		});
		btnNewButton_2_3.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnNewButton_2_3.setBounds(1077, 16, 180, 57);
		contentPane.add(btnNewButton_2_3);
		
		
		entrydate = new JTextField();
		entrydate.setBounds(923, 374, 138, 30);
		contentPane.add(entrydate);
		entrydate.setColumns(10);
		
		moodtoday = new JTextField();
		moodtoday.setColumns(10);
		moodtoday.setBounds(839, 471, 138, 30);
		contentPane.add(moodtoday);
		
		JButton btnNewButton = new JButton("INFO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				daily info = new daily();
				daily.main(null);
			}	
		});
		btnNewButton.setBounds(987, 419, 54, 41);
		contentPane.add(btnNewButton);
		
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/91f845b06de5a68389cafa6ee5bcfcf5.jpg"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  JFileChooser fileChooser = new JFileChooser();
		           fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif")); // valid files 
		           int returnValue = fileChooser.showOpenDialog(null);

		           if (returnValue == JFileChooser.APPROVE_OPTION) {
		        	   selectedImageFile = fileChooser.getSelectedFile();
		        	   imagePaths.add(selectedImageFile.getAbsolutePath());

		               // Create an ImageIcon from the selected image file
		               ImageIcon imageIcon = new ImageIcon(selectedImageFile.getAbsolutePath());

		               // Set the ImageIcon as the icon for the btnUploadImage
		               btnNewButton_1.setIcon(imageIcon);
		               
		            
		           }
			
			}
		});
		btnNewButton_1.setBounds(85, 181, 214, 185);
		contentPane.add(btnNewButton_1);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("VISION BOARD !!");
		lblNewLabel_2.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		lblNewLabel_2.setBounds(221, 592, 173, 41);
		contentPane.add(lblNewLabel_2);
		

		JButton updatevision = new JButton("SAVE");
		updatevision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
			            Files.write(new File("imagePaths.txt").toPath(), imagePaths);
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }
			    }
				        
				    
				});

		
			
		updatevision.setBounds(249, 628, 117, 29);
		contentPane.add(updatevision);
		
		
		
		
		// Create a new button with the label "New button"
		btnNewButton_1_1 = new JButton("New button");

		// Set the initial icon for the button using an image file path
		btnNewButton_1_1.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/91f845b06de5a68389cafa6ee5bcfcf5.jpg"));

		// Add an ActionListener to the button to handle the button click event
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    // Define the actionPerformed method to be executed when the button is clicked
		    public void actionPerformed(ActionEvent e) {
		        // Create a new JFileChooser for selecting an image file
		        JFileChooser fileChooser = new JFileChooser();

		        // Set a file filter to restrict file selection to image files with specified extensions
		        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

		        // Show the file chooser dialog and wait for the user's selection
		        int returnValue = fileChooser.showOpenDialog(null);

		        // Check if the user selected a file
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            // Get the selected image file
		            selectedImageFile = fileChooser.getSelectedFile();

		            // Add the absolute path of the selected image file to the list of image paths
		            imagePaths.add(selectedImageFile.getAbsolutePath());

		            // Create an ImageIcon from the selected image file
		            ImageIcon imageIcon = new ImageIcon(selectedImageFile.getAbsolutePath());

		            // Set the ImageIcon as the new icon for the btnNewButton_1_1
		            btnNewButton_1_1.setIcon(imageIcon);
		        }
		    }
		});

		// Set the bounds (position and size) of the button on the content pane
		btnNewButton_1_1.setBounds(300, 181, 214, 185);

		// Add the button to the content pane
		contentPane.add(btnNewButton_1_1);

		
	btnNewButton_1_2 = new JButton("New button");
	btnNewButton_1_2.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/91f845b06de5a68389cafa6ee5bcfcf5.jpg"));
	btnNewButton_1_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

	        int returnValue = fileChooser.showOpenDialog(null);

	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	            selectedImageFile = fileChooser.getSelectedFile();
	            
	            // Check if the selected file has a valid extension
	            if (!isValidImageFile(selectedImageFile)) {
	                JOptionPane.showMessageDialog(null, "Please select a valid image file (jpg, jpeg, png, gif).", "Invalid File Format", JOptionPane.ERROR_MESSAGE);
	                return; // Exit the method without proceeding
	            }

	            // Continue with the rest of your code
	            imagePaths.add(selectedImageFile.getAbsolutePath());
	            ImageIcon imageIcon = new ImageIcon(selectedImageFile.getAbsolutePath());
	            btnNewButton_1_2.setIcon(imageIcon);
	        }
	    }

	    // Helper method to check if the file has a valid image extension
	    private boolean isValidImageFile(File file) {
	        String fileName = file.getName();
	        int lastDotIndex = fileName.lastIndexOf(".");
	        
	        // Check if the file has an extension
	        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
	            String fileExtension = fileName.substring(lastDotIndex + 1).toLowerCase();
	            return fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png") || fileExtension.equals("gif");
	        }
	        
	        return false;
	    }
	});

			
		btnNewButton_1_2.setBounds(85, 371, 214, 196);
		contentPane.add(btnNewButton_1_2);
		
		 btnNewButton_1_3 = new JButton("New button");
		 btnNewButton_1_3.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/91f845b06de5a68389cafa6ee5bcfcf5.jpg"));
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		           fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif")); // valid files 
		           int returnValue = fileChooser.showOpenDialog(null);

		           if (returnValue == JFileChooser.APPROVE_OPTION) {
		        	   selectedImageFile = fileChooser.getSelectedFile();
		        	   imagePaths.add(selectedImageFile.getAbsolutePath());

		               // Create an ImageIcon from the selected image file
		               ImageIcon imageIcon = new ImageIcon(selectedImageFile.getAbsolutePath());

		               // Set the ImageIcon as the icon for the btnUploadImage
		               btnNewButton_1_3.setIcon(imageIcon);
		               
		              
		           }
			
			}
		});
		
		btnNewButton_1_3.setBounds(300, 371, 214, 196);
		contentPane.add(btnNewButton_1_3);
		
		
	

		JLabel lblNewLabel = new JLabel("IMAGE INSERT");
		lblNewLabel.setFont(new Font("Myanmar MN", Font.BOLD, 21));
		lblNewLabel.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/board.png"));
		lblNewLabel.setBounds(0, 0, 1280, 702);
		contentPane.add(lblNewLabel);
		
		
	}
}
