package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
//import javax.swing.JSpinner;
import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
//import javax.swing.SwingConstants;
//import controller.UserController;
import controller.SetupController;
//import model.User;
import model.Setup;

public class addSetup extends JFrame {

	int suspension=50;
	int front=15;
	int rear=15;
	int gear=50;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static String username;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int circuitID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addSetup frame = new addSetup(circuitID);
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
	
	public addSetup(int circuitID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBackground(new Color(192, 192, 192));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetupPage frame = new SetupPage(circuitID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(613, 419, 130, 28);
		contentPane.add(btnBack);
		
		JLabel lblSuspension = new JLabel("SUSPENSION");
		lblSuspension.setFont(new Font("The Rift Shop", Font.PLAIN, 22));
		lblSuspension.setForeground(new Color(255, 255, 255));
		lblSuspension.setBounds(442, 66, 146, 33);
		contentPane.add(lblSuspension);
		
		JTextPane txtValueSuspension = new JTextPane();
		txtValueSuspension.setText("0");
		txtValueSuspension.setBounds(699, 120, 44, 19);
		contentPane.add(txtValueSuspension);
		
		JTextPane txtValueFrontWing = new JTextPane();
		txtValueFrontWing.setText("0");
		txtValueFrontWing.setBounds(699, 201, 44, 19);
		contentPane.add(txtValueFrontWing);
		
		JTextPane txtValueRearWing = new JTextPane();
		txtValueRearWing.setText("0");
		txtValueRearWing.setBounds(699, 279, 44, 19);
		contentPane.add(txtValueRearWing);
		
		JTextPane txtValueGearRatio = new JTextPane();
		txtValueGearRatio.setText("0");
		txtValueGearRatio.setBounds(699, 358, 44, 19);
		contentPane.add(txtValueGearRatio);
		
		JSlider sliderSuspension = new JSlider();
		sliderSuspension.setSnapToTicks(true);
		sliderSuspension.setPaintTicks(true);
		sliderSuspension.setPaintLabels(true);
		sliderSuspension.setBorder(null);
		sliderSuspension.setValue(50);
		txtValueSuspension.setText(Integer.toString(suspension)+"%");
		sliderSuspension.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
				
				 suspension=sliderSuspension.getValue();
				txtValueSuspension.setText(Integer.toString(  suspension));
			}
		});
		sliderSuspension.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				//increases or decreases values as mouse is dragged
			
				
				 suspension=sliderSuspension.getValue();
				txtValueSuspension.setText(Integer.toString(suspension)+"%");
				
			}
		});
		sliderSuspension.setBounds(296, 106, 383, 33);
		sliderSuspension.setBackground(new Color(51, 51, 51));
		contentPane.add(sliderSuspension);
		
		JSlider sliderFrontWing = new JSlider();
		sliderFrontWing.setMaximum(20);
		sliderFrontWing.setMinimum(10);
		sliderFrontWing.setPaintTicks(true);
		sliderFrontWing.setValue(15);
		txtValueFrontWing.setText(Integer.toString(front));
		
		sliderFrontWing.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				
				front=sliderFrontWing.getValue();
				txtValueFrontWing.setText(Integer.toString(front));
				
			}
		});
		sliderFrontWing.setBackground(new Color(51, 51, 51));
		sliderFrontWing.setBounds(296, 193, 383, 33);
		contentPane.add(sliderFrontWing);
		
		JSlider sliderRearWing = new JSlider();
		sliderRearWing.setMaximum(20);
		sliderRearWing.setMinimum(10);
		sliderRearWing.setValue(15);
		txtValueRearWing.setText(Integer.toString(rear));
		
		sliderRearWing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				rear=sliderRearWing.getValue();
				txtValueRearWing.setText(Integer.toString(rear));
			}
		});
		sliderRearWing.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) 
			{
			
				rear=sliderRearWing.getValue();
				txtValueRearWing.setText(Integer.toString(rear));
				
			}
		});
		sliderRearWing.setPaintTicks(true);
		sliderRearWing.setBackground(new Color(51, 51, 51));
		sliderRearWing.setBounds(296, 265, 383, 33);
		contentPane.add(sliderRearWing);
		
		JSlider sliderGearRatio = new JSlider();
		sliderGearRatio.setValue(50);
		txtValueGearRatio.setText(Integer.toString(gear)+"%");
		sliderGearRatio.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				
				 gear=sliderGearRatio.getValue();
				txtValueGearRatio.setText(Integer.toString( gear)+"%");
				return;
				
			}
		});
		sliderGearRatio.setPaintTicks(true);
		sliderGearRatio.setBackground(new Color(51, 51, 51));
		sliderGearRatio.setBounds(296, 344, 383, 33);
		contentPane.add(sliderGearRatio);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			
				 Setup setup = new Setup();
			        setup.setSuspension( suspension);
			        setup.setFrontWing(front);
			        setup.setRearWing(rear);
			        setup.setGearRatio(gear);
			    try 
			    {	
			        	
			    	SetupController setupController = new SetupController();
			        setupController.insertSetup(setup);
			        JOptionPane.showMessageDialog(contentPane, "Car setup added successfully!");
			        SetupPage frame = new SetupPage(circuitID);
					frame.setVisible(true);
					dispose();
			           
			    } 
			    catch (NumberFormatException | ClassNotFoundException | SQLException e1)
			    {
			        e1.printStackTrace();
			        
			    }
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(305, 419, 130, 28);
		contentPane.add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(0, 0, 0));
		btnReset.setBackground(new Color(192, 192, 192));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String number="15";
				sliderSuspension.setValue(50);
				txtValueSuspension.setText(number+"%");
				sliderFrontWing.setValue(15);
				txtValueFrontWing.setText(number);
				sliderRearWing.setValue(15);
				txtValueRearWing.setText(number);
				sliderGearRatio.setValue(50);
				txtValueGearRatio.setText((number)+"%");
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReset.setBounds(458, 419, 130, 28);
		contentPane.add(btnReset);
		
		JLabel lblFrontWing = new JLabel("FRONT WING ANGLE");
		lblFrontWing.setForeground(Color.WHITE);
		lblFrontWing.setFont(new Font("The Rift Shop", Font.PLAIN, 22));
		lblFrontWing.setBounds(426, 149, 175, 33);
		contentPane.add(lblFrontWing);
		
		JLabel lblRearWing = new JLabel("REAR WING ANGLE");
		lblRearWing.setForeground(Color.WHITE);
		lblRearWing.setFont(new Font("The Rift Shop", Font.PLAIN, 22));
		lblRearWing.setBounds(426, 230, 175, 33);
		contentPane.add(lblRearWing);
		
		JLabel lblGearRatios = new JLabel("GEAR RATIOS");
		lblGearRatios.setForeground(Color.WHITE);
		lblGearRatios.setFont(new Font("The Rift Shop", Font.PLAIN, 22));
		lblGearRatios.setBounds(442, 309, 175, 33);
		contentPane.add(lblGearRatios);
		
		JLabel lblSuspension0 = new JLabel("0");
		lblSuspension0.setForeground(Color.WHITE);
		lblSuspension0.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSuspension0.setBounds(282, 77, 24, 33);
		contentPane.add(lblSuspension0);
		
		JLabel lblSuspension100 = new JLabel("100");
		lblSuspension100.setForeground(Color.WHITE);
		lblSuspension100.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSuspension100.setBounds(673, 77, 32, 33);
		contentPane.add(lblSuspension100);
		
		JLabel lblFrontWing10 = new JLabel("10");
		lblFrontWing10.setForeground(Color.WHITE);
		lblFrontWing10.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFrontWing10.setBounds(282, 158, 24, 33);
		contentPane.add(lblFrontWing10);
		
		JLabel lblFrontWing20 = new JLabel("20");
		lblFrontWing20.setForeground(Color.WHITE);
		lblFrontWing20.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFrontWing20.setBounds(673, 158, 32, 33);
		contentPane.add(lblFrontWing20);
		
		JLabel lblRearWing10 = new JLabel("10");
		lblRearWing10.setForeground(Color.WHITE);
		lblRearWing10.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRearWing10.setBounds(282, 230, 24, 33);
		contentPane.add(lblRearWing10);
		
		JLabel lblRearWing20 = new JLabel("20");
		lblRearWing20.setForeground(Color.WHITE);
		lblRearWing20.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRearWing20.setBounds(673, 236, 32, 33);
		contentPane.add(lblRearWing20);
		
		JLabel lblGearRatioShort = new JLabel("Short");
		lblGearRatioShort.setForeground(Color.WHITE);
		lblGearRatioShort.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGearRatioShort.setBounds(282, 315, 44, 33);
		contentPane.add(lblGearRatioShort);
		
		JLabel lblGearRatioLong = new JLabel("Long");
		lblGearRatioLong.setForeground(Color.WHITE);
		lblGearRatioLong.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGearRatioLong.setBounds(661, 315, 44, 33);
		contentPane.add(lblGearRatioLong);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 241, 462);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMain2 = new JLabel("CAR SET-UP");
		lblMain2.setBounds(41, 135, 146, 44);
		panel.add(lblMain2);
		lblMain2.setFont(new Font("The Rift Shop", Font.PLAIN, 35));
		
		JLabel lblMain = new JLabel("NEW ");
		lblMain.setBounds(78, 91, 175, 33);
		panel.add(lblMain);
		lblMain.setForeground(Color.WHITE);
		lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 35));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(addSetup.class.getResource("/images/360_F_528780055_eQ2UYVU0YYh9bFJTLk9q2orIAkbCkSUg-removebg-preview (2) (1).png")));
		lblNewLabel.setBounds(10, 190, 194, 141);
		panel.add(lblNewLabel);
		
		
	}
}
