package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.TimingController;
import model.Timing;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class updateTiming extends JFrame {

	 private static final String TIME_REGEX = "^[0-5]?[0-9]:[0-5][0-9]:[0-9]{3}$";
	 int lapNumber1=0;
	 String time1 = "";
	 double speed1 = 0;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLapNumber;
	private JTextField txtTime;
	private JTextField txtSpeed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String lapNumber,String time,String speed,int setupID,int circuitID,int timingID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateTiming frame = new updateTiming( lapNumber, time, speed, setupID, circuitID, timingID);
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
	public updateTiming(String lapNumber,String time,String speed,int setupID,int circuitID,int timingID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel selectIDLabel = new JLabel("Selected Circuit ID : ");
		selectIDLabel.setFont(new Font("The Rift Shop", Font.PLAIN, 22));
		selectIDLabel.setBounds(359, 65, 187, 35);
		contentPane.add(selectIDLabel);
		
		JLabel circuitIDLabel = new JLabel(Integer.toString(circuitID));
		circuitIDLabel.setFont(new Font("The Rift Shop", Font.PLAIN, 22));
		circuitIDLabel.setBounds(556, 63, 41, 38);
		contentPane.add(circuitIDLabel);
		
		JLabel lblLap = new JLabel("Lap Number :");
		lblLap.setFont(new Font("The Rift Shop", Font.PLAIN, 18));
		lblLap.setBounds(354, 148, 98, 22);
		contentPane.add(lblLap);
		
		txtLapNumber = new JTextField();
		txtLapNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLapNumber.setBounds(462, 148, 86, 20);
		contentPane.add(txtLapNumber);
		txtLapNumber.setColumns(10);
		txtLapNumber.setText(lapNumber);
		
		txtTime = new JTextField();
		txtTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTime.setBounds(462, 190, 86, 20);
		contentPane.add(txtTime);
		txtTime.setColumns(10);
		txtTime.setText(time);
		
		JLabel lblTime = new JLabel("Time :");
		lblTime.setFont(new Font("The Rift Shop", Font.PLAIN, 18));
		lblTime.setBounds(387, 181, 40, 27);
		contentPane.add(lblTime);
		
		JLabel lblSpeed = new JLabel("Speed km/h :");
		lblSpeed.setFont(new Font("The Rift Shop", Font.PLAIN, 18));
		lblSpeed.setBounds(354, 219, 86, 30);
		contentPane.add(lblSpeed);
		
		txtSpeed = new JTextField();
		txtSpeed.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtSpeed.setBounds(462, 231, 86, 20);
		contentPane.add(txtSpeed);
		txtSpeed.setColumns(10);
		txtSpeed.setText(speed);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(0, 0, 0));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBackground(new Color(255, 255, 255));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtLapNumber.getText()=="" | txtTime.getText()=="" | txtSpeed.getText()==""| setupID == 0)
				{
					 JDialog customDialog = new JDialog();
				        customDialog.setTitle("Custom Dialog");
				        
				        JPanel panel = new JPanel();
				        JLabel label = new JLabel("Please Enter All Details First!");
				        JButton okButton = new JButton("OK");

				        okButton.addActionListener(new ActionListener() {
				            
				            public void actionPerformed(ActionEvent e) {
				                customDialog.dispose(); 
				            }
				        });

				        panel.add(label);
				        panel.add(okButton);
				        customDialog.getContentPane().add(panel);

				        // Set the dialog's size and position
				        customDialog.setSize(300, 100);
				        customDialog.setLocation(300,300);

				        // Make the dialog visible
				        customDialog.setVisible(true);
					
				}
				else
				{
					if (isPositiveInteger(txtLapNumber.getText())) {
			        
			            lapNumber1 = Integer.parseInt(txtLapNumber.getText());
			          
			        } else {
			           
			            JOptionPane.showMessageDialog(null, "Please enter a valid positive integer for Lap Number.");
			            return; 
			        }

			    
			        if (isValidTimeFormat(txtTime.getText())) {
			       
			            time1 = txtTime.getText();
			           
			        } else {
			            
			            JOptionPane.showMessageDialog(null, "Please enter a valid time format (h:mm:SSS).");
			            return;
			        }

			       
			        if (isValidDouble(txtSpeed.getText())) {
			          
			           speed1 = Double.parseDouble(txtSpeed.getText());
			      
			        } else {
			         
			            JOptionPane.showMessageDialog(null, "Please enter a valid double for Speed.");
			            return;
			        }
			        
			        JOptionPane.showMessageDialog(null, "Timings Successfully Updated.");
			        
					Timing timing = new Timing();
					
					timing.setLapNumber(lapNumber1);
					timing.setTime(time1);
					timing.setAverageSpeed(speed1);
					timing.setSetupID(setupID);
					timing.setTimingID(timingID);
					
					TimingController timingController = new TimingController();
					
					try {
						timingController.updateTiming(timing);
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					TimingPage frame = new TimingPage(circuitID,0);
					frame.setVisible(true);
					dispose();
				
				}
			}
		});
		btnUpdate.setBounds(363, 391, 89, 35);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimingPage frame = new TimingPage(circuitID,0);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(508, 391, 89, 35);
		contentPane.add(btnBack);
		
		JButton btnSetup = new JButton("Choose Car Setup");
		btnSetup.setForeground(new Color(0, 0, 0));
		btnSetup.setBackground(new Color(255, 255, 255));
		btnSetup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseSetup frame = new chooseSetup(txtLapNumber.getText(), txtTime.getText(), txtSpeed.getText(), circuitID,timingID,0);
				frame.setVisible(true);
				dispose();
				
			}
		});
		btnSetup.setBounds(397, 327, 138, 30);
		contentPane.add(btnSetup);
		
		JLabel lblSetup = new JLabel("Car Setup :");
		lblSetup.setFont(new Font("The Rift Shop", Font.PLAIN, 18));
		lblSetup.setBounds(354, 260, 71, 24);
		contentPane.add(lblSetup);
		
		JLabel lblSetupID = new JLabel(Integer.toString(setupID));
		lblSetupID.setFont(new Font("The Rift Shop", Font.PLAIN, 18));
		lblSetupID.setBounds(472, 262, 46, 22);
		contentPane.add(lblSetupID);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 217, 462);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMain = new JLabel("UPDATE ");
		lblMain.setForeground(new Color(255, 255, 255));
		lblMain.setBounds(60, 56, 147, 51);
		panel.add(lblMain);
		lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 34));
		
		JLabel lblNewLabel_2 = new JLabel("TIMING LIST");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("The Rift Shop", Font.PLAIN, 34));
		lblNewLabel_2.setBounds(24, 104, 162, 43);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(updateTiming.class.getResource("/images/360_F_528780055_eQ2UYVU0YYh9bFJTLk9q2orIAkbCkSUg-removebg-preview (2) (1).png")));
		lblNewLabel_3.setBounds(10, 233, 197, 120);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(updateTiming.class.getResource("/images/kisspng-petronas-dagangan-berhad-logo-organization-company-conference-vector-5ad86d8ea7ff61.1371625815241332626881-removebg-preview (1).png")));
		lblNewLabel_1.setBounds(716, 315, 98, 136);
		contentPane.add(lblNewLabel_1);
		

	}
	
	// Validation method for positive integer
		private static boolean isPositiveInteger(String input) {
	        try {
	            int number = Integer.parseInt(input);
	            return number > 0;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }

	    // Validation method for time format (h:mm:SSS)
		private static boolean isValidTimeFormat(String input) {

	        Pattern pattern = Pattern.compile(TIME_REGEX);
	        Matcher matcher = pattern.matcher(input);

	        return matcher.matches();
	    }

	    // Validation method for double
	    private static boolean isValidDouble(String input) {
	        try {
	            double number = Double.parseDouble(input);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
}
