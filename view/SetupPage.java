package view;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.SetupController;
import model.Setup;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

public class SetupPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableSetup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int circuitID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupPage frame = new SetupPage(circuitID);
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
	public SetupPage(int circuitID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(304, 92, 477, 285);
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
		
		JLabel lblMain = new JLabel("Car Setup Menu");
		lblMain.setForeground(new Color(32, 178, 170));
		lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 30));
		lblMain.setBounds(457, 30, 205, 39);
		contentPane.add(lblMain);
		
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

		// Add a ListSelectionListener to the table
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\synob\\Downloads\\Telegram Desktop\\ProjectOOP\\src\\images\\62243d43bbd692ff6e3d9a5ca70c3fbb-removebg-preview (5).png"));
		lblNewLabel.setBounds(668, 408, 184, 59);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 253, 467);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAdd = new JButton("New Car Setup");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(32, 178, 170));
		btnAdd.setBounds(54, 131, 130, 30);
		panel.add(btnAdd);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(SetupPage.class.getResource("/images/kisspng-petronas-dagangan-berhad-logo-organization-company-conference-vector-5ad86d8ea7ff61.1371625815241332626881-removebg-preview (1).png")));
		lblNewLabel_2.setBounds(76, 0, 100, 101);
		panel.add(lblNewLabel_2);
		
		JLabel lblCarImage = new JLabel("");
		lblCarImage.setIcon(new ImageIcon(SetupPage.class.getResource("/images/360_F_528780055_eQ2UYVU0YYh9bFJTLk9q2orIAkbCkSUg-removebg-preview (2) (1).png")));
		lblCarImage.setBounds(28, 379, 184, 64);
		panel.add(lblCarImage);
		
		JButton btnDelete = new JButton("Delete Setup");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(32, 178, 170));
		btnDelete.setBounds(54, 192, 130, 30);
		panel.add(btnDelete);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setEnabled(false); // Initially disable the button
		
		tableSetup.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        // Enable/disable the button based on the selection
		        btnDelete.setEnabled(tableSetup.getSelectedRowCount() == 1);
		    }
		});
		
		JButton btnAnalyze = new JButton("Analyze");
		btnAnalyze.setForeground(new Color(255, 255, 255));
		btnAnalyze.setBackground(new Color(32, 178, 170));
		btnAnalyze.setBounds(54, 254, 130, 30);
		panel.add(btnAnalyze);
		btnAnalyze.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAnalyze.setEnabled(false); // Initially disable the button
		
		tableSetup.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        // Enable/disable the button based on the selection
		    	btnAnalyze.setEnabled(tableSetup.getSelectedRowCount() == 1);
		    }
		});
		
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(32, 178, 170));
		btnBack.setBounds(54, 315, 130, 30);
		panel.add(btnBack);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainMenu frame = new MainMenu(circuitID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnAnalyze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Setup setup = new Setup();

					int setupID = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 0);
					int frontWing = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 1);
					int rearWing = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 2);
					int gearRatio = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 3);
					int suspension = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 4);
					setup.setSetupID(setupID);
					setup.setFrontWing(frontWing);
					setup.setRearWing(rearWing);
					setup.setGearRatio(gearRatio);
					setup.setSuspension(suspension);
					SetupAnalytics frame = null;
					try {
						frame = new SetupAnalytics(circuitID, setup);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.setVisible(true);
					dispose();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Setup setup = new Setup();
				SetupController setupController = new SetupController();
				
				int setupID = (int) tableSetup.getValueAt(tableSetup.getSelectedRow(), 0);
					setup.setSetupID(setupID);
					try {
						setupController.deleteSetup(setup);
						JOptionPane.showMessageDialog(btnDelete,"Delete successfull!" );
						SetupPage frame = new SetupPage(circuitID);
						frame.setVisible(true);
						dispose();
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addSetup frame = new addSetup(circuitID);
				frame.setVisible(true);
				dispose();
			}
		});
		
		
		
	}
}