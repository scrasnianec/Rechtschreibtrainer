package Controller;

import View.EditView;
import Model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

public class EditController implements ActionListener {

	private EditView editView;
	private SaveLoadQuizFile saveLoad;
	private MainMenuController mainMenuController;


	public EditController(EditView editView, MainMenuController mainMenuController) {
		this.mainMenuController = mainMenuController;
		this.editView = editView;
		this.saveLoad = new SaveLoadQuizFile();
		editView.getSaveButton().addActionListener(this);
		editView.getNewButton().addActionListener(this);
		editView.getExitButton().addActionListener(this);
		editView.getResetButton().addActionListener(this);
		editView.getLoadButton().addActionListener(this);
		editView.getResetButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
			case "SAVE":
				saveQuestion();
				break;
			case "NEW":
				clearFields();
				break;
			case "EXIT":
				stopEditor();
				break;
			case "RESET":
				QuizReset.resetQuizFile();
				JOptionPane.showMessageDialog(editView, "Quiz file reset successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				break;
			case "LOAD":
				loadQuizFile();
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}

	private void saveQuestion() {
		String type = editView.getSelectedQuestionType();
		String answer = editView.getAnswerInput();
		String relatedWord = editView.getRelatedWord();
		String uncompleteWord = editView.getUncompleteWord();
		String pictureURL = editView.getPictureURL();

		QuizQuestion question = null;
		switch (type) {
			case "Completion":
				question = new CompleteQuestion(relatedWord, uncompleteWord, answer);
				break;
			case "Capitalization":
				question = new CapitalizationQuestion(answer);
				break;
			case "Picture":
				question = new PictureQuestion(pictureURL, answer);
				break;
			case "SSharp":
				question = new SSharpQuestion(relatedWord, answer);
				break;
			default:
				throw new IllegalArgumentException("Unknown question type: " + type);
		}

		try {
			List<QuizQuestion> questions = loadExistingQuestions();
			questions.add(question);
			saveQuestionsToFile(questions);
			JOptionPane.showMessageDialog(editView, "Question saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editView, "Error saving question.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void loadQuizFile() {
		try {
			List<QuizQuestion> questions = loadExistingQuestions();
			editView.setLoadQuestions(questions);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editView, "Error loading questions.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private List<QuizQuestion> loadExistingQuestions() throws IOException {
		List<QuizQuestion> questions = saveLoad.loadQuestions();
		return (questions != null) ? questions : new ArrayList<>();
	}

	private void saveQuestionsToFile(List<QuizQuestion> questions) throws IOException {
		saveLoad.saveQuestions(questions);
	}

	private void clearFields() {
		editView.setAnswerInput("");
		editView.setRelatedWord("");
		editView.setUncompleteWord("");
		editView.setPictureURL("");
		JOptionPane.showMessageDialog(editView, "Fields cleared for a new question.", "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	public void startEditor() {
		mainMenuController.hideMainMenu();
		mainMenuController.addPanel(editView);
		editView.setVisible(true);
	}

	public void stopEditor() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(editView);
	}
}
