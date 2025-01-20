package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class QuizSet {

	public static final int NUM_QUESTIONS_IN_SET = 5;
	private List<QuizQuestion> quizFile;
	private List<QuizQuestion> quizSet;
	private boolean[] setHistory;
	private QuizQuestion quizQuestion;
	private SaveLoadQuizFile saveLoadQuizFile;
	int quizSetIndex = 0;

	public QuizSet() {
		this.saveLoadQuizFile = new SaveLoadQuizFile();
		this.quizFile = saveLoadQuizFile.loadQuestions();
		this.quizSet = new ArrayList<>();
		this.setHistory = new boolean[quizFile.size()];

		HashSet<Integer> usedIndices = new HashSet<>();
		Random random = new Random();

		while (quizSet.size() < 5 && quizSet.size() < quizFile.size()) {
			int randomIndex = random.nextInt(quizFile.size());
			if (!usedIndices.contains(randomIndex)) {
				quizSet.add(quizFile.get(randomIndex));
				usedIndices.add(randomIndex);
			}
		}
	}

	public QuizQuestion getRandomQuestionFromFile() {
		List<QuizQuestion> questions = saveLoadQuizFile.loadQuestions();
		return questions.get(new Random().nextInt(questions.size()));
	}

	public QuizQuestion getRandomQuestionFromSet() {
		if (quizSet.isEmpty()) return null;
		QuizQuestion question = null;
		if (quizSetIndex < NUM_QUESTIONS_IN_SET) {
			question = quizSet.get(quizSetIndex);
			quizSetIndex++;
		}
		return question;
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
