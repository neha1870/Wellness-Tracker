package Wellnesstracker.gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class dashboard extends JFrame {

	private JPanel contentPane;
	private JTextField txtsearch;
	private JTextField txtDate;
	private JTextField txtTask;
	private JTable table;
	private JTextField txtProgress;
	private static final Color GREEN_COLOR = new Color(0, 255, 0); // RGB value for green

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dashboard frame = new dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}





//}
	
	public dashboard() { 
		notifs();
		loadTable();
		 setupTableSelectionListener();
		
		
		
	}
	
	
	
	
	// Method to set up a ListSelectionListener for the table
	private void setupTableSelectionListener() {
	    // Adding a ListSelectionListener to the table's selection model
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        // ListSelectionListener method for handling selection changes
	        public void valueChanged(ListSelectionEvent e) {
	            // Checking if the selection change is still adjusting (e.g., during a drag)
	            if (!e.getValueIsAdjusting()) {
	                // Getting the index of the selected row
	                int selectedRow = table.getSelectedRow();

	                // Checking if a row is selected
	                if (selectedRow != -1) {
	                    // Setting the background color of the selected row to red
	                    table.setSelectionBackground(Color.RED);

	                    // Retrieving values from the selected row
	                    String status = table.getValueAt(selectedRow, 4).toString(); // Assuming "Status" is the fifth column (index 4)
	                    String date = table.getValueAt(selectedRow, 1).toString();
	                    String task = table.getValueAt(selectedRow, 2).toString();
	                    String progress = table.getValueAt(selectedRow, 3).toString();

	                    // Setting text fields with values from the selected row
	                    txtDate.setText(date);
	                    txtTask.setText(task);
	                    txtProgress.setText(progress);

	                    // Adjusting the background color based on the status of the task
	                    if ("Done".equalsIgnoreCase(status)) {
	                        table.setSelectionBackground(GREEN_COLOR);
	                    } else {
	                        table.setSelectionBackground(Color.RED);
	                    }
	                }
	            }
	        }
	    });
	}




		       
		                
	
	public void loadTable() { 
		
		Connection conn = null;
		
		try {
			conn = DatabaseManager.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tasklist;");
			ResultSet rs = pstmt.executeQuery();
			
	       
			// table.setModel((TableModel) DatabaseManager.buildTableModel(rs));
	        
			DefaultTableModel tableModel = DatabaseManager.buildTableModel(rs);
	        table.setModel(tableModel);

	        pstmt.close();
					
		}catch(Exception e) { 
			e.printStackTrace();
		}
	
	}
	
	

	private void notifs() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		txtsearch = new JTextField();
		txtsearch.setBounds(112, 519, 451, 57);
		contentPane.add(txtsearch);
		txtsearch.setColumns(10);
		
		// Creating a JButton for search
		JButton btnsearch = new JButton("SEARCH");

		// Adding ActionListener to the search button
		btnsearch.addActionListener(new ActionListener() {
		    // ActionListener method for handling button click
		    public void actionPerformed(ActionEvent e) {
		        // Getting the search query from the text field and trimming any leading/trailing spaces
		        String searchQuery = txtsearch.getText().trim();

		        // Checking if the search query is empty
		        if (searchQuery.length() == 0) {
		            // If empty, load the original table data
		            loadTable();
		        } else {
		            // If not empty, perform a search

		            // Getting the original table model
		            DefaultTableModel originalTableModel = (DefaultTableModel) table.getModel();

		            // Creating a new table model for filtered results
		            DefaultTableModel newTableModel = new DefaultTableModel();
		            newTableModel.setColumnIdentifiers(new String[] {"task_id", "date", "Task", "Progress"});

		            // Iterating through each row in the original table model
		            for (int i = 0; i < originalTableModel.getRowCount(); i++) {
		                // Extracting the task description from the current row
		                String task = originalTableModel.getValueAt(i, 2).toString();

		                // Checking if the task description contains the search query (case-insensitive)
		                if (task.toLowerCase().contains(searchQuery.toLowerCase())) {
		                    // If found, adding the matching row to the new table model
		                    newTableModel.addRow(new Object[] {
		                        originalTableModel.getValueAt(i, 0),
		                        originalTableModel.getValueAt(i, 1),
		                        originalTableModel.getValueAt(i, 2),
		                        originalTableModel.getValueAt(i, 3)
		                    });
		                }
		            }

		            // Setting the table model to the one containing the search results
		            table.setModel(newTableModel);
		        }
		    }
		});


		btnsearch.setFont(new Font("Al Bayan", Font.BOLD, 23));
		btnsearch.setBounds(43, 611, 520, 57);
		contentPane.add(btnsearch);
		
		JButton btnadd = new JButton("ADD");
		btnadd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get text from text fields
		        String date = txtDate.getText();
		        String task = txtTask.getText();
		        String progress = txtProgress.getText();

		        // Check if any field is empty
		        if (date.isEmpty() || task.isEmpty() || progress.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please fill in all the details", "Missing Information", JOptionPane.WARNING_MESSAGE);
		        } else {
		            // Insert the data into the database
		            try {
		                Connection conn = DatabaseManager.getConnection();
		                String insertQuery = "INSERT INTO tasklist (date, Task, Progress) VALUES (?, ?, ?)";
		                PreparedStatement pstmt = conn.prepareStatement(insertQuery);
		                pstmt.setString(1, date);
		                pstmt.setString(2, task);
		                pstmt.setString(3, progress);
		                pstmt.executeUpdate();
		                pstmt.close();
		                loadTable(); // Reload the table to show the new data
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});




		btnadd.setFont(new Font("Al Bayan", Font.BOLD, 21));
		btnadd.setBounds(20, 166, 153, 57);
		contentPane.add(btnadd);
		
		// Create a JButton with the label "DELETE"
		JButton btndelete = new JButton("DELETE");

		
		btndelete.setFont(new Font("Al Bayan", Font.BOLD, 21));

		
		btndelete.addActionListener(new ActionListener() {
		    // Implement the actionPerformed method
		    public void actionPerformed(ActionEvent e) {
		        // Get the index of the selected row in the table
		        int selectedRow = table.getSelectedRow();
		        
		        // Check if a row is selected
		        if (selectedRow != -1) {
		            // Retrieve the task_id from the selected row in the table
		            String taskId = table.getValueAt(selectedRow, 0).toString();

		            try {
		                // Establish a database connection
		                Connection conn = DatabaseManager.getConnection();

		                // Define the SQL query for deleting a row in the tasklist table
		                String deleteQuery = "DELETE FROM tasklist WHERE task_id = ?";

		                // Create a prepared statement to prevent SQL injection
		                PreparedStatement pstmt = conn.prepareStatement(deleteQuery);

		                // Set the parameter in the prepared statement with the task_id
		                pstmt.setString(1, taskId);

		                // Execute the update to delete the row from the database
		                pstmt.executeUpdate();

		                // Close the prepared statement
		                pstmt.close();

		                // Reload the table to reflect the changes
		                loadTable();
		            } catch (Exception ex) {
		                // Print the stack trace if an exception occurs (for debugging purposes)
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		btndelete.setBounds(20, 251, 153, 57);
		contentPane.add(btndelete);
		
		
		JButton btnupdate = new JButton("UPDATE");
		btnupdate.setFont(new Font("Al Bayan", Font.BOLD, 21));

		// Add an ActionListener to handle button clicks
		btnupdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get the index of the selected row in the table
		        int selectedRow = table.getSelectedRow();

		        // Check if a row is selected
		        if (selectedRow != -1) {
		            // Retrieve the task ID from the selected row in the table
		            String taskId = table.getValueAt(selectedRow, 0).toString();

		            // Get new values from text fields for date, task, and progress
		            String newDate = txtDate.getText();
		            String newTask = txtTask.getText();
		            String newProgress = txtProgress.getText();

		            try {
		                // Establish a connection to the database
		                Connection conn = DatabaseManager.getConnection();

		                // Define the SQL query for updating a row in the tasklist table
		                String updateQuery = "UPDATE tasklist SET date = ?, Task = ?, Progress = ? WHERE task_id = ?";

		                // Create a prepared statement to prevent SQL injection
		                PreparedStatement pstmt = conn.prepareStatement(updateQuery);

		                // Set parameters in the prepared statement with new values
		                pstmt.setString(1, newDate);
		                pstmt.setString(2, newTask);
		                pstmt.setString(3, newProgress);
		                pstmt.setString(4, taskId);

		                // Execute the update to modify the row in the database
		                pstmt.executeUpdate();

		                // Close the prepared statement
		                pstmt.close();

		                // Reload the table to reflect the changes
		                loadTable();
		            } catch (Exception ex) {
		                // Print the stack trace if an exception occurs (for debugging purposes)
		                ex.printStackTrace();
		            }
		        }
		    }
		});






		btnupdate.setBounds(20, 333, 153, 57);
		contentPane.add(btnupdate);
		
		txtDate = new JTextField();
		txtDate.setBounds(303, 166, 236, 89);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		txtTask = new JTextField();
		txtTask.setColumns(10);
		txtTask.setBounds(303, 270, 236, 102);
		contentPane.add(txtTask);
		
		table = new JTable();
		table.setBackground(new Color(255, 204, 51));
		table.setBounds(0, 26, 443, 224);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(624, 142, 650, 526); // Set different coordinates
		contentPane.add(scrollPane);

		
		JLabel lblNewLabel_1 = new JLabel("Wellness priority task list");
		lblNewLabel_1.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		lblNewLabel_1.setBounds(845, 85, 241, 45);
		contentPane.add(lblNewLabel_1);
		
		txtProgress = new JTextField();
		txtProgress.setColumns(10);
		txtProgress.setBounds(303, 391, 236, 102);
		contentPane.add(txtProgress);
		
		// Creating a JButton for marking a task as "DONE"
		JButton btndone = new JButton("DONE");

		// Adding ActionListener to the "DONE" button
		btndone.addActionListener(new ActionListener() {
		    // ActionListener method for handling button click
		    public void actionPerformed(ActionEvent e) {
		        // Getting the index of the selected row in the table
		        int selectedRow = table.getSelectedRow();

		        // Checking if a row is selected
		        if (selectedRow != -1) {
		            // If a row is selected, retrieve the task ID from the selected row
		            String taskId = table.getValueAt(selectedRow, 0).toString();

		            try {
		                // Establishing a connection to the database
		                Connection conn = DatabaseManager.getConnection();

		                // Defining the SQL query to update the task status to 'Done'
		                String updateStatusQuery = "UPDATE tasklist SET Status = 'Done' WHERE task_id = ?";

		                // Creating a prepared statement for executing the update
		                PreparedStatement pstmt = conn.prepareStatement(updateStatusQuery);

		                // Setting the task ID parameter in the prepared statement
		                pstmt.setString(1, taskId);

		                // Executing the update
		                pstmt.executeUpdate();

		                // Closing the prepared statement
		                pstmt.close();

		                // Reload the table to reflect the changes in task status
		                loadTable();

		                // Set the background color of the selected row to green to visually indicate completion
		                table.setSelectionBackground(GREEN_COLOR);

		            } catch (Exception ex) {
		                // Print stack trace if an exception occurs during the database operation
		                ex.printStackTrace();
		            }
		        }
		    }
		});
 
		
		
		
		
		
		
		
		
	
	btndone.setFont(new Font("Al Bayan", Font.BOLD, 21));
		btndone.setBounds(20, 414, 153, 61);
		contentPane.add(btndone);
		
		
		
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
		
		JButton btngoal = new JButton("Goal Setting");
		btngoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				diaryentry info = new diaryentry();
				diaryentry.main(null);
			}
		});
		btngoal.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btngoal.setBounds(761, 16, 180, 57);
		contentPane.add(btngoal);
		
		JButton btnreflection = new JButton("Email/Reflection");
		btnreflection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			goalsetting info = new goalsetting();
			goalsetting.main(null);
			}
		});
		btnreflection.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnreflection.setBounds(1077, 16, 180, 57);
		contentPane.add(btnreflection);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Date :");
		lblNewLabel_2.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		lblNewLabel_2.setBounds(229, 191, 62, 39);
		contentPane.add(lblNewLabel_2);
		
		
		
		JLabel lblNewLabel_2_1 = new JLabel("Task :");
		lblNewLabel_2_1.setFont(new Font("Malayalam Sangam MN", Font.BOLD, 21));
		lblNewLabel_2_1.setBounds(229, 303, 62, 39);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Progress :");
		lblNewLabel_2_1_1.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		lblNewLabel_2_1_1.setBounds(195, 423, 96, 39);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/dashboard.png"));
		lblNewLabel.setBounds(0, 0, 1280, 692);
		contentPane.add(lblNewLabel);
		
		
		
		
	}
}
