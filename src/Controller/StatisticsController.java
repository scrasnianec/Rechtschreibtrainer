package Controller;

import View.StatisticsView;
import Model.UserInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsController implements ActionListener {

	private StatisticsView statisticsView;
	private UserInfo userInfo;
	private MainMenuController mainMenuController;


	public StatisticsController(UserInfo userInfo, MainMenuController mainMenuController) {
		statisticsView = new StatisticsView();
		this.userInfo = userInfo;

		// Initialize the view with data from the model
		updateStatisticsView();

		// Register event listeners
		this.statisticsView.getExitButton().addActionListener(this);

		this.mainMenuController = mainMenuController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "EXIT":
				stopStatistics();
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}

	private void updateStatisticsView() {
		// Update the view with statistics data from the UserInfo model
		statisticsView.setAveragePointsPerChallange(calculateAveragePoints());
		statisticsView.setMaxPointsForChallange(calculateMaxPoints());
		statisticsView.setTotalChallangeCount(userInfo.getPointHistory().size());
		statisticsView.setTotalTimesFailed(calculateFailures());
		statisticsView.setSuccessRate(calculateSuccessRate());
		statisticsView.setWinningStreak(calculateWinningStreak());
	}

	private int calculateAveragePoints() {
		if (userInfo.getPointHistory().isEmpty()) return 0;
		int total = userInfo.getPointHistory().stream().mapToInt(Integer::intValue).sum();
		return total / userInfo.getPointHistory().size();
	}

	private int calculateMaxPoints() {
		return userInfo.getPointHistory().stream().mapToInt(Integer::intValue).max().orElse(0);
	}

	private int calculateFailures() {
		// Assume failures are negative entries in the point history
		return (int) userInfo.getPointHistory().stream().filter(points -> points < 0).count();
	}

	private int calculateSuccessRate() {
		int totalChallenges = userInfo.getPointHistory().size();
		if (totalChallenges == 0) return 0;
		int successes = (int) userInfo.getPointHistory().stream().filter(points -> points > 0).count();
		return (successes * 100) / totalChallenges;
	}

	private int calculateWinningStreak() {
		int streak = 0;
		int maxStreak = 0;

		for (int points : userInfo.getPointHistory()) {
			if (points > 0) {
				streak++;
				maxStreak = Math.max(maxStreak, streak);
			} else {
				streak = 0;
			}
		}

		return maxStreak;
	}

	public void startStatistics() {
		mainMenuController.hideMainMenu();
		mainMenuController.addPanel(statisticsView);
		statisticsView.setVisible(true);
	}

	public void stopStatistics() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(statisticsView);
	}
}
