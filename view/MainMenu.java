package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Button;
import java.awt.Panel;
import javax.swing.ImageIcon;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int circuitID) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu(circuitID);
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
	public MainMenu(int circuitID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(32, 178, 170));
		panel_2.setBounds(0, 426, 840, 36);
		contentPane.add(panel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 840, 36);
		contentPane.add(panel);
		
		JLabel selectIDLabel = new JLabel("Selected Circuit ID : ");
		selectIDLabel.setFont(new Font("The Rift Shop", Font.PLAIN, 25));
		selectIDLabel.setBounds(435, 176, 205, 25);
		contentPane.add(selectIDLabel);
		
		JLabel circuitIDLabel = new JLabel(Integer.toString(circuitID));
		circuitIDLabel.setFont(new Font("The Rift Shop", Font.PLAIN, 25));
		circuitIDLabel.setBounds(653, 178, 59, 20);
		contentPane.add(circuitIDLabel);
		
		Button timingsLabel = new Button("Timings");
		timingsLabel.setBackground(new Color(192, 192, 192));
		timingsLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		timingsLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimingPage frame = new TimingPage(circuitID,0);
				frame.setVisible(true);
				dispose();
			}
		});
		timingsLabel.setBounds(505, 230, 135, 36);
		contentPane.add(timingsLabel);
		
		Button setupLabel = new Button("Car Setup");
		setupLabel.setBackground(new Color(192, 192, 192));
		setupLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		setupLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetupPage frame = new SetupPage(circuitID);
				frame.setVisible(true);
				dispose();
			}
		});
		setupLabel.setBounds(505, 291, 135, 36);
		contentPane.add(setupLabel);
		
		Button btnBack = new Button("Back");
		btnBack.setBackground(new Color(192, 192, 192));
		btnBack.setFont(new Font("Dialog", Font.BOLD, 14));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CircuitPage frame = new CircuitPage();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(505, 353, 135, 36);
		contentPane.add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(0, 0, 284, 462);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMain = new JLabel("WELCOME TO ");
		lblMain.setForeground(new Color(255, 255, 255));
		lblMain.setBounds(26, 204, 248, 79);
		panel_1.add(lblMain);
		lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 50));
		
		JLabel lblMain2 = new JLabel("MAIN MENU ");
		lblMain2.setForeground(new Color(255, 255, 255));
		lblMain2.setBounds(55, 269, 219, 47);
		panel_1.add(lblMain2);
		lblMain2.setFont(new Font("The Rift Shop", Font.PLAIN, 45));
		
		JLabel lblPetronasImage = new JLabel("");
		lblPetronasImage.setIcon(new ImageIcon(MainMenu.class.getResource("/images/kisspng-petronas-dagangan-berhad-logo-organization-company-conference-vector-5ad86d8ea7ff61.1371625815241332626881-removebg-preview (1).png")));
		lblPetronasImage.setBounds(88, 93, 144, 79);
		panel_1.add(lblPetronasImage);
		
		JLabel lblF1Image = new JLabel("");
		lblF1Image.setIcon(new ImageIcon(MainMenu.class.getResource("/images/62243d43bbd692ff6e3d9a5ca70c3fbb-removebg-preview (5).png")));
		lblF1Image.setBounds(470, 57, 218, 59);
		contentPane.add(lblF1Image);
	}
}
