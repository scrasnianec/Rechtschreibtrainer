package Controller;

import View.GameView;
import Model.QuizSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

	private GameView gameView;
	private QuizSet quizSet;

	public GameController(GameView gameView, QuizSet quizSet) {
		this.gameView = gameView;
		this.quizSet = quizSet;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "NEXT":
				// Logic for showing the next question
				break;
			case "EXIT":
				// Logic for exiting the game
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}
}
