package View;

import Controller.EditController;

import javax.swing.*;

public class EditView extends JPanel {

	private EditController controller;

	private JComboBox questionType;

	private JTextField inputAnswer;

	private JTextField relatedWord;

	private JTextField uncompleteWord;

	private JTextField pictureURL;

	private JButton exit;

	private JButton save;

	private JButton newF;

	private JButton load;

	private JComboBox loadQuestion;

	public EditView(EditController controller) {

	}

	public String getSelectedQuestionType() {
		return null;
	}

	public String getAnswerInput() {
		return null;
	}

	public String getRelatedWord() {
		return null;
	}

	public String getPictureURL() {
		return null;
	}

	public String getUncompleteWord() {
		return null;
	}

	public void setAnswerInput(String answer) {

	}

	public void setRelatedWord(String word) {

	}

	public void setPictureURL(String url) {

	}

	public void setUncompleteWord(String word) {

	}

}
