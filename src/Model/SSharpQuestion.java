package Model;

public class SSharpQuestion extends QuizQuestion {

	private static final String question = "Trage die Nennformen (Infinitive) ein.";

	private String relatedWord;

	private String correctAnswer;

	public boolean validateAnswer(String answer) {
		return false;
	}

	public String questionExplanation() {
		return null;
	}

}
