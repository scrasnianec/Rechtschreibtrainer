package Model;

public class SSharpQuestion extends QuizQuestion {
	private String relatedWord;
	private String correctAnswer;

	public SSharpQuestion(String relatedWord, String correctAnswer) {
		this.relatedWord = relatedWord;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public boolean validateAnswer(String answer) {
		return correctAnswer.equalsIgnoreCase(answer);
	}

	@Override
	public String questionExplanation() {
		return "Geben Sie die Nennform (Infinitivform) von " + relatedWord + " ein.";
	}

	@Override
	public String getType() {
		return "SSharpQuestion";
	}

    public String getRelatedWord() {
		return relatedWord;
    }

	public String getAnswer() {
		return correctAnswer;
	}
}
