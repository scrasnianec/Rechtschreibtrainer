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
	private SaveLoadQuizFile save;

	public Frame frame;

	public MainMenuController(Frame frame) {
		this.frame = frame;

		mainMenuView = new MainMenuView();
		save = new SaveLoadQuizFile();
		statisticsController = new StatisticsController(this);
		editController = new EditController(new EditView(), this);
		quizController = new QuizController(new QuizSet(), this);
		gameController = new GameController(new QuizSet(), this);

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
				quizController.startQuiz();
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
		frame.setVisible(true);
		frame.add(mainMenuView);

		mainMenuView.setLevel(new UserInfo().getLevel());
		mainMenuView.setPercentToNextLevel(new UserInfo().percentToNextLevel());
	}


	public void hideMainMenu() {
		mainMenuView.setVisible(false);
	}

	public void showMainMenu() {
		this.updateMainMenuView();
		mainMenuView.setVisible(true);
	}

	public void addPanel(JPanel panel) {
		frame.add(panel);
	}

	public void removePanel(JPanel panel) {
		frame.remove(panel);
	}

}
