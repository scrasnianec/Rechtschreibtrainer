package Controller;

import Model.PictureQuestion;
import Model.QuizQuestion;
import Model.QuizSet;
import View.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

	private GameView gameView;
	private QuizSet quizSet;
	private MainMenuController mainMenuController;
	private QuizQuestion currentQuestion;
	private int wrongAnswersCount = 0;
	private final int MAX_WRONG = 6; // maximum allowed wrong answers / hangman steps

	public GameController(QuizSet quizSet, MainMenuController mainMenuController) {
		this.gameView = new GameView(this);
		this.quizSet = quizSet;
		this.mainMenuController = mainMenuController;
		loadNextQuestion();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "NEXT":
				handleNextAction();
				break;
			case "EXIT":
				exitGame();
				break;
			case "RESTART_QUIZ":
				restartQuiz();
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}

	private void handleNextAction() {
		String userAnswer = gameView.getAnswerInput();
		boolean isCorrect = currentQuestion.validateAnswer(userAnswer);
		quizSet.addHistoryEntry(isCorrect);

		if (isCorrect) {
			gameView.setFeedbackMessage("Correct answer!");
			gameView.setMessageColor(Color.GREEN);
		} else {
			gameView.setFeedbackMessage("Incorrect. Correct answer: " + currentQuestion.getAnswer());
			gameView.setMessageColor(Color.RED);
			wrongAnswersCount++;
			gameView.addHangmanStep();
			if (wrongAnswersCount >= MAX_WRONG) {
				// end of game
				String feedback = "Game Over!\nDu hast " + quizSet.calculatePointsEarned() + " Fragen richtig beantwortet.\n";
				gameView.setFeedbackMessage(feedback);
				gameView.getInputField().setEnabled(false);
				gameView.enableRestartQuizButton();
				gameView.setOnlyQuestion("");
				return;
			}
		}
		loadNextQuestion();
		gameView.clearInput();
		gameView.setFocusToInput();
	}

	private void loadNextQuestion() {
		currentQuestion = quizSet.getRandomQuestionFromFile();

		if (currentQuestion != null) {
			if (currentQuestion instanceof PictureQuestion) {
				PictureQuestion pictureQuestion = (PictureQuestion) currentQuestion;
				gameView.setPictureQuestion(currentQuestion.questionExplanation(), pictureQuestion.getImageURL());
			} else {
				gameView.setOnlyQuestion(currentQuestion.questionExplanation());
			}
		} else {
			// In case no more questions are available
			JOptionPane.showMessageDialog(gameView,
					"No more questions available.",
					"Information",
					JOptionPane.INFORMATION_MESSAGE);
			exitGame();
		}
	}

	public void startGame() {
		mainMenuController.hideMainMenu();
		mainMenuController.addPanel(gameView);
		gameView.setVisible(true);
		wrongAnswersCount = 0;
		gameView.resetHangman();
		gameView.resetNextButton();
		gameView.clearInput();
		gameView.clearFeedbackMessage();
		gameView.setPictureURL(null);
	}

	public void exitGame() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(gameView);
	}

	private void restartQuiz() {
		gameView.setFeedbackMessage("");
		gameView.getInputField().setEnabled(true);
		startGame();
		loadNextQuestion();
	}
}
