package Model;

public class CompleteQuestion extends QuizQuestion {
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
		return "Vervollständige die verwandten Wörter. (Beachte die Rechtschreibung): " + uncompleteWord;
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
