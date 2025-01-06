package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.SetupController;
import model.Setup;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class chooseSetup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableSetup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String lapNumber,String time,String speed,int circuitID,int timingID, int choice) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chooseSetup frame = new chooseSetup(lapNumber, time, speed, circuitID, timingID,choice);
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
	public chooseSetup(String lapNumber,String time,String speed, int circuitID,int timingID,int choice) {
		int setupID = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(292, 98, 488, 285);
		contentPane.add(scrollPane);
		
		tableSetup = new JTable();
		tableSetup.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Setup ID", "F Wing Angle", "R Wing Angle", "Gear Ratio", "Suspension Stiffness"
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
		
		tableSetup.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableSetup.getColumnModel().getColumn(0).setMinWidth(80);
		tableSetup.getColumnModel().getColumn(0).setMaxWidth(80);
		tableSetup.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableSetup.getColumnModel().getColumn(1).setMinWidth(90);
		tableSetup.getColumnModel().getColumn(1).setMaxWidth(90);
		tableSetup.getColumnModel().getColumn(2).setPreferredWidth(90);
		tableSetup.getColumnModel().getColumn(2).setMinWidth(90);
		tableSetup.getColumnModel().getColumn(2).setMaxWidth(90);
		tableSetup.getColumnModel().getColumn(3).setPreferredWidth(90);
		tableSetup.getColumnModel().getColumn(3).setMinWidth(90);
		tableSetup.getColumnModel().getColumn(3).setMaxWidth(90);
		tableSetup.getColumnModel().getColumn(4).setPreferredWidth(140);
		tableSetup.getColumnModel().getColumn(4).setMinWidth(140);
		tableSetup.getColumnModel().getColumn(4).setMaxWidth(140);
		scrollPane.setViewportView(tableSetup);
		
		SetupController setupController = new SetupController();
		DefaultTableModel model = (DefaultTableModel) tableSetup.getModel();
		
		try {
			List<Setup> setups = setupController.viewSetup();
			
			for (Setup setup : setups) {
                Object[] row = { setup.getSetupID(),setup.getFrontWing(),setup.getRearWing(),setup.getGearRatio(),setup.getSuspension()};
                model.addRow(row);
            }
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnSelect = new JButton("Select Setup");
		btnSelect.setBackground(new Color(255, 255, 255));
		btnSelect.setForeground(new Color(0, 0, 0));
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelect.setEnabled(false); // Initially disable the button

		// Add a ListSelectionListener to the table
		tableSetup.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        // Enable/disable the button based on the selection
		    	btnSelect.setEnabled(tableSetup.getSelectedRowCount() == 1);
		    }
		});
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(timingID == 0)
					{
						if(choice == 0)
						{
							int setupID = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 0);
							addTiming frame = new addTiming(lapNumber, time, speed, setupID, circuitID);
							frame.setVisible(true);
						}
						else
						{
							int setupID = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 0);
							TimingPage frame = new TimingPage(circuitID,setupID);
							frame.setVisible(true);
						}
					}
					else
					{
						int setupID = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 0);
						updateTiming frame = new updateTiming( lapNumber, time, speed, setupID, circuitID, timingID);
						frame.setVisible(true);
					}
					dispose();
					
			}
		});
		btnSelect.setBounds(548, 394, 121, 36);
		contentPane.add(btnSelect);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(timingID == 0)
				{
					if(choice == 0)
					{
						addTiming frame = new addTiming(lapNumber, time, speed, setupID, circuitID);
						frame.setVisible(true);
					}
					else
					{
						TimingPage frame = new TimingPage(circuitID,setupID);
						frame.setVisible(true);
					}
				
					
				}
				else
				{
					updateTiming frame = new updateTiming( lapNumber, time, speed, setupID, circuitID, timingID);
					frame.setVisible(true);
				}
				dispose();
				
			}
		});
		btnBack.setBounds(385, 394, 121, 36);
		contentPane.add(btnBack);
		
		JLabel lblChooseSetup = new JLabel("Please Choose The Setup");
		lblChooseSetup.setFont(new Font("The Rift Shop", Font.PLAIN, 25));
		lblChooseSetup.setVerticalAlignment(SwingConstants.TOP);
		lblChooseSetup.setBounds(409, 30, 239, 23);
		contentPane.add(lblChooseSetup);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 249, 462);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMain = new JLabel("Car Setup");
		lblMain.setForeground(new Color(255, 255, 255));
		lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 41));
		lblMain.setBounds(52, 119, 143, 46);
		panel.add(lblMain);
		
		JLabel lblCarImage = new JLabel("");
		lblCarImage.setIcon(new ImageIcon(chooseSetup.class.getResource("/images/360_F_528780055_eQ2UYVU0YYh9bFJTLk9q2orIAkbCkSUg-removebg-preview (2) (1).png")));
		lblCarImage.setBounds(25, 361, 193, 101);
		panel.add(lblCarImage);
		
		JLabel lblSetupImage = new JLabel("");
		lblSetupImage.setIcon(new ImageIcon(chooseSetup.class.getResource("/images/car-settings-icon-vector-29904544-removebg-preview (1).png")));
		lblSetupImage.setBounds(25, 29, 173, 82);
		panel.add(lblSetupImage);
	}
}
