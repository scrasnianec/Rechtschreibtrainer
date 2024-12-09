package Model;

public class CapitalizationQuestion extends QuizQuestion {

	private static final String question = "Tippe diesen Satz ab mit korrekter Groß- und Kleinschreibung.";

	private String correctAnswer;

	public boolean validateAnswer(String answer) {
		return false;
	}

	public String questionExplanation() {
		return null;
	}

}
