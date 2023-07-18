import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Activity extends JFrame {
	private JLabel startL, stopL, myLabel;
	private JButton searchB;
	private JComboBox<String> stop_locations, start_locations;
	private SearchButtonHandler sbHandler;
	public String SelectedStopLoc, SelectedStartLoc;
	private ImageIcon bgimg;

	public Activity() {

		String[] all_locations = {};
		try (CSVReader reader = new CSVReader(new FileReader("Scrapper/Addresses.csv"))) {
			List<String[]> adjacencyMatrix = reader.readAll();
			all_locations = adjacencyMatrix.get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		bgimg = new ImageIcon("images/leg2(mod).jpg");
		myLabel = new JLabel(bgimg);

		// Instantiating labels
		startL = new JLabel("Start: ", SwingConstants.LEFT);
		startL.setFont(new Font("Serif", Font.BOLD, 16));
		startL.setForeground(Color.black);

		stopL = new JLabel("Stop: ", SwingConstants.LEFT);
		stopL.setFont(new Font("Serif", Font.BOLD, 16));
		stopL.setForeground(Color.black);

		// Initializing button
		// Initializing button
		searchB = new JButton("Search");
		searchB.setFont(new Font("Serif", Font.BOLD, 16));
		searchB.setBackground(new Color(0x0000FF));
		searchB.setForeground(Color.white);
		searchB.setFocusPainted(false);
		searchB.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0x999999), 2),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)));
		sbHandler = new SearchButtonHandler();
		searchB.addActionListener(sbHandler);

		// Initializing combobox
		start_locations = new JComboBox<String>(all_locations);
		stop_locations = new JComboBox<String>(all_locations);

		// Title of window
		setTitle("Main page");

		// Getting container
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		JPanel contentPane = new JPanel(new GridBagLayout());
		contentPane.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Add components to the content pane
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(startL, gbc);

		gbc.gridx = 1;
		contentPane.add(start_locations, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPane.add(stopL, gbc);

		gbc.gridx = 1;
		contentPane.add(stop_locations, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 20;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPane.add(searchB, gbc);

		myLabel.setLayout(new BorderLayout());
		myLabel.add(contentPane, BorderLayout.CENTER);

		pane.add(myLabel);

		// Size and display
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class SearchButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			SelectedStartLoc = (String) start_locations.getSelectedItem();
			SelectedStopLoc = (String) stop_locations.getSelectedItem();
			try {
				FileWriter myWriter = new FileWriter("data/LocationQuery.txt");
				myWriter.write(SelectedStartLoc + "\n");
				myWriter.write(SelectedStopLoc);
				myWriter.flush();
				myWriter.close();
			} catch (IOException k) {
			}

			String[] n = {""};
			try {
				ReadCSV.main(n);
			} catch (IOException | CsvException ioException) {
				ioException.printStackTrace();
			}

			new Routes();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Activity());
	}
}
