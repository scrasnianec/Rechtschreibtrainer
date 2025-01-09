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

	public QuizController(QuizSet quizSet) {
		this.quizSet = quizSet;
		this.quizView = new QuizView(this);

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
			if (currentQuestion instanceof CompleteQuestion) {
				CompleteQuestion completeQuestion = (CompleteQuestion) currentQuestion;
				quizView.setRelatedWord(completeQuestion.getRelatedWord());
			} else if (currentQuestion instanceof PictureQuestion) {
				PictureQuestion pictureQuestion = (PictureQuestion) currentQuestion;
				quizView.setPictureURL(pictureQuestion.getImageURL());
			} else if (currentQuestion instanceof SSharpQuestion) {
				SSharpQuestion sSharpQuestion = (SSharpQuestion) currentQuestion;
				quizView.setRelatedWord(sSharpQuestion.getRelatedWord());
			} else if (currentQuestion instanceof CapitalizationQuestion) {
				CapitalizationQuestion capitalizationQuestion = (CapitalizationQuestion) currentQuestion;
				quizView.setRelatedWord(capitalizationQuestion.getRelatedWord().toLowerCase());
			} else {
				// Handle unexpected question type
				System.err.println("Unsupported question type: " + currentQuestion.getClass().getSimpleName());
			}
			quizView.setQuestion(currentQuestion.questionExplanation());
		} else {
			// Handle case when no more questions are available
			quizView.getNextButton().setEnabled(false);
			System.out.println("Quiz finished!");
			quizView.setRelatedWord("Quiz Complete!");
			quizView.setUncompleteWord("");
			quizView.setPictureURL(null);
		}
	}

	/**
	 * Resets the quiz view to clear any stale data from the previous question.
	 */
	private void resetQuizView() {
		quizView.setRelatedWord("");
		quizView.setUncompleteWord("");
		quizView.setPictureURL(null);
	}

	public QuizView getView() {
		return quizView;
	}


	private void handleExitAction() {
		// Exit the quiz and close the view
		System.out.println("Exiting the quiz...");
		System.exit(0); // Or implement proper view disposal in a larger application
	}
}
