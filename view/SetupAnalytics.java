package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Setup;
import javax.swing.*;
import java.awt.Component;
import java.awt.Color;

public class SetupAnalytics extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea analysisTextArea;

	/**
	 * Launch the application.
	 */

    public static void main(String[] args, int circuitID, Setup setup) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					SwingUtilities.invokeLater(() -> {

			            // Create and display the graph
			            SetupAnalytics example = null;
						try {
							example = new SetupAnalytics(circuitID, setup);
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            example.setSize(400, 300);
			            example.setLocationRelativeTo(null);
			            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			            example.setVisible(true);
			        });
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 * @param circuitID 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public SetupAnalytics(int circuitID, Setup setup) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(856, 501);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(32, 178, 170));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Use BoxLayout

        JLabel lblMain = new JLabel("Setup Analytics");
        lblMain.setFont(new Font("The Rift Shop", Font.PLAIN, 21));
        lblMain.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
        contentPanel.add(lblMain);
        
        // Calculate oversteer
        double oversteer = calculateOversteer(setup.getSuspension(), setup.getFrontWing(), setup.getRearWing(), setup.getGearRatio());

        // Calculate cornering
        double cornering = calculateCornering(setup.getFrontWing(), setup.getRearWing());

        // Calculate traction
        double traction = calculateTraction(setup.getSuspension(), setup.getGearRatio());

        // Calculate straight-line performance
        double straightLinePerformance = calculateStraightLinePerformance(setup.getFrontWing(), setup.getRearWing(), setup.getGearRatio());
        
        // Generate graph
        CategoryDataset dataset = createDataset(oversteer, cornering, traction, straightLinePerformance);
        JFreeChart barChart = createBarChart(dataset);
        ChartPanel chartPanel = new ChartPanel(barChart);
        contentPanel.add(chartPanel);
        
        // Initialize analysisTextArea
        analysisTextArea = new JTextArea();
        analysisTextArea.setEditable(false);
        analysisTextArea.setLineWrap(true);
        analysisTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(analysisTextArea);
        contentPanel.add(scrollPane);

        // Add some space between the components
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnBack = new JButton("BACK");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetupPage frame;
                frame = new SetupPage(circuitID);
				frame.setVisible(true);
				dispose();
            }
        });
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        contentPanel.add(btnBack);
        
        String pattern = "0.00"; // Two decimal places
        DecimalFormat decimal = new DecimalFormat(pattern);
        
        // Print the results
        String analysisText = ("Oversteer: " + decimal.format(oversteer) +
        				"\nCornering: " + decimal.format(cornering) +
        				"\nTraction: " + decimal.format(traction) +
        				"\nStraight-line Performance: " + decimal.format(straightLinePerformance));
        
        analysisTextArea.setText(analysisText);

        // Ensure proper visibility
        analysisTextArea.revalidate();
        analysisTextArea.repaint();
	}
	
    private JFreeChart createBarChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "",      // chart title
                "Bias",               // category axis label
                "Performance",                  // value axis label
                dataset);                 // data
    }
    
    // formula for calculating oversteer
    private static double calculateOversteer(int suspension, int frontWingAngle, int rearWingAngle, int gearRatio) {
        return suspension * 0.2 + frontWingAngle * 0.1 - rearWingAngle * 0.1 + gearRatio * 0.05;
    }

    // formula for calculating cornering
    private static double calculateCornering(int frontWingAngle, int rearWingAngle) {
        return frontWingAngle * 0.2 + rearWingAngle * 0.3;
    }

    // formula for calculating traction
    private static double calculateTraction(int suspension, int gearRatio) {
        return suspension * 0.1 + gearRatio * 0.2;
    }

    // formula for calculating straight-line performance
    private static double calculateStraightLinePerformance(int frontWingAngle, int rearWingAngle, int gearRatio) {
        return frontWingAngle * 0.1 + rearWingAngle * 0.1 + gearRatio * 0.3;
    }
    
    private CategoryDataset createDataset(double oversteer, double cornering, double traction, double straight) throws ClassNotFoundException, SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
		dataset.addValue(oversteer, "Oversteer", "Oversteer");
		dataset.addValue(cornering, "Cornering", "Cornering");
		dataset.addValue(traction, "Traction", "Traction");
		dataset.addValue(straight, "Straight-Line", "Straight-Line");		

        return dataset;
    }
}
