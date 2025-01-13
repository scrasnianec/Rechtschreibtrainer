package Model;

public class CapitalizationQuestion extends QuizQuestion {
	private String correctAnswer;

	public CapitalizationQuestion(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public boolean validateAnswer(String answer) {
		return correctAnswer.equals(answer);
	}

	@Override
	public String questionExplanation() {
		return "Tippe diesen Satz ab mit korrekter Groß- und Kleinschreibung: \n" + correctAnswer.toLowerCase();
	}

	@Override
	public String getType() {
		return "CapitalizationQuestion";
	}

	public String getUncompleteWord() {
		return correctAnswer;
	}

	public String getRelatedWord() {
		return correctAnswer;
	}
}
