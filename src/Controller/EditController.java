package Controller;

import View.EditView;
import Model.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditController implements ActionListener {

	private EditView editView;
	private SaveLoadQuizFile saveLoad;
	private MainMenuController mainMenuController;

	// Keep the currently loaded questions so we can easily retrieve them by index
	private List<QuizQuestion> currentQuestions = new ArrayList<>();

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

		// Listen for selections in the question list:
		editView.getQuestionList().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int idx = editView.getQuestionList().getSelectedIndex();
				if (idx >= 0 && idx < currentQuestions.size()) {
					QuizQuestion selectedQ = currentQuestions.get(idx);
					loadQuestionIntoFields(selectedQ);
				}
			}
		});

		// Also listen for changes to questionType, so we can grey out unused fields:
		editView.questionType.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String newType = (String) e.getItem();
				editView.updateFieldVisibility(newType);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
			case "SAVE":
				saveQuestion();
				reloadQuestions();
				break;
			case "NEW":
				clearFields();
				break;
			case "EXIT":
				stopEditor();
				break;
			case "RESET":
				QuizReset.resetQuizFile();
				reloadQuestions();
				JOptionPane.showMessageDialog(editView, "Quiz file reset successfully.",
						"Success", JOptionPane.INFORMATION_MESSAGE);
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

	private void reloadQuestions() {
		try {
			currentQuestions = loadExistingQuestions();
			editView.setLoadQuestions(currentQuestions);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editView, "Error loading questions.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadQuestionIntoFields(QuizQuestion question) {
		// Clear them all first
		editView.setAnswerInput("");
		editView.setRelatedWord("");
		editView.setUncompleteWord("");
		editView.setPictureURL("");

		if (question == null) {
			return; // nothing to load
		}

		// Set the question type in the combo box so it matches the loaded question
		String type = question.getType();
		editView.questionType.setSelectedItem(questionTypeToComboValue(type));

		// Fill in the general fields
		editView.setAnswerInput(question.getAnswer());

		// Now fill in *type-specific* fields by casting:
		switch (type) {
			case "CompleteQuestion":
				// Cast so we can get the uncompleteWord:
				CompleteQuestion cQuestion = (CompleteQuestion) question;
				editView.setUncompleteWord(cQuestion.getUncompleteWord());
				// The other fields (relatedWord, pictureURL) stay empty
				break;

			case "PictureQuestion":
				// Cast so we can get the URL:
				PictureQuestion pQuestion = (PictureQuestion) question;
				editView.setPictureURL(pQuestion.getImageURL());
				// The other fields stay empty
				break;

			case "SSharpQuestion":
				// Cast so we can get the relatedWord, etc.:
				SSharpQuestion sQuestion = (SSharpQuestion) question;
				editView.setRelatedWord(sQuestion.getRelatedWord());
				break;

			case "CapitalizationQuestion":
				// For Capitalization, it might only have an answer
				// so nothing else needed, if that's how your class is defined:
				// CapitalizationQuestion cap = (CapitalizationQuestion) question;
				break;
		}

		// Finally, enable/disable fields:
		editView.updateFieldVisibility(editView.getSelectedQuestionType());
	}


	/**
	 * A small helper to map the getType() string to the item in the combo box.
	 * Example: "PictureQuestion" -> "Picture"
	 */
	private String questionTypeToComboValue(String questionTypeName) {
		switch (questionTypeName) {
			case "CompleteQuestion":
				return EditView.QUESTION_TYPES[0];
			case "CapitalizationQuestion":
				return EditView.QUESTION_TYPES[1];
			case "PictureQuestion":
				return EditView.QUESTION_TYPES[2];
			case "SSharpQuestion":
				return EditView.QUESTION_TYPES[3];
			default:
				return "Completion"; // fallback
		}
	}

	private List<QuizQuestion> loadExistingQuestions() throws IOException {
		List<QuizQuestion> questions = saveLoad.loadQuestions();
		return (questions != null) ? questions : new ArrayList<>();
	}

	private void saveQuestionsToFile(List<QuizQuestion> questions) throws IOException {
		saveLoad.saveQuestions(questions);
	}

	private void saveQuestion() {
		String type = editView.getSelectedQuestionType(); //
		String answer = editView.getAnswerInput();
		String relatedWord = editView.getRelatedWord();
		String uncompleteWord = editView.getUncompleteWord();
		String pictureURL = editView.getPictureURL();

		QuizQuestion question;
        if (type.equals(EditView.QUESTION_TYPES[0])) {
            question = new CompleteQuestion(uncompleteWord, answer);
        } else if (type.equals(EditView.QUESTION_TYPES[1])) {
            question = new CapitalizationQuestion(answer);
        } else if (type.equals(EditView.QUESTION_TYPES[2])) {
            question = new PictureQuestion(pictureURL, answer);
        } else if (type.equals(EditView.QUESTION_TYPES[3])) {
            question = new SSharpQuestion(relatedWord, answer);
        } else {
            throw new IllegalArgumentException("Unknown question type: " + type);
        }

		try {
			List<QuizQuestion> questions = loadExistingQuestions();
			questions.add(question);
			saveQuestionsToFile(questions);
			JOptionPane.showMessageDialog(editView, "Question saved successfully!",
					"Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editView, "Error saving question.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteSelectedQuestion() {
		int selectedIndex = editView.getQuestionList().getSelectedIndex();
		if (selectedIndex < 0) {
			JOptionPane.showMessageDialog(editView, "No question selected for deletion.",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			List<QuizQuestion> questions = loadExistingQuestions();
			questions.remove(selectedIndex); // remove the selected question
			saveQuestionsToFile(questions);
			reloadQuestions();
			JOptionPane.showMessageDialog(editView, "Question deleted successfully.",
					"Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(editView, "Error deleting question.",
					"Error", JOptionPane.ERROR_MESSAGE);
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
				List<QuizQuestion> emptyList = new ArrayList<>();
				saveQuestionsToFile(emptyList);
				reloadQuestions();
				JOptionPane.showMessageDialog(editView, "All questions deleted successfully.",
						"Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(editView, "Error deleting all questions.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void clearFields() {
		editView.setAnswerInput("");
		editView.setRelatedWord("");
		editView.setUncompleteWord("");
		editView.setPictureURL("");
		// Optionally reset the questionType combo
		editView.questionType.setSelectedIndex(0);
		// and update field visibility
		editView.updateFieldVisibility(editView.getSelectedQuestionType());
		JOptionPane.showMessageDialog(editView, "Fields cleared for a new question.",
				"Info", JOptionPane.INFORMATION_MESSAGE);
	}

	public void startEditor() {
		mainMenuController.hideMainMenu();
		mainMenuController.addPanel(editView);
		editView.setVisible(true);
		reloadQuestions();
		// Make sure fields are greyed out (initially)
		editView.updateFieldVisibility(editView.getSelectedQuestionType());
	}

	public void stopEditor() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(editView);
	}
}
