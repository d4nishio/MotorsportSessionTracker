package view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.CircuitController;
import model.Circuit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class CircuitPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCircuit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CircuitPage frame = new CircuitPage();
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
	public CircuitPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(260, 113, 508, 184);
		contentPane.add(scrollPane);
		
		tableCircuit = new JTable();
		tableCircuit.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Circuit ID", "Circuit Name"
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
		tableCircuit.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableCircuit.getColumnModel().getColumn(0).setMinWidth(100);
		tableCircuit.getColumnModel().getColumn(0).setMaxWidth(100);
		scrollPane.setViewportView(tableCircuit);
		
		CircuitController circuitController = new CircuitController();
		DefaultTableModel model = (DefaultTableModel) tableCircuit.getModel();
		
		try {
			List<Circuit> circuits = circuitController.viewCircuit();
			
			for (Circuit circuit : circuits) {
                Object[] row = { circuit.getCircuitID(), circuit.getName() };
                model.addRow(row);
            }
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblChooseCircuit = new JLabel("Choose a circuit");
		lblChooseCircuit.setForeground(new Color(0, 0, 0));
		lblChooseCircuit.setBackground(new Color(0, 0, 0));
		lblChooseCircuit.setFont(new Font("The Rift Shop", Font.PLAIN, 25));
		lblChooseCircuit.setBounds(435, 55, 207, 28);
		contentPane.add(lblChooseCircuit);
		
		JButton btnNext = new JButton("Next");
		btnNext.setForeground(new Color(0, 0, 0));
		btnNext.setBackground(new Color(255, 255, 255));
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnNext.setEnabled(false); // Initially disable the button

		// Add a ListSelectionListener to the table
		tableCircuit.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        // Enable/disable the button based on the selection
		    	btnNext.setEnabled(tableCircuit.getSelectedRowCount() == 1);
		    }
		});
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int circuitID = (int) tableCircuit.getValueAt(tableCircuit.getSelectedRow(), 0);
				MainMenu frame = new MainMenu(circuitID);
				frame.setVisible(true);
				dispose();
					
			}
		});
		btnNext.setBounds(546, 363, 110, 28);
		contentPane.add(btnNext);
		
		JButton btnLogout = new JButton("Log-out");
		btnLogout.setForeground(new Color(0, 0, 0));
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage loginFrame = new LoginPage();
				loginFrame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(367, 363, 110, 28);
		contentPane.add(btnLogout);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 224, 462);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lvlCircuitLayoutImage = new JLabel("");
		lvlCircuitLayoutImage.setIcon(new ImageIcon(CircuitPage.class.getResource("/images/sepang-international-circuit-lamborghini-super-trofeo-lamborghini-huracan-mugello-circuit-others-removebg-preview (1).png")));
		lvlCircuitLayoutImage.setBounds(-27, 115, 241, 228);
		panel.add(lvlCircuitLayoutImage);
		
		JLabel lblMain = new JLabel("Circuit List");
		lblMain.setForeground(new Color(255, 255, 255));
		lblMain.setBounds(42, 62, 136, 59);
		panel.add(lblMain);
		lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 30));
	}
}
