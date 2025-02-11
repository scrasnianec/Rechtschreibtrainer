package Controller;

import Model.PictureQuestion;
import Model.QuizSet;
import Model.QuizQuestion;
import View.QuizView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizController implements ActionListener {
	private QuizView quizView;
	private QuizSet quizSet;
	private QuizQuestion currentQuestion;
	private MainMenuController mainMenuController;

	public QuizController(QuizSet quizSet, MainMenuController mainMenuController) {
		this.quizSet = quizSet;
		this.quizView = new QuizView(this);

		this.mainMenuController = mainMenuController;

		// Initialize the first question
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
				handleExitAction();
				break;
			case "RESTART_QUIZ":
				restartQuiz();
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}

	private void handleNextAction() {
		String userAnswer = quizView.getAnswerInput();
		boolean isCorrect = currentQuestion.validateAnswer(userAnswer);

		// Update the quiz history and provide feedback
		quizSet.addHistoryEntry(isCorrect);
		if (isCorrect) {
			quizView.setFeedbackMessage("Correct answer!");
			quizView.setMessageColor(Color.GREEN);
		} else {
			quizView.setFeedbackMessage("Incorrect. Correct answer: " + currentQuestion.questionExplanation());
			quizView.setMessageColor(Color.RED);
		}

		// Load the next question
		loadNextQuestion();
		quizView.setFocusToInput();
	}

	private void loadNextQuestion() {
		currentQuestion = quizSet.getRandomQuestionFromSet();
		resetQuizView(); // Clear stale data

		if (currentQuestion != null) {
			if (currentQuestion instanceof PictureQuestion) {
				PictureQuestion pictureQuestion = (PictureQuestion) currentQuestion;
				quizView.setPictureQuestion(currentQuestion.questionExplanation(), pictureQuestion.getImageURL());
			} else {
				quizView.setOnlyQuestion(currentQuestion.questionExplanation());
			}
		} else {
			// End of quiz
			quizView.enableRestartQuizButton();
			String feedback = "Ende!\nDu hast " + quizSet.calculatePointsEarned() + " von " + QuizSet.NUM_QUESTIONS_IN_SET + " Fragen richtig beantwortet.\n";
			quizView.setOnlyQuestion("");

			if (quizSet.calculatePointsEarned() == QuizSet.NUM_QUESTIONS_IN_SET) {
				quizView.setMessageColor(Color.GREEN);
				feedback += "Perfekt!";
			} else if (quizSet.calculatePointsEarned() == 0) {
				quizView.setMessageColor(Color.RED);
				feedback += "Das war leider nix!";
			} else {
				quizView.setMessageColor(Color.ORANGE);
				feedback += "Nicht schlecht!";
			}

			quizView.addToFeedbackMessage(feedback);
		}
	}

	private void resetQuizView() {
		quizView.clearInput();
		quizView.clearFeedbackMessage();
	}

	private void restartQuiz() {
		quizView.setFeedbackMessage("");
		quizSet = new QuizSet(); // Reset quiz set
		quizView.resetNextButton(); // Reset button to "Next"
		loadNextQuestion(); // Load first question of new quiz
	}

	private void handleExitAction() {
		stopQuiz();
	}

	public void openQuiz() {
		mainMenuController.hideMainMenu();
		mainMenuController.addPanel(quizView);
		quizView.setVisible(true);
	}

	public void stopQuiz() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(quizView);
	}

	public QuizView getView() {
		return quizView;
	}
}
