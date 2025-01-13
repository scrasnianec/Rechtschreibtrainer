package Controller;

import View.GameView;
import Model.QuizSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

	private GameView gameView;
	private QuizSet quizSet;
	private MainMenuController mainMenuController;


	public GameController(GameView gameView, QuizSet quizSet, MainMenuController mainMenuController) {
		this.gameView = gameView;
		this.quizSet = quizSet;
		this.mainMenuController = mainMenuController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "NEXT":
				// Logic for showing the next question
				break;
			case "EXIT":
				stopGame();
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}

	public void startGame() {
		mainMenuController.hideMainMenu();
		mainMenuController.addPanel(gameView);
		gameView.setVisible(true);
	}

	public void stopGame() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(gameView);
	}
}
