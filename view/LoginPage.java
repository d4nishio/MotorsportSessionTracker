package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.TextField;
import javax.swing.JPasswordField;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPanel panel;
	private JLabel lblMain;
	private JLabel lblMain2;
	private JLabel lblMain3;
	private JLabel lblPetronasImage;
	private JPasswordField txtPassword;
	private JLabel lblMain4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage loginFrame = new LoginPage();
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameDisplay = new JLabel("USERNAME : ");
		usernameDisplay.setFont(new Font("Tahoma", Font.BOLD, 11));
		usernameDisplay.setBounds(409, 165, 95, 26);
		contentPane.add(usernameDisplay);
		
		JLabel passwordDisplay = new JLabel("PASSWORD : ");
		passwordDisplay.setFont(new Font("Tahoma", Font.BOLD, 11));
		passwordDisplay.setBounds(409, 202, 85, 21);
		contentPane.add(passwordDisplay);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtUsername.setBackground(Color.LIGHT_GRAY);
		txtUsername.setForeground(Color.BLACK);
		txtUsername.setBounds(490, 168, 123, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		
		panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		panel.setBounds(0, 0, 300, 462);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblMain = new JLabel("Petronas F1 ");
		lblMain.setForeground(new Color(32, 178, 170));
		lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 38));
		lblMain.setBounds(23, 71, 188, 44);
		panel.add(lblMain);
		
		lblMain2 = new JLabel("Motorsport ");
		lblMain2.setBackground(Color.WHITE);
		lblMain2.setForeground(Color.WHITE);
		lblMain2.setFont(new Font("The Rift Shop", Font.PLAIN, 38));
		lblMain2.setBounds(23, 110, 166, 44);
		panel.add(lblMain2);
		
		lblMain3 = new JLabel("PRACTICE SESSION");
		lblMain3.setForeground(Color.WHITE);
		lblMain3.setBackground(Color.WHITE);
		lblMain3.setFont(new Font("The Rift Shop", Font.PLAIN, 38));
		lblMain3.setBounds(23, 252, 267, 50);
		panel.add(lblMain3);
		
		lblMain4 = new JLabel("TRACKER");
		lblMain4.setForeground(new Color(255, 255, 255));
		lblMain4.setFont(new Font("The Rift Shop", Font.PLAIN, 38));
		lblMain4.setBounds(78, 299, 128, 44);
		panel.add(lblMain4);
		
		lblPetronasImage = new JLabel("");
		lblPetronasImage.setIcon(new ImageIcon(LoginPage.class.getResource("/images/kisspng-petronas-dagangan-berhad-logo-organization-company-conference-vector-5ad86d8ea7ff61.1371625815241332626881-removebg-preview (1).png")));
		lblPetronasImage.setBounds(185, 71, 154, 83);
		panel.add(lblPetronasImage);
		
		
		Button btnLogin = new Button("Login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 13));
		btnLogin.setBackground(new Color(105, 105, 105));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txtUsername.getText();
				String password = new String (txtPassword.getText()).trim();
				
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				
				UserController userController = new UserController();
				try {
					boolean valid = userController.doLogin(user);
					
					if(valid)
					{
						JOptionPane.showMessageDialog(btnLogin,"Login Success!" );
						CircuitPage frame = new CircuitPage();
						frame.setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(btnLogin,"Login Failed, Please Enter the Correct Credentials" );
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(504, 256, 102, 38);
		contentPane.add(btnLogin);
		
		JLabel lblLoginMain = new JLabel("LOGIN PAGE");
		lblLoginMain.setFont(new Font("The Rift Shop", Font.PLAIN, 44));
		lblLoginMain.setBounds(465, 42, 276, 79);
		contentPane.add(lblLoginMain);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPassword.setBackground(Color.LIGHT_GRAY);
		txtPassword.setBounds(490, 202, 123, 20);
		contentPane.add(txtPassword);
	}
}
