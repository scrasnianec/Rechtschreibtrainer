package Model;

public class CompleteQuestion extends QuizQuestion {
	// When changing names also change in Deserializer!!!
	private String uncompleteWord;
	private String correctAnswer;

	public CompleteQuestion(String uncompleteWord, String correctAnswer) {
		this.uncompleteWord = uncompleteWord;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public boolean validateAnswer(String answer) {
		return correctAnswer.equals(answer);
	}

	@Override
	public String questionExplanation() {
		return "Vervollständige die verwandten Wörter.\n(Beachte die Rechtschreibung):\n" + uncompleteWord;
	}

	@Override
	public String getType() {
		return "CompleteQuestion";
	}

	@Override
	public String getAnswer() {
		return correctAnswer;
	}
}
