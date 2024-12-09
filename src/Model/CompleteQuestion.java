package Model;

public class CompleteQuestion extends QuizQuestion {

	private static final String question = "Vervollständige die verwandten Wörter. (Beachte die Rechtschreibung)";

	private String relatedWord;

	private String uncompleteWord;

	private String answerToCompleteWord;

	public boolean validateAnswer(String answer) {
		return false;
	}

	public String questionExplanation() {
		return null;
	}

}
