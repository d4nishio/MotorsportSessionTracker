package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controller.CircuitController;
import controller.TimingController;

import model.Timing;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;


public class TimingPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableTiming;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int circuitID,int setupID) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimingPage frame = new TimingPage(circuitID,setupID);
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
	public TimingPage(int circuitID,int setupID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel selectIDLabel = new JLabel("Selected Circuit ID : ");
		selectIDLabel.setForeground(new Color(255, 255, 255));
		selectIDLabel.setFont(new Font("The Rift Shop", Font.PLAIN, 21));
		selectIDLabel.setBounds(192, 31, 190, 29);
		contentPane.add(selectIDLabel);
		
		JLabel circuitIDLabel = new JLabel(Integer.toString(circuitID));
		circuitIDLabel.setForeground(new Color(255, 255, 255));
		circuitIDLabel.setFont(new Font("The Rift Shop", Font.PLAIN, 21));
		circuitIDLabel.setBounds(362, 31, 41, 29);
		contentPane.add(circuitIDLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 80, 521, 281);
		contentPane.add(scrollPane);
		
		tableTiming = new JTable();
		tableTiming.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Lap Number", "Time", "Gap", "Avg. Speed", "Car Setup","Timing ID"
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
		}	
		);
		tableTiming.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableTiming.getColumnModel().getColumn(0).setMinWidth(100);
		tableTiming.getColumnModel().getColumn(0).setMaxWidth(100);
		tableTiming.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableTiming.getColumnModel().getColumn(1).setMinWidth(120);
		tableTiming.getColumnModel().getColumn(1).setMaxWidth(120);
		tableTiming.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableTiming.getColumnModel().getColumn(2).setMinWidth(100);
		tableTiming.getColumnModel().getColumn(2).setMaxWidth(100);
		tableTiming.getColumnModel().getColumn(3).setPreferredWidth(110);
		tableTiming.getColumnModel().getColumn(3).setMinWidth(110);
		tableTiming.getColumnModel().getColumn(3).setMaxWidth(110);
		tableTiming.getColumnModel().getColumn(4).setPreferredWidth(90);
		tableTiming.getColumnModel().getColumn(4).setMinWidth(90);
		tableTiming.getColumnModel().getColumn(4).setMaxWidth(90);
		tableTiming.getColumnModel().getColumn(5).setPreferredWidth(0);
		tableTiming.getColumnModel().getColumn(5).setMinWidth(0);
		tableTiming.getColumnModel().getColumn(5).setMaxWidth(0);
		
		scrollPane.setViewportView(tableTiming);
		
		TimingController timingController = new TimingController();
		DefaultTableModel model = (DefaultTableModel) tableTiming.getModel();
		
		try {
			List<Timing> timings = timingController.viewTiming(circuitID,0,setupID);
			
			for (Timing timing : timings) {
                Object[] row = {timing.getLapNumber(),timing.getTime(),timing.getGap(),timing.getAverageSpeed(),timing.getSetupID(),timing.getTimingID()};
                model.addRow(row);
            }
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnSortFastest = new JButton("Sort By Fastest Lap");
		btnSortFastest.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSortFastest.setBackground(new Color(128, 128, 128));
		btnSortFastest.setForeground(Color.WHITE);
		btnSortFastest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setRowCount(0);
				
				TimingController timingController = new TimingController();
				DefaultTableModel model = (DefaultTableModel) tableTiming.getModel();
				
				try {
					List<Timing> timings = timingController.viewTiming(circuitID,1,setupID);
					
					for (Timing timing : timings) {
		                Object[] row = {timing.getLapNumber(),timing.getTime(),timing.getGap(),timing.getAverageSpeed(),timing.getSetupID(),timing.getTimingID()};
		                model.addRow(row);
		            }
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSortFastest.setBounds(46, 397, 148, 28);
		contentPane.add(btnSortFastest);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setEnabled(false); // Initially disable the button

		// Add a ListSelectionListener to the table
		tableTiming.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        // Enable/disable the button based on the selection
		    	btnUpdate.setEnabled(tableTiming.getSelectedRowCount() == 1);
		    }
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
					int lapNumber = (int) tableTiming.getValueAt(tableTiming.getSelectedRow(), 0);
					String time = (String) tableTiming.getValueAt(tableTiming.getSelectedRow(), 1);
					double speed = (double) tableTiming.getValueAt(tableTiming.getSelectedRow(), 3);
					int setupID = (int) tableTiming.getValueAt(tableTiming.getSelectedRow(), 4);
					int timingID = (int) tableTiming.getValueAt(tableTiming.getSelectedRow(), 5);
					
					String strLapNumber = Integer.toString(lapNumber);
					String strSpeed = Double.toString(speed);
					
					
					updateTiming frame = new updateTiming(strLapNumber, time, strSpeed, setupID, circuitID, timingID);
					frame.setVisible(true);
					dispose();
				
			}
		});
		btnUpdate.setBounds(551, 219, 89, 28);
		contentPane.add(btnUpdate);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String lapNumber="", time="", speed="";
				int setupID = 0;
				
				addTiming frame = new addTiming(lapNumber, time, speed, setupID, circuitID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnAdd.setBounds(551, 160, 89, 28);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setEnabled(false); // Initially disable the button

		// Add a ListSelectionListener to the table
		tableTiming.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        // Enable/disable the button based on the selection
		        btnDelete.setEnabled(tableTiming.getSelectedRowCount() == 1);
		    }
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				int timingID = (int) tableTiming.getValueAt(tableTiming.getSelectedRow(), 5);
				
				Timing timing = new Timing();
				timing.setTimingID(timingID);
				
				TimingController timingController = new TimingController();
				try {
					timingController.deleteTiming(timing);
					JOptionPane.showMessageDialog(btnUpdate,"Delete Successful!" );
					TimingPage frame = new TimingPage(circuitID,0);
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
			}
		});
		btnDelete.setBounds(551, 280, 89, 28);
		contentPane.add(btnDelete);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setForeground(Color.BLACK);
		btnBack.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainMenu frame = new MainMenu(circuitID);
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(551, 341, 89, 28);
		contentPane.add(btnBack);
		
		JButton btnSortByLap = new JButton("Sort By Lap ");
		btnSortByLap.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSortByLap.setForeground(Color.WHITE);
		btnSortByLap.setBackground(new Color(128, 128, 128));
		btnSortByLap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setRowCount(0);
				
				TimingController timingController = new TimingController();
				DefaultTableModel model = (DefaultTableModel) tableTiming.getModel();
				
				try {
					List<Timing> timings = timingController.viewTiming(circuitID,0,setupID);
					
					for (Timing timing : timings) {
		                Object[] row = {timing.getLapNumber(),timing.getTime(),timing.getGap(),timing.getAverageSpeed(),timing.getSetupID(),timing.getTimingID()};
		                model.addRow(row);
		            }
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSortByLap.setBounds(371, 397, 148, 28);
		contentPane.add(btnSortByLap);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(650, 0, 190, 462);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(Color.BLACK);
		btnReset.setBackground(Color.WHITE);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReset.setBounds(25, 142, 138, 43);
		panel.add(btnReset);
		
		JButton btnCarSetup = new JButton("Choose Car Setup");
		btnCarSetup.setForeground(Color.BLACK);
		btnCarSetup.setBackground(Color.WHITE);
		btnCarSetup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCarSetup.setBounds(25, 88, 138, 43);
		panel.add(btnCarSetup);
		
		JLabel lblSetupID = new JLabel(Integer.toString(setupID));
		lblSetupID.setForeground(Color.BLACK);
		lblSetupID.setFont(new Font("The Rift Shop", Font.PLAIN, 20));
		lblSetupID.setBounds(134, 39, 46, 24);
		panel.add(lblSetupID);
		
		JLabel lblSetup = new JLabel("Setup ID : ");
		lblSetup.setForeground(Color.BLACK);
		lblSetup.setBounds(42, 37, 83, 28);
		panel.add(lblSetup);
		lblSetup.setFont(new Font("The Rift Shop", Font.PLAIN, 20));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 246, 227, 216);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGenerate = new JLabel("Generate Report");
		lblGenerate.setFont(new Font("The Rift Shop", Font.PLAIN, 20));
		lblGenerate.setBounds(34, 24, 130, 24);
		panel_1.add(lblGenerate);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setForeground(Color.BLACK);
		btnGenerate.setBackground(Color.GRAY);
		btnGenerate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CircuitController circuitController = new CircuitController();
				String circuitName="";
				try {
					circuitName = circuitController.selectCircuit(circuitID);
				} catch (ClassNotFoundException | SQLException e1) {
				
					e1.printStackTrace();
				}
				
				try {
					
					String outputPath = "C:\\PDF\\reports.pdf";
					Document document = new Document();
					
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
					
					document.open();
					
					Image image = Image.getInstance("C:\\PDF\\PetronasLogo.png");
		            // Set alignment to center
		            image.setAlignment(Image.MIDDLE | Image.TEXTWRAP);
		            // Add the image to the document
		            document.add(image);
		            
		            PdfContentByte contentByte = writer.getDirectContent();
		            contentByte.setLineWidth(1f); // Set line width
		            contentByte.setRGBColorStroke(0, 0, 0); // Set black color
		            contentByte.moveTo(document.left(), 730);
		            contentByte.lineTo(document.right(), 730);
		            contentByte.stroke();
		            
		            
					
		            
		            contentByte.setLineWidth(1f); // Set line width
		            contentByte.setRGBColorStroke(0, 0, 0); // Set black color
		            contentByte.moveTo(document.left(), document.bottom());
		            contentByte.lineTo(document.right(), document.bottom());
		            contentByte.stroke();
		            
				    document.add(new Paragraph(" "));
				    document.add(new Phrase("Timings Report"));
				    document.add(new Paragraph(" "));
				    document.add(new Paragraph(" "));
				    document.add(new Paragraph("Circuit : " + circuitName));
				    document.add(new Paragraph(" "));
				    document.add(new Paragraph(" "));
			
					PdfPTable table = new PdfPTable(5);
					table.addCell(new PdfPCell(new Phrase("Lap Number")));
					table.addCell(new PdfPCell(new Phrase("Time")));
					table.addCell(new PdfPCell(new Phrase("Gap")));
					table.addCell(new PdfPCell(new Phrase("Avg. Speed")));
					table.addCell(new PdfPCell(new Phrase("Car Setup")));
					
					
					DefaultTableModel model = (DefaultTableModel) tableTiming.getModel();
		            int rowCount = model.getRowCount();
		            for (int i = 0; i < rowCount; i++) {
		                // Assuming the column indices for lapNumber, time, gap, avgSpeed, and carSetup
		                int lapNumber = (int) model.getValueAt(i, 0);
		                String time = (String) model.getValueAt(i, 1);
		                String gap = (String) model.getValueAt(i, 2);
		                double avgSpeed = (double) model.getValueAt(i, 3);
		                int carSetup = (int) model.getValueAt(i, 4);

		                // Add data to PdfPTable
		                table.addCell(String.valueOf(lapNumber));
		                table.addCell(time);
		                table.addCell(gap);
		                table.addCell(String.valueOf(avgSpeed));
		                table.addCell(String.valueOf(carSetup));
		            }

		            // Add PdfPTable to the document
		            document.add(table);

					document.close();
					
					JOptionPane.showMessageDialog(btnUpdate,"Generated Successfully !" );
					openPdfFile(outputPath);
				} catch (Exception e1) {
					
					System.err.println(e1);
				}
			}
		});
		btnGenerate.setBounds(26, 78, 138, 43);
		panel_1.add(btnGenerate);
		btnCarSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chooseSetup frame = new chooseSetup("", "", "", circuitID, 0,1);
				frame.setVisible(true);
				dispose();
			}
		});
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(setupID == 0)
				{
					JOptionPane.showMessageDialog(btnUpdate,"Setup ID Already Resetted !" );
				}
				else
				{
					TimingPage frame = new TimingPage(circuitID,0);
					frame.setVisible(true);
					dispose();
				}
				
			
			}
		});
	}
	
	private static void openPdfFile(String filePath) {
        try {
            File file = new File(filePath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("Desktop not supported. Open the file manually: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
