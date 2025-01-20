package Model;

import java.util.List;
import java.util.Random;

public class QuizSet {

	public static final int NUM_QUESTIONS_IN_SET = 5;
	private List<QuizQuestion> quizSet;
	private boolean[] setHistory;
	private QuizQuestion quizQuestion;
	private SaveLoadQuizFile saveLoadQuizFile;

	public QuizSet(SaveLoadQuizFile saveLoadQuizFile) {
		this.saveLoadQuizFile = saveLoadQuizFile;
		this.quizSet = saveLoadQuizFile.loadQuestions();
		this.setHistory = new boolean[quizSet.size()];
	}

	public QuizQuestion getRandomQuestionFromSet() {
		if (quizSet.isEmpty()) return null;
		return quizSet.get(new Random().nextInt(quizSet.size()));
	}

	public void addHistoryEntry(boolean entry) {
		// Example assumes fixed size history array
		for (int i = setHistory.length - 1; i > 0; i--) {
			setHistory[i] = setHistory[i - 1];
		}
		setHistory[0] = entry;
	}

	public int calculatePointsEarned() {
		int points = 0;
		for (boolean entry : setHistory) {
			if (entry) points += 1;
		}
		return points;
	}
}
