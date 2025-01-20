package Model;

import java.util.List;
import java.util.Random;

public class QuizSet {

	private List<QuizQuestion> quizSet;
	private boolean[] setHistory;
	private QuizQuestion quizQuestion;
	private SaveLoadQuizFile saveLoadQuizFile;

	public QuizSet() {
		this.saveLoadQuizFile = new SaveLoadQuizFile();
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
