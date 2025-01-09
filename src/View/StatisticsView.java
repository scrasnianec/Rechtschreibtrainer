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

		averagePoints = new JLabel("Average Points per Challenge: 0", SwingConstants.CENTER);
		maxPoints = new JLabel("Max Points for a Challenge: 0", SwingConstants.CENTER);
		totalChallenges = new JLabel("Total Challenges: 0", SwingConstants.CENTER);
		totalFailures = new JLabel("Total Failures: 0", SwingConstants.CENTER);
		successRate = new JLabel("Success Rate: 0%", SwingConstants.CENTER);
		winningStreak = new JLabel("Winning Streak: 0", SwingConstants.CENTER);

		// Styling components
		Font labelFont = new Font("Arial", Font.BOLD, 14);
		averagePoints.setFont(labelFont);
		maxPoints.setFont(labelFont);
		totalChallenges.setFont(labelFont);
		totalFailures.setFont(labelFont);
		successRate.setFont(labelFont);
		winningStreak.setFont(labelFont);
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

	public void setAveragePointsPerChallange(int points) {
		averagePoints.setText("Average Points per Challenge: " + points);
	}

	public void setMaxPointsForChallange(int points) {
		maxPoints.setText("Max Points for a Challenge: " + points);
	}

	public void setTotalChallangeCount(int count) {
		totalChallenges.setText("Total Challenges: " + count);
	}

	public void setTotalTimesFailed(int count) {
		totalFailures.setText("Total Failures: " + count);
	}

	public void setSuccessRate(int rate) {
		successRate.setText("Success Rate: " + rate + "%");
	}

	public void setWinningStreak(int streak) {
		winningStreak.setText("Winning Streak: " + streak);
	}

	public JButton getExitButton() {
		return exit;
	}
}
