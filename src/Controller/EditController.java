package Controller;

import View.EditView;
import Model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditController implements ActionListener {

	private EditView editView;
	private SaveLoadQuizFile saveLoad;
	private MainMenuController mainMenuController;

	public EditController(EditView editView, MainMenuController mainMenuController) {
		this.mainMenuController = mainMenuController;
		this.editView = editView;
		this.saveLoad = new SaveLoadQuizFile();

		// Add action listeners to buttons
		editView.getSaveButton().addActionListener(this);
		editView.getNewButton().addActionListener(this);
		editView.getExitButton().addActionListener(this);
		editView.getResetButton().addActionListener(this);
		editView.getDeleteQuestionButton().addActionListener(this);
		editView.getDeleteAllButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
			case "SAVE":
				saveQuestion();
				try {
					List<QuizQuestion> questions = loadExistingQuestions();
					editView.setLoadQuestions(questions);
				} catch (IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(editView, "Error loading questions.", "Error", JOptionPane.ERROR_MESSAGE);
				}				break;
			case "NEW":
				clearFields();
				break;
			case "EXIT":
				stopEditor();
				break;
			case "RESET":
				QuizReset.resetQuizFile();
				try {
					List<QuizQuestion> questions = loadExistingQuestions();
					editView.setLoadQuestions(questions);
					JOptionPane.showMessageDialog(editView, "Quiz file reset successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(editView, "Error resetting quiz file.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case "DELETE_QUESTION":
				deleteSelectedQuestion();
				break;
			case "DELETE_ALL":
				deleteAllQuestions();
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

	private void deleteSelectedQuestion() {
		int selectedIndex = editView.getLoadQuestionComboBox().getSelectedIndex();
		if (selectedIndex < 0) {
			JOptionPane.showMessageDialog(editView, "No question selected for deletion.", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			List<QuizQuestion> questions = loadExistingQuestions();
			questions.remove(selectedIndex); // Remove the selected question
			saveQuestionsToFile(questions); // Save updated list to file
			editView.setLoadQuestions(questions); // Refresh UI
			JOptionPane.showMessageDialog(editView, "Question deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editView, "Error deleting question.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {
			List<QuizQuestion> questions = loadExistingQuestions();
			editView.setLoadQuestions(questions);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void deleteAllQuestions() {
		int confirmation = JOptionPane.showConfirmDialog(
				editView,
				"Are you sure you want to delete all questions?",
				"Confirm Deletion",
				JOptionPane.YES_NO_OPTION
		);
		if (confirmation == JOptionPane.YES_OPTION) {
			try {
				List<QuizQuestion> questions = new ArrayList<>();
				saveQuestionsToFile(questions); // Save empty list to file
				editView.setLoadQuestions(questions); // Refresh UI
				JOptionPane.showMessageDialog(editView, "All questions deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(editView, "Error deleting all questions.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		try {
			List<QuizQuestion> questions = loadExistingQuestions();
			editView.setLoadQuestions(questions);
		} catch (IOException ex) {
			ex.printStackTrace();
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
		try {
			List<QuizQuestion> questions = loadExistingQuestions();
			editView.setLoadQuestions(questions);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editView, "Error loading questions.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void stopEditor() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(editView);
	}
}
