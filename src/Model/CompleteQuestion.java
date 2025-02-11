package Model;

public class CompleteQuestion extends QuizQuestion {
	// When changing names also change in Deserializer!!!
	private String relatedWord;
	private String uncompleteWord;
	private String correctAnswer;

	public CompleteQuestion(String relatedWord, String uncompleteWord, String correctAnswer) {
		this.relatedWord = relatedWord;
		this.uncompleteWord = uncompleteWord;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public boolean validateAnswer(String answer) {
		return correctAnswer.equals(answer);
	}

	@Override
	public String questionExplanation() {
		return "Vervollständige die verwandten Wörter. (Beachte die Rechtschreibung): " + uncompleteWord;
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
