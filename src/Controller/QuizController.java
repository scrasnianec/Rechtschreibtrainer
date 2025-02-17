package Controller;

import Model.PictureQuestion;
import Model.QuizSet;
import Model.QuizQuestion;
import Model.UserInfo;
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
				exitQuiz();
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
		quizSet.addHistoryEntry(isCorrect);
		if (isCorrect) {
			quizView.setFeedbackMessage("Correct answer!");
			quizView.setMessageColor(Color.GREEN);

		} else {
			quizView.setFeedbackMessage("Incorrect. Correct answer: " + currentQuestion.getAnswer());
			quizView.setMessageColor(Color.RED);
		}
		quizView.clearInput();
		loadNextQuestion();
		quizView.setFocusToInput();
	}

	private void loadNextQuestion() {
		currentQuestion = quizSet.getRandomQuestionFromSet();

		if (currentQuestion != null) {
			// Handle regular questions
			if (currentQuestion instanceof PictureQuestion) {
				PictureQuestion pictureQuestion = (PictureQuestion) currentQuestion;
				quizView.setPictureQuestion(currentQuestion.questionExplanation(), pictureQuestion.getImageURL());
			} else {
				quizView.setOnlyQuestion(currentQuestion.questionExplanation());
			}
		} else {
			// Handle the end of the quiz
			int correctRounds = quizSet.calculateCorrectRounds();
			String feedback = "Ende!\nDu hast " + correctRounds + " von " + quizSet.NUM_QUESTIONS_IN_SET + " Fragen richtig beantwortet.\n";
			quizView.getInputAnswerField().setEnabled(false);
			quizView.enableRestartQuizButton();
			quizView.setOnlyQuestion("");

			if (correctRounds == QuizSet.NUM_QUESTIONS_IN_SET) {
				quizView.setMessageColor(Color.GREEN);
				feedback += "Perfekt!";
			} else if (correctRounds == 0) {
				quizView.setMessageColor(Color.RED);
				feedback += "Das war leider nix!";
			} else {
				quizView.setMessageColor(Color.ORANGE);
				feedback += "Nicht schlecht!";
			}
			new UserInfo().addPoints(quizSet.calculatePointsEarnedInQuiz());
			quizView.setFeedbackMessage(feedback);
			quizView.setPictureURL(null);
		}
	}

	public void startQuiz() {
		restartQuiz();
		mainMenuController.hideMainMenu();
		mainMenuController.addPanel(quizView);
		quizView.setVisible(true);
	}

	public void exitQuiz() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(quizView);
	}

	private void restartQuiz() {
		quizSet = new QuizSet();

		loadNextQuestion();

		quizView.clearInput();
		quizView.clearFeedbackMessage();
		quizView.resetNextButton();
		quizView.setFeedbackMessage("");
		quizView.getInputAnswerField().setEnabled(true);
		quizView.resetNextButton();
	}
}
