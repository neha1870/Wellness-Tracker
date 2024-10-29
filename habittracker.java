package Wellnesstracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class habittracker extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					habittracker frame = new habittracker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private final JCheckBox[] checkboxes = new JCheckBox[35];  // Initialize the array size accordingly

	/**
	 * Create the frame.
	 * 
	 * 
	 */
	


	// Save the checkbox states to a file
	private void saveCheckboxStates() {
	    try (FileWriter writer = new FileWriter("checkbox_states.txt")) {
	        // Iterate through each checkbox in the checkboxes array
	        for (JCheckBox checkbox : checkboxes) {
	            // Get the selected state of the checkbox
	            boolean selected = checkbox.isSelected();
	            
	            // Write the selected state to the file followed by a newline character
	            writer.write(selected + "\n");
	        }
	    } catch (IOException e) {
	        // Handle IOException by printing the stack trace for debugging purposes
	        e.printStackTrace();
	    }
	}

	// Load the checkbox states from a file
	private void loadCheckboxStates() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("checkbox_states.txt"))) {
	        String line;
	        int index = 0;
	        
	        // Read each line from the file
	        while ((line = reader.readLine()) != null) {
	            // Parse the boolean value from the read line
	            boolean selected = Boolean.parseBoolean(line);
	            
	            // Set the checkbox state based on the parsed boolean value
	            checkboxes[index].setSelected(selected);
	            
	            // Move to the next checkbox in the checkboxes array
	            index++;
	        }
	    } catch (IOException e) {
	        // Handle IOException by printing the stack trace for debugging purposes
	        e.printStackTrace();
	    }
	}


	public habittracker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(320, 213, 36, 42);
		contentPane.add(chckbxNewCheckBox);
		
		final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setBounds(320, 306, 36, 42);
		contentPane.add(chckbxNewCheckBox_1);
		
		final JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
		chckbxNewCheckBox_2.setBounds(320, 408, 36, 42);
		contentPane.add(chckbxNewCheckBox_2);
		
		final JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
		chckbxNewCheckBox_3.setBounds(320, 504, 36, 42);
		contentPane.add(chckbxNewCheckBox_3);
		
		final JCheckBox chckbxNewCheckBox_4 = new JCheckBox("");
		chckbxNewCheckBox_4.setBounds(320, 594, 36, 42);
		contentPane.add(chckbxNewCheckBox_4);
		
		final JCheckBox chckbxNewCheckBox_5 = new JCheckBox("");
		chckbxNewCheckBox_5.setBounds(454, 213, 36, 42);
		contentPane.add(chckbxNewCheckBox_5);
		
		final JCheckBox chckbxNewCheckBox_6 = new JCheckBox("");
		chckbxNewCheckBox_6.setBounds(454, 306, 36, 42);
		contentPane.add(chckbxNewCheckBox_6);
		
		final JCheckBox chckbxNewCheckBox_7 = new JCheckBox("");
		chckbxNewCheckBox_7.setBounds(454, 408, 36, 42);
		contentPane.add(chckbxNewCheckBox_7);
		
		final JCheckBox chckbxNewCheckBox_8 = new JCheckBox("");
		chckbxNewCheckBox_8.setBounds(454, 504, 36, 42);
		contentPane.add(chckbxNewCheckBox_8);
		
		final JCheckBox chckbxNewCheckBox_9 = new JCheckBox("");
		chckbxNewCheckBox_9.setBounds(454, 594, 36, 42);
		contentPane.add(chckbxNewCheckBox_9);
		
		final JCheckBox chckbxNewCheckBox_10 = new JCheckBox("");
		chckbxNewCheckBox_10.setBounds(600, 213, 36, 42);
		contentPane.add(chckbxNewCheckBox_10);
		
		final JCheckBox chckbxNewCheckBox_11 = new JCheckBox("");
		chckbxNewCheckBox_11.setBounds(600, 306, 36, 42);
		contentPane.add(chckbxNewCheckBox_11);
		
		final JCheckBox chckbxNewCheckBox_12 = new JCheckBox("");
		chckbxNewCheckBox_12.setBounds(600, 408, 36, 42);
		contentPane.add(chckbxNewCheckBox_12);
		
		final JCheckBox chckbxNewCheckBox_13 = new JCheckBox("");
		chckbxNewCheckBox_13.setBounds(600, 504, 36, 42);
		contentPane.add(chckbxNewCheckBox_13);
		
		final JCheckBox chckbxNewCheckBox_14 = new JCheckBox("");
		chckbxNewCheckBox_14.setBounds(600, 594, 36, 42);
		contentPane.add(chckbxNewCheckBox_14);
		
		final JCheckBox chckbxNewCheckBox_15 = new JCheckBox("");
		chckbxNewCheckBox_15.setBounds(745, 213, 36, 42);
		contentPane.add(chckbxNewCheckBox_15);
		
		final JCheckBox chckbxNewCheckBox_16 = new JCheckBox("");
		chckbxNewCheckBox_16.setBounds(745, 306, 36, 42);
		contentPane.add(chckbxNewCheckBox_16);
		
		final JCheckBox chckbxNewCheckBox_17 = new JCheckBox("");
		chckbxNewCheckBox_17.setBounds(745, 408, 36, 42);
		contentPane.add(chckbxNewCheckBox_17);
		
		final JCheckBox chckbxNewCheckBox_18 = new JCheckBox("");
		chckbxNewCheckBox_18.setBounds(745, 504, 36, 42);
		contentPane.add(chckbxNewCheckBox_18);
		
		final JCheckBox chckbxNewCheckBox_19 = new JCheckBox("");
		chckbxNewCheckBox_19.setBounds(745, 594, 36, 42);
		contentPane.add(chckbxNewCheckBox_19);
		
		final JCheckBox chckbxNewCheckBox_20 = new JCheckBox("");
		chckbxNewCheckBox_20.setBounds(879, 213, 36, 42);
		contentPane.add(chckbxNewCheckBox_20);
		
		final JCheckBox chckbxNewCheckBox_21 = new JCheckBox("");
		chckbxNewCheckBox_21.setBounds(879, 306, 36, 42);
		contentPane.add(chckbxNewCheckBox_21);
		
		final JCheckBox chckbxNewCheckBox_22 = new JCheckBox("");
		chckbxNewCheckBox_22.setBounds(879, 408, 36, 42);
		contentPane.add(chckbxNewCheckBox_22);
		
		final JCheckBox chckbxNewCheckBox_23 = new JCheckBox("");
		chckbxNewCheckBox_23.setBounds(879, 504, 36, 42);
		contentPane.add(chckbxNewCheckBox_23);
		
		final JCheckBox chckbxNewCheckBox_24 = new JCheckBox("");
		chckbxNewCheckBox_24.setBounds(879, 594, 36, 42);
		contentPane.add(chckbxNewCheckBox_24);
		
		final JCheckBox chckbxNewCheckBox_25 = new JCheckBox("");
		chckbxNewCheckBox_25.setBounds(1028, 213, 36, 42);
		contentPane.add(chckbxNewCheckBox_25);
		
		final JCheckBox chckbxNewCheckBox_26 = new JCheckBox("");
		chckbxNewCheckBox_26.setBounds(1028, 306, 36, 42);
		contentPane.add(chckbxNewCheckBox_26);
		
		final JCheckBox chckbxNewCheckBox_27 = new JCheckBox("");
		chckbxNewCheckBox_27.setBounds(1028, 408, 36, 42);
		contentPane.add(chckbxNewCheckBox_27);
		
		final JCheckBox chckbxNewCheckBox_28 = new JCheckBox("");
		chckbxNewCheckBox_28.setBounds(1028, 504, 36, 42);
		contentPane.add(chckbxNewCheckBox_28);
		
		final JCheckBox chckbxNewCheckBox_29 = new JCheckBox("");
		chckbxNewCheckBox_29.setBounds(1028, 594, 36, 42);
		contentPane.add(chckbxNewCheckBox_29);
		
		final JCheckBox chckbxNewCheckBox_30 = new JCheckBox("");
		chckbxNewCheckBox_30.setBounds(1164, 213, 36, 42);
		contentPane.add(chckbxNewCheckBox_30);
		
		final JCheckBox chckbxNewCheckBox_31 = new JCheckBox("");
		chckbxNewCheckBox_31.setBounds(1164, 306, 36, 42);
		contentPane.add(chckbxNewCheckBox_31);
		
		final JCheckBox chckbxNewCheckBox_32 = new JCheckBox("");
		chckbxNewCheckBox_32.setBounds(1164, 408, 36, 42);
		contentPane.add(chckbxNewCheckBox_32);
		
		 final JCheckBox chckbxNewCheckBox_33 = new JCheckBox("");
		chckbxNewCheckBox_33.setBounds(1164, 504, 36, 42);
		contentPane.add(chckbxNewCheckBox_33);
		
		final JCheckBox chckbxNewCheckBox_34 = new JCheckBox("");
		chckbxNewCheckBox_34.setBounds(1164, 594, 36, 42);
		contentPane.add(chckbxNewCheckBox_34);
		
		    checkboxes[0] = chckbxNewCheckBox;
		    checkboxes[1] = chckbxNewCheckBox_1;
		    checkboxes[2] = chckbxNewCheckBox_2;
		    checkboxes[3] = chckbxNewCheckBox_3;
		    checkboxes[4] = chckbxNewCheckBox_4;
		    checkboxes[5] = chckbxNewCheckBox_5;
		    checkboxes[6] = chckbxNewCheckBox_6;
		    checkboxes[7] = chckbxNewCheckBox_7;
		    checkboxes[8] = chckbxNewCheckBox_8;
		    checkboxes[9] = chckbxNewCheckBox_9;
		    checkboxes[10] = chckbxNewCheckBox_10;
		    checkboxes[11] = chckbxNewCheckBox_11;
		    checkboxes[12] = chckbxNewCheckBox_12;
		    checkboxes[13] = chckbxNewCheckBox_13;
		    checkboxes[14] = chckbxNewCheckBox_14;
		    checkboxes[15] = chckbxNewCheckBox_15;
		    checkboxes[16] = chckbxNewCheckBox_16;
		    checkboxes[17] = chckbxNewCheckBox_17;
		    checkboxes[18] = chckbxNewCheckBox_18;
		    checkboxes[19] = chckbxNewCheckBox_19;
		    checkboxes[20] = chckbxNewCheckBox_20;
		    checkboxes[21] = chckbxNewCheckBox_21;
		    checkboxes[22] = chckbxNewCheckBox_22;
		    checkboxes[23] = chckbxNewCheckBox_23;
		    checkboxes[24] = chckbxNewCheckBox_24;
		    checkboxes[25] = chckbxNewCheckBox_25;
		    checkboxes[26] = chckbxNewCheckBox_26;
		    checkboxes[27] = chckbxNewCheckBox_27;
		    checkboxes[28] = chckbxNewCheckBox_28;
		    checkboxes[29] = chckbxNewCheckBox_29;
		    checkboxes[30] = chckbxNewCheckBox_30;
		    checkboxes[31] = chckbxNewCheckBox_31;
		    checkboxes[32] = chckbxNewCheckBox_32;
		    checkboxes[33] = chckbxNewCheckBox_33;
		    checkboxes[34] = chckbxNewCheckBox_34;
		   
		    
		    loadCheckboxStates();
		    
		   
		
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JCheckBox[] checkboxes = {
		                  chckbxNewCheckBox, chckbxNewCheckBox_1, chckbxNewCheckBox_2, chckbxNewCheckBox_3,chckbxNewCheckBox_4,chckbxNewCheckBox_5,chckbxNewCheckBox_6,chckbxNewCheckBox_7,chckbxNewCheckBox_8,chckbxNewCheckBox_9,chckbxNewCheckBox_10,chckbxNewCheckBox_11,chckbxNewCheckBox_12,chckbxNewCheckBox_13,chckbxNewCheckBox_14,chckbxNewCheckBox_15,chckbxNewCheckBox_16,chckbxNewCheckBox_17,chckbxNewCheckBox_18,chckbxNewCheckBox_19,chckbxNewCheckBox_20,chckbxNewCheckBox_21,chckbxNewCheckBox_22,chckbxNewCheckBox_23,chckbxNewCheckBox_24,chckbxNewCheckBox_25,chckbxNewCheckBox_26,chckbxNewCheckBox_27,chckbxNewCheckBox_28,chckbxNewCheckBox_29,chckbxNewCheckBox_30,chckbxNewCheckBox_31,chckbxNewCheckBox_32,chckbxNewCheckBox_33,chckbxNewCheckBox_34// ... and so on
		              };
		              for (JCheckBox checkbox : checkboxes) {
		                  checkbox.setSelected(false);
		                  
		                 
		              } 
			}
		}
		);
		btnClear.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 23));
		btnClear.setBounds(91, 113, 98, 42);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("8 glasses of water");
		lblNewLabel_1.setFont(new Font("Myanmar Sangam MN", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(6, 213, 163, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Sleep for \n8 hours");
		lblNewLabel.setFont(new Font("Myanmar Sangam MN", Font.PLAIN, 21));
		lblNewLabel.setBounds(6, 306, 163, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblEatHealthy = new JLabel("Eat Healthy");
		lblEatHealthy.setFont(new Font("Myanmar Sangam MN", Font.PLAIN, 21));
		lblEatHealthy.setBounds(6, 394, 118, 42);
		contentPane.add(lblEatHealthy);
		
		JLabel lblMeditateFor = new JLabel("Meditate\n");
		lblMeditateFor.setFont(new Font("Myanmar Sangam MN", Font.PLAIN, 21));
		lblMeditateFor.setBounds(6, 488, 87, 30);
		contentPane.add(lblMeditateFor);
		
		JLabel lblWorkout = new JLabel("Workout");
		lblWorkout.setFont(new Font("Myanmar Sangam MN", Font.PLAIN, 21));
		lblWorkout.setBounds(6, 580, 87, 30);
		contentPane.add(lblWorkout);
		
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
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/habit tracker.png"));
		lblNewLabel_2.setBounds(0, 0,1280, 702);
		contentPane.add(lblNewLabel_2);
		
	
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        saveCheckboxStates();
		    }
		});

                  
                 
		
		
		
	}
}
 
