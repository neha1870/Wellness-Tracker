package Wellnesstracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class daily extends JFrame {

	private JPanel contentPane;
	private JTable journaltable;
	private JTextField tdyjournal;
	private JTextField textdate;
	private JTextField mood1;
	private JTextArea area1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					daily frame = new daily();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public daily() { 
		dj();
		loadTable();
	
		
		
		
	}

	public void loadTable() { 
		
		Connection conn1 = null;
		
		try {
			conn1 = DatabaseManager.getConnection();
			
			PreparedStatement pstmt1 = conn1.prepareStatement("SELECT * FROM journal;");
			ResultSet rs1 = pstmt1.executeQuery();
			
	       
			// table.setModel((TableModel) DatabaseManager.buildTableModel(rs));
	        
			DefaultTableModel tableModel = DatabaseManager.buildTableModel(rs1);
	        journaltable.setModel(tableModel);

	        pstmt1.close();
					
		}catch(Exception e) { 
			e.printStackTrace();
		}
	
	}
	
	
	
	private void  dj() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 area1 = new JTextArea(); // Initialize JTextArea
	        area1.setBounds(16, 243, 619, 384);
	        contentPane.add(area1);
		

	
		
		JButton btnNewButton = new JButton("1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				area1.setText("************************ MOOD TRACKER ************************\n");
				area1.setText("Dont stress too much?\n" );
				area1.setText("everything will be good and just work hard\n" );
			
			}
		});
		btnNewButton.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/18.png"));
		btnNewButton.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 24));
		btnNewButton.setBounds(17, 137, 64, 64);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("2");
		btnNewButton_1.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/19.png"));
		btnNewButton_1.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 24));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
				 area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Dont stress too much?\n" );
					area1.setText("everything will be good and just work hard\n" );;
			}
		});
		btnNewButton_1.setBounds(76, 137, 64, 64);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("3");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
				 area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Dont stress too much?\n" );
					area1.setText("everything will be good and just work hard\n" );
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/20.png"));
		btnNewButton_3.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 24));
		btnNewButton_3.setBounds(137, 137, 64, 64);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("4");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
				 area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Dont stress too much?\n" );
					area1.setText("everything will be good and just work hard\n" );
			}
		});
		btnNewButton_4.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/21.png"));
		btnNewButton_4.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 24));
		btnNewButton_4.setBounds(196, 137, 64, 64);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
				 area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Dont stress too much?\n" );
					area1.setText("everything will be good and just work hard\n" );
			}
		});
		btnNewButton_5.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/22.png"));
		btnNewButton_5.setBounds(258, 137, 64, 64);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
					area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Hey , you seem like you are in a good mood \n" );
					area1.setText("Keep going\n" );
			}
		});
		btnNewButton_6.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/23.png"));
		btnNewButton_6.setBounds(321, 137, 64, 64);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
				 area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Hey , you seem like you are in a good mood \n" );
					area1.setText("Keep going\n" );
			}
		});
		btnNewButton_7.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/24.png"));
		btnNewButton_7.setBounds(385, 137, 64, 64);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("New button");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
				 area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Hey , you seem like you are in a good mood \n" );
					area1.setText("Keep going\n" );
			}
		});
		btnNewButton_8.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/25.png"));
		btnNewButton_8.setBounds(446, 137, 64, 64);
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("New button");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
				 area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Hey , you seem like you are in a good mood \n" );
					area1.setText("Keep going\n" );
			}
		});
		btnNewButton_9.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/27.png"));
		btnNewButton_9.setBounds(509, 137, 64, 64);
		contentPane.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("New button");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea area = null;
				 area1.setText("************************ MOOD TRACKER ************************\n");
					area1.setText("Hey , you seem like you are in a good mood \n" );
					area1.setText("Keep going\n" );
			}
		});
		btnNewButton_10.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/COLOURSBUTTON 2/26.png"));
		btnNewButton_10.setBounds(572, 137, 64, 64);
		contentPane.add(btnNewButton_10);
		
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("How is your mood today on a scale of one to ten ?");
		lblNewLabel_1.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		lblNewLabel_1.setBounds(112, 100, 461, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Return ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diaryentry info = new diaryentry();
				diaryentry.main(null);
			}
		});
		btnNewButton_2.setFont(new Font("Myanmar Sangam MN", Font.BOLD, 21));
		btnNewButton_2.setBounds(269, 24, 131, 53);
		contentPane.add(btnNewButton_2);
		
				
			
				
				JLabel lblNewLabel = new JLabel("New label");
				lblNewLabel.setIcon(new ImageIcon("/Users/sikhakollujwala/Downloads/SUGESTIO.png"));
				lblNewLabel.setBounds(0, 0, 672, 720);
				contentPane.add(lblNewLabel);
		
		
		
		
		
		
		
	}
}
