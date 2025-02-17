package View;

import com.formdev.flatlaf.FlatDarkLaf;
import Controller.MainMenuController;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {

	private MainMenuController controller;

	private JButton startQuiz;
	private JButton startGame;
	private JButton modeEdit;
	private JButton modeStatistics;
	private JLabel levelLabel;
	private JLabel nextLevelUpLabel;

	public MainMenuView() {
		FlatDarkLaf.setup();
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

		// Style buttons
		styleButton(startQuiz);
		styleButton(startGame);
		styleButton(modeEdit);
		styleButton(modeStatistics);

		startQuiz.setActionCommand("START_QUIZ");
		startGame.setActionCommand("START_GAME");
		modeEdit.setActionCommand("EDIT_MODE");
		modeStatistics.setActionCommand("VIEW_STATISTICS");
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

	private void styleButton(JButton button) {
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 15, 5, 15)
		));
	}

	public void setLevel(int level) {
		levelLabel.setText("Level: " + level);
	}

	public void setPercentToNextLevel(int percent) {
		nextLevelUpLabel.setText("Fortschritt zum nächsten Level: " + percent + "%");
	}

	public JButton getStartGameButton() {
		return startGame;
	}

	public JButton getStartQuizButton() {
		return startQuiz;
	}

	public JButton getEditModeButton() {
		return modeEdit;
	}

	public JButton getViewStatisticsButton() {
		return modeStatistics;
	}

}
