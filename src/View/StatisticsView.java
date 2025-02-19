package View;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;

public class StatisticsView extends JPanel {

	private JButton exit;
	private JLabel averagePoints;
	private JLabel maxPoints;
	private JLabel totalChallenges;
	private JLabel totalFailures;
	private JLabel successRate;
	private JLabel winningStreak;

	public StatisticsView() {
		FlatDarkLaf.setup();
		setLayout(new BorderLayout(10, 10));
		initializeComponents();
		layoutComponents();
	}

	private void initializeComponents() {
		exit = new JButton("Exit");

		averagePoints = new JLabel("Durchschnittliche Punkte pro Herausforderung: 0", SwingConstants.CENTER);
		maxPoints = new JLabel("Maximale Punkte für eine Herausforderung: 0", SwingConstants.CENTER);
		totalChallenges = new JLabel("Gesamtanzahl an Herausforderungen: 0", SwingConstants.CENTER);
		totalFailures = new JLabel("Gesamtanzahl an Fehlschlägen: 0", SwingConstants.CENTER);
		successRate = new JLabel("Erfolgsquote: 0%", SwingConstants.CENTER);
		winningStreak = new JLabel("Längste Siegesserie: 0", SwingConstants.CENTER);

		// Styling components
		Font labelFont = new Font("Arial", Font.BOLD, 14);
		averagePoints.setFont(labelFont);
		maxPoints.setFont(labelFont);
		totalChallenges.setFont(labelFont);
		totalFailures.setFont(labelFont);
		successRate.setFont(labelFont);
		winningStreak.setFont(labelFont);

		exit.setActionCommand("EXIT");
	}

	private void layoutComponents() {
		JPanel statsPanel = new JPanel(new GridLayout(6, 1, 5, 5));
		statsPanel.add(averagePoints);
		statsPanel.add(maxPoints);
		statsPanel.add(totalChallenges);
		statsPanel.add(totalFailures);
		statsPanel.add(successRate);
		statsPanel.add(winningStreak);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(exit);

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(statsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void setAveragePointsPerChallenge(int points) {
		averagePoints.setText("Durchschnittliche Punkte pro Herausforderung: " + points);
	}

	public void setMaxPointsForChallenge(int points) {
		maxPoints.setText("Maximale Punkte für eine Herausforderung: " + points);
	}

	public void setTotalChallengeCount(int count) {
		totalChallenges.setText("Gesamtanzahl an Herausforderungen: " + count);
	}

	public void setTotalTimesFailed(int count) {
		totalFailures.setText("Gesamtanzahl an Fails: " + count);
	}

	public void setSuccessRate(int rate) {
		successRate.setText("Erfolgsquote: " + rate + "%");
	}

	public void setWinningStreak(int streak) {
		winningStreak.setText("Längste Siegesserie: " + streak);
	}

	public JButton getExitButton() {
		return exit;
	}
}
