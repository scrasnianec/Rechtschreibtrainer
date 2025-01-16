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
			quizView.setFeedbackMessage("Correct answer!");
			quizView.setMessageColor(Color.GREEN);

		} else {
			quizView.setFeedbackMessage("Incorrect. Correct answer: " + currentQuestion.questionExplanation());
			quizView.setMessageColor(Color.RED);
		}

		// Load the next question
		loadNextQuestion();
	}

	private void loadNextQuestion() {
		currentQuestion = quizSet.getRandomQuestionFromSet();
		resetQuizView(); // Clear the view to avoid stale data from previous questions
		if (currentQuestion != null) {
			if (currentQuestion instanceof PictureQuestion) {
				PictureQuestion pictureQuestion = (PictureQuestion) currentQuestion;
				quizView.setPictureQuestion(currentQuestion.questionExplanation(), pictureQuestion.getImageURL());
			} else {
				quizView.setOnlyQuestion(currentQuestion.questionExplanation());
			}
		} else {
			// Handle case when no more questions are available
			quizView.getNextButton().setEnabled(false);
			quizView.setFeedbackMessage("Quiz finished!");
			quizView.setPictureURL(null);
		}
	}

	private void resetQuizView() {
		quizView.clearInput();
		quizView.clearFeedbackMessage();
		quizView.setPictureURL(null);
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
