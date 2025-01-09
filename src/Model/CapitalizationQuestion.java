package Model;

public class CapitalizationQuestion extends QuizQuestion {

	private static final String question = "Tippe diesen Satz ab mit korrekter Groß- und Kleinschreibung.";
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
		return "Stellen Sie sicher, dass Ihre Antwort die gleiche Groß- und Kleinschreibung wie das Original hat.";
	}

	@Override
	public String getType() {
		return "CapitalizationQuestion";
	}

	public String getUncompleteWord() {
		return correctAnswer;
	}
}
