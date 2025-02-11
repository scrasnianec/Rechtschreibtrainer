package Model;

public class CapitalizationQuestion extends QuizQuestion {
	// When changing name also change in Deserializer!!!
	private String corrrectSentence;

	public CapitalizationQuestion(String corrrectSentence) {
		this.corrrectSentence = corrrectSentence;
	}

	@Override
	public boolean validateAnswer(String answer) {
		return corrrectSentence.equals(answer);
	}

	@Override
	public String questionExplanation() {
		return "Tippe diesen Satz ab mit korrekter Groß- und Kleinschreibung: \n" + corrrectSentence.toLowerCase();
	}

	@Override
	public String getType() {
		return "CapitalizationQuestion";
	}

	@Override
	public String getAnswer() {
		return corrrectSentence;
	}
}
