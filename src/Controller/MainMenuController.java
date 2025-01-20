package Controller;

import Model.QuizSet;
import Model.SaveLoadQuizFile;
import View.*;
import Model.UserInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {

	private MainMenuView mainMenuView;
	private StatisticsController statisticsController;
	private EditController editController;
	private QuizController quizController;
	private GameController gameController;
	private UserInfo userInfo;
	SaveLoadQuizFile save;

	public Frame frame;

	public MainMenuController(Frame frame) {
		this.frame = frame;

		userInfo = new UserInfo();
		mainMenuView = new MainMenuView();
		statisticsController = new StatisticsController(userInfo, this);
		editController = new EditController(new EditView(), this);
		quizController = new QuizController(new QuizSet(), this);
		gameController = new GameController(new GameView(), new QuizSet(), this);

		// Update the view with initial data
		updateMainMenuView();

		// Register action listeners
		this.mainMenuView.getStartGameButton().addActionListener(this);
		this.mainMenuView.getStartQuizButton().addActionListener(this);
		this.mainMenuView.getEditModeButton().addActionListener(this);
		this.mainMenuView.getViewStatisticsButton().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "START_GAME":
				gameController.startGame();
				break;
			case "START_QUIZ":
				quizController.openQuiz();
				break;
			case "EDIT_MODE":
				editController.startEditor();
				break;
			case "VIEW_STATISTICS":
				statisticsController.startStatistics();
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}

	private void updateMainMenuView() {
		// Update the view with data from the UserInfo model
		frame.setVisible(true);
		frame.add(mainMenuView);

		mainMenuView.setLevel(userInfo.getLevel());
		mainMenuView.setPercentToNextLevel(userInfo.percentToNextLevel());
	}


	public void hideMainMenu() {
		mainMenuView.setVisible(false);
	}

	public void showMainMenu() {
		mainMenuView.setVisible(true);
	}

	public void addPanel(JPanel panel) {
		frame.add(panel);
	}

	public void removePanel(JPanel panel) {
		frame.remove(panel);
	}

}
