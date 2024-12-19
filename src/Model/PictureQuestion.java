package Model;

public class PictureQuestion extends QuizQuestion {

	private static final String question = "Was ist in diesem Bild zu sehen? (Beachte die Rechtschreibung)";
	private String imageURL;
	private String correctAnswer;

	public PictureQuestion(String imageURL, String correctAnswer) {
		this.imageURL = imageURL;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public boolean validateAnswer(String answer) {
		return correctAnswer.equalsIgnoreCase(answer);
	}

	@Override
	public String questionExplanation() {
		return "Beschreiben Sie, was Sie auf dem Bild sehen. URL: " + imageURL;
	}

	@Override
	public String getType() {
		return "PictureQuestion";
	}
}
