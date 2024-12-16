package View;

import Controller.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {

	private MainMenuController controller;

	private JButton startQuiz;
	private JButton startGame;
	private JButton modeEdit;
	private JButton modeStatistics;
	private JLabel levelLabel;
	private JLabel nextLevelUpLabel;

	public MainMenuView() {
		setTitle("Rechtschreib-Trainer - Hauptmenü");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initializeComponents();
		layoutComponents();
		setVisible(true);
	}

	private void initializeComponents() {
		startQuiz = new JButton("Quiz starten");
		startGame = new JButton("Spiel starten");
		modeEdit = new JButton("Editor öffnen");
		modeStatistics = new JButton("Statistiken anzeigen");

		levelLabel = new JLabel("Level: 1", SwingConstants.CENTER);
		levelLabel.setFont(new Font("Arial", Font.BOLD, 16));

		nextLevelUpLabel = new JLabel("Fortschritt zum nächsten Level: 0%", SwingConstants.CENTER);
		nextLevelUpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
	}

	private void layoutComponents() {
		setLayout(new BorderLayout(10, 10));

		// Top Panel for Level Info
		JPanel levelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		levelPanel.add(levelLabel);
		levelPanel.add(nextLevelUpLabel);

		// Center Panel for Buttons
		JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		buttonPanel.add(startQuiz);
		buttonPanel.add(startGame);
		buttonPanel.add(modeEdit);
		buttonPanel.add(modeStatistics);

		// Padding and Styling
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		levelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		add(levelPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
	}

	public void setLevel(int level) {
		levelLabel.setText("Level: " + level);
	}

	public void setPercentToNextLevel(int percent) {
		nextLevelUpLabel.setText("Fortschritt zum nächsten Level: " + percent + "%");
	}
}
