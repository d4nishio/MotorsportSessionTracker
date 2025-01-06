package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.TimingController;
import controller.UserController;
import model.Timing;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class addTiming extends JFrame {
	
	 private static final String TIME_REGEX = "^[0-5]?[0-9]:[0-5][0-9]:[0-9]{3}$";
	 int lapNumber1=0;
	 String time1 = "";
	 double speed1 = 0;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLapNumber;
	private JTextField txtTime;
	private JTextField txtSpeed;
	private JTable tableTiming;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String lapNumber,String time,String speed,int setupID,int circuitID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addTiming frame = new addTiming(lapNumber, time, speed, setupID, circuitID);
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
	public addTiming(String lapNumber,String time,String speed,int setupID,int circuitID) {
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel selectIDLabel = new JLabel("Selected Circuit ID : ");
		selectIDLabel.setFont(new Font("The Rift Shop", Font.PLAIN, 16));
		selectIDLabel.setForeground(Color.DARK_GRAY);
		selectIDLabel.setBounds(427, 60, 126, 14);
		contentPane.add(selectIDLabel);
		
		JLabel circuitIDLabel = new JLabel(Integer.toString(circuitID));
		circuitIDLabel.setForeground(Color.DARK_GRAY);
		circuitIDLabel.setFont(new Font("The Rift Shop", Font.PLAIN, 16));
		circuitIDLabel.setBounds(563, 60, 41, 14);
		contentPane.add(circuitIDLabel);
		
		JLabel lblMain = new JLabel("ADD TIMING LIST");
		lblMain.setForeground(new Color(32, 178, 170));
		lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 25));
		lblMain.setBounds(428, 11, 159, 39);
		contentPane.add(lblMain);
		
		JLabel lblLap = new JLabel("Lap Number :");
		lblLap.setFont(new Font("The Rift Shop", Font.PLAIN, 15));
		lblLap.setBounds(12, 152, 98, 22);
		contentPane.add(lblLap);
		
		txtLapNumber = new JTextField();
		txtLapNumber.setBackground(Color.LIGHT_GRAY);
		txtLapNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtLapNumber.setBounds(95, 153, 86, 20);
		contentPane.add(txtLapNumber);
		txtLapNumber.setColumns(10);
		txtLapNumber.setText(lapNumber);
		
		txtTime = new JTextField();
		txtTime.setBackground(Color.LIGHT_GRAY);
		txtTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTime.setBounds(95, 195, 86, 20);
		contentPane.add(txtTime);
		txtTime.setColumns(10);
		txtTime.setText(time);
		
		JButton btnAdd = new JButton("Add ");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setBackground(Color.WHITE);
 
		if(setupID!=0)
		{
			btnAdd.setEnabled(true);
		}
		else
		{
			btnAdd.setEnabled(false);
		}
		

		btnAdd.addActionListener(new ActionListener() {
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
			        
			        JOptionPane.showMessageDialog(null, "Timings Successfully Added.");
			        
					Timing timing = new Timing();
					
					timing.setLapNumber(lapNumber1);
					timing.setTime(time1);
					timing.setAverageSpeed(speed1);
					timing.setSetupID(setupID);
					
					TimingController timingController = new TimingController();
					
					try {
						timingController.insertTiming(timing,circuitID);
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					addTiming frame = new addTiming( "", "", "", 0, circuitID);
					frame.setVisible(true);
					dispose();
				
				}
			}
		});
		btnAdd.setBounds(21, 421, 89, 23);
		contentPane.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(305, 98, 446, 317);
		contentPane.add(scrollPane);
		
		tableTiming = new JTable();
		scrollPane.setViewportView(tableTiming);
		
		tableTiming.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Lap Number", "Time", "Gap", "Avg. Speed", "Car Setup"
			}
		)
		{
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {                
		                return false;               
		        }
		});
		
	
		
		tableTiming.getColumnModel().getColumn(0).setPreferredWidth(90);
		tableTiming.getColumnModel().getColumn(0).setMinWidth(90);
		tableTiming.getColumnModel().getColumn(0).setMaxWidth(90);
		tableTiming.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableTiming.getColumnModel().getColumn(1).setMinWidth(90);
		tableTiming.getColumnModel().getColumn(1).setMaxWidth(90);
		tableTiming.getColumnModel().getColumn(2).setPreferredWidth(90);
		tableTiming.getColumnModel().getColumn(2).setMinWidth(90);
		tableTiming.getColumnModel().getColumn(2).setMaxWidth(90);
		tableTiming.getColumnModel().getColumn(3).setPreferredWidth(90);
		tableTiming.getColumnModel().getColumn(3).setMinWidth(90);
		tableTiming.getColumnModel().getColumn(3).setMaxWidth(90);
		tableTiming.getColumnModel().getColumn(4).setPreferredWidth(90);
		tableTiming.getColumnModel().getColumn(4).setMinWidth(90);
		tableTiming.getColumnModel().getColumn(4).setMaxWidth(90);
		DefaultTableModel model = (DefaultTableModel) tableTiming.getModel();
		
		
		JLabel lblTime = new JLabel("Time :");
		lblTime.setFont(new Font("The Rift Shop", Font.PLAIN, 15));
		lblTime.setBounds(56, 198, 40, 14);
		contentPane.add(lblTime);
		
		JLabel lblSpeed = new JLabel("Speed km/h :");
		lblSpeed.setFont(new Font("The Rift Shop", Font.PLAIN, 15));
		lblSpeed.setBounds(12, 239, 86, 14);
		contentPane.add(lblSpeed);
		
		txtSpeed = new JTextField();
		txtSpeed.setBackground(Color.LIGHT_GRAY);
		txtSpeed.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtSpeed.setBounds(95, 236, 86, 20);
		contentPane.add(txtSpeed);
		txtSpeed.setColumns(10);
		txtSpeed.setText(speed);
		
		
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimingPage frame = new TimingPage(circuitID,0);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(135, 421, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblSetup = new JLabel("Car Setup :");
		lblSetup.setFont(new Font("The Rift Shop", Font.PLAIN, 15));
		lblSetup.setBounds(12, 274, 71, 14);
		contentPane.add(lblSetup);
		
		JLabel lblSetupID = new JLabel(Integer.toString(setupID));
		lblSetupID.setFont(new Font("The Rift Shop", Font.PLAIN, 15));
		lblSetupID.setBounds(105, 274, 46, 14);
		contentPane.add(lblSetupID);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(0, 0, 234, 462);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPetronasImage = new JLabel("");
		lblPetronasImage.setIcon(new ImageIcon(addTiming.class.getResource("/images/kisspng-petronas-dagangan-berhad-logo-organization-company-conference-vector-5ad86d8ea7ff61.1371625815241332626881-removebg-preview (1).png")));
		lblPetronasImage.setBounds(61, 30, 119, 98);
		panel_1.add(lblPetronasImage);
		
		JButton btnSetup = new JButton("Choose Car Setup");
		btnSetup.setBounds(42, 326, 138, 30);
		panel_1.add(btnSetup);
		btnSetup.setBackground(Color.WHITE);
		btnSetup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseSetup frame = new chooseSetup(txtLapNumber.getText(), txtTime.getText(), txtSpeed.getText(), circuitID,0,0);
				frame.setVisible(true);
				dispose();
				
			}
		});
		
		
		TimingController timingController = new TimingController();
		
		try {
			List<Timing> timings = timingController.viewTiming(circuitID,0,0);
			
			for (Timing timing : timings) {
                Object[] row = {timing.getLapNumber(),timing.getTime(),timing.getGap(),timing.getAverageSpeed(),timing.getSetupID()};
                model.addRow(row);
            }
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	// Validation method for lapNumber
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
