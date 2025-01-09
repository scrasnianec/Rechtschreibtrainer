package Model;

public class CompleteQuestion extends QuizQuestion {

	private static final String question = "Vervollständige die verwandten Wörter. (Beachte die Rechtschreibung)";
	private String relatedWord;
	private String uncompleteWord;
	private String answerToCompleteWord;

	public CompleteQuestion(String relatedWord, String uncompleteWord, String answerToCompleteWord) {
		this.relatedWord = relatedWord;
		this.uncompleteWord = uncompleteWord;
		this.answerToCompleteWord = answerToCompleteWord;
	}

	@Override
	public boolean validateAnswer(String answer) {
		return answerToCompleteWord.equals(answer);
	}

	@Override
	public String questionExplanation() {
		return "Ergänzen Sie das unvollständige Wort korrekt, basierend auf das verwandte Wort: " + relatedWord;
	}

	@Override
	public String getType() {
		return "CompleteQuestion";
	}

	public String getRelatedWord() {
		return relatedWord;
	}

	public String getUncompleteWord() {
		return uncompleteWord;
	}
}
