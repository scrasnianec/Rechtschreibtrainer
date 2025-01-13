package Controller;

import Model.PictureQuestion;
import View.QuizView;
import Model.QuizSet;
import Model.*;
import Model.QuizQuestion;

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

		// Initialize the first question
		loadNextQuestion();
		this.mainMenuController = mainMenuController;
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
			System.out.println("Correct answer!");
		} else {
			System.out.println("Incorrect. Correct answer: " + currentQuestion.questionExplanation());
		}

		// Load the next question
		loadNextQuestion();
	}

	private void loadNextQuestion() {
		currentQuestion = quizSet.getRandomQuestionFromSet();
		resetQuizView(); // Clear the view to avoid stale data from previous questions

		if (currentQuestion != null) {
			quizView.setQuestion(currentQuestion.questionExplanation());
			if (currentQuestion instanceof PictureQuestion) {
				PictureQuestion pictureQuestion = (PictureQuestion) currentQuestion;
				quizView.setPictureURL(pictureQuestion.getImageURL());
			}
		} else {
			// Handle case when no more questions are available
			quizView.getNextButton().setEnabled(false);
			System.out.println("Quiz finished!");
			quizView.setPictureURL(null);
		}
	}

	/**
	 * Resets the quiz view to clear any stale data from the previous question.
	 */
	private void resetQuizView() {
		quizView.clearInput();
		// ts pmo ong dis cooked weil dings ähm bild geht nicht weg wenn dings es nächste dings is
		// quizView.setPictureURL(null);
	}

	public QuizView getView() {
		return quizView;
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
}
