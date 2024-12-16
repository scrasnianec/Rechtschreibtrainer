package Controller;

import View.MainMenuView;
import Model.UserInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {

	private MainMenuView mainMenuView;
	private StatisticsController statisticsController;
	private EditController editController;
	private QuizController quizController;
	private GameController gameController;
	private UserInfo userInfo;

	public MainMenuController(MainMenuView mainMenuView, UserInfo userInfo,
							  StatisticsController statisticsController,
							  EditController editController,
							  QuizController quizController,
							  GameController gameController) {
		this.mainMenuView = mainMenuView;
		this.userInfo = userInfo;
		this.statisticsController = statisticsController;
		this.editController = editController;
		this.quizController = quizController;
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "START_GAME":
				// Logic for starting the game
				break;
			case "START_QUIZ":
				// Logic for starting the quiz
				break;
			case "EDIT_MODE":
				// Logic for opening edit mode
				break;
			case "VIEW_STATISTICS":
				// Logic for viewing statistics
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}

	public static void main(String[] args) {
		// Initialize the application
	}
}
