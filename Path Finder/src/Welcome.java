import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Welcome extends JFrame {
	private JLabel welcomeL, myLabel;
	private JButton welcomeB;
	private WelcomeButtonHandler wbHandler;
	private ImageIcon bgimg;

	public Welcome() {
		myLabel = new JLabel(bgimg);

		// Instantiating label
		welcomeL = new JLabel("Welcome  to campus path finder", SwingConstants.CENTER);
		welcomeL.setFont(new Font("Serif", Font.BOLD, 30));
		welcomeL.setForeground(Color.BLACK);
		welcomeL.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		// Instantiating Button
		// Instantiating Button
		welcomeB = new JButton("Search");
		welcomeB.setFont(new Font("Serif", Font.BOLD, 20));
		welcomeB.setBackground(Color.blue);
		welcomeB.setForeground(Color.white);
		welcomeB.setFocusPainted(false);
		welcomeB.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0x999999), 2),
				BorderFactory.createEmptyBorder(10, 20, 15, 20)));
		wbHandler = new WelcomeButtonHandler();
		welcomeB.addActionListener(wbHandler);


		// Title of window
		setTitle("Welcome page");

		// Create an outer panel with GridBagLayout
		JPanel outerPanel = new JPanel(new GridBagLayout());
		outerPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Add components to the outer panel
		outerPanel.add(welcomeL, gbc);

		gbc.gridy = 1;
		outerPanel.add(welcomeB, gbc);

		// Create a content panel with BorderLayout and add the outer panel
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.WHITE);
		contentPane.add(outerPanel, BorderLayout.CENTER);

		// Set image label properties
		myLabel.setSize(350, 400);
		contentPane.add(myLabel, BorderLayout.SOUTH);

		// Set the content pane of the frame
		setContentPane(contentPane);

		String workdr = System.getProperty("user.dir");
		System.out.println("Current working directory: " + workdr);

		// Display the window
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class WelcomeButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Activity();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Welcome());
	}
}
