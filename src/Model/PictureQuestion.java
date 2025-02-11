package Model;

import java.net.URL;

public class PictureQuestion extends QuizQuestion {
	// When changing names also change in Deserializer!!!
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
		return "Was ist in diesem Bild zu sehen? (Beachte die Rechtschreibung)";
	}

	@Override
	public String getType() {
		return "PictureQuestion";
	}

	@Override
    public String getAnswer() {
		return correctAnswer;
    }

	public String getImageURL() {
		return imageURL;
	}
}
