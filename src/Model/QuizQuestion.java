package Model;

public abstract class QuizQuestion {
	public abstract boolean validateAnswer(String answer);

	public abstract String questionExplanation();

	public abstract String getType();

	public abstract String getAnswer();
}
