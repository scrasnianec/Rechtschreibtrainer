package Model;

public class PictureQuestion extends QuizQuestion {

	private static final String question = "Was ist in diesem Bild zu sehen? (Beachte die Rechtschreibung)";

	private String imageURL;

	private String correctAnswer;

	public boolean validateAnswer(String answer) {
		return false;
	}

	public String questionExplanation() {
		return null;
	}

}
