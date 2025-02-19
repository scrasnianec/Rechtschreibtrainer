package View;

import Controller.EditController;
import Model.QuizQuestion;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditView extends JPanel {

	private JTextField inputAnswer;
	private JTextField relatedWord;
	private JTextField uncompleteWord;
	private JTextField pictureURL;
	public JComboBox<String> questionType;

	// We now use JList for the questions, instead of a JComboBox:
	private JList<String> questionList;
	private DefaultListModel<String> questionListModel;
	private JScrollPane questionScrollPane;

	// Buttons
	private JButton exit;
	private JButton save;
	private JButton newF;
	private JButton deleteQuestion;
	private JButton deleteAll;
	private JButton reset;

	// Keep a local copy of the questions so we can reference them by index
	private List<QuizQuestion> allQuestions;

	public EditView() {
		FlatDarkLaf.setup();
		setLayout(new BorderLayout(10, 10));
		initializeComponents();
		layoutComponents();
	}

	private void initializeComponents() {
		// Fields
		inputAnswer = new JTextField(20);
		relatedWord = new JTextField(20);
		uncompleteWord = new JTextField(20);
		pictureURL = new JTextField(20);
		questionType = new JComboBox<>(new String[]{"Completion", "Capitalization", "Picture", "SSharp"});

		// JList + scroll pane
		questionListModel = new DefaultListModel<>();
		questionList = new JList<>(questionListModel);
		questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		questionScrollPane = new JScrollPane(questionList);

		exit = new JButton("Exit");
		save = new JButton("Save Question");
		newF = new JButton("New Question");
		deleteQuestion = new JButton("Delete Question");
		deleteAll = new JButton("Delete All Questions");
		reset = new JButton("Reset File");

		exit.setActionCommand("EXIT");
		save.setActionCommand("SAVE");
		newF.setActionCommand("NEW");
		reset.setActionCommand("RESET");
		deleteQuestion.setActionCommand("DELETE_QUESTION");
		deleteAll.setActionCommand("DELETE_ALL");

		// You can optionally set fonts, etc.
	}

	private void layoutComponents() {
		// Left panel: The list of loaded questions (scrollable)
		JPanel listPanel = new JPanel(new BorderLayout());
		listPanel.setBorder(BorderFactory.createTitledBorder("Loaded Questions"));
		listPanel.add(questionScrollPane, BorderLayout.CENTER);

		// Center panel: Fields for editing
		JPanel fieldPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		fieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		fieldPanel.add(createLabeledComponent("Question Type:", questionType));
		fieldPanel.add(createLabeledComponent("Answer Input:", inputAnswer));
		fieldPanel.add(createLabeledComponent("Related Word:", relatedWord));
		fieldPanel.add(createLabeledComponent("Uncomplete Word:", uncompleteWord));
		fieldPanel.add(createLabeledComponent("Picture URL:", pictureURL));

		// Bottom panel: the buttons
		JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
		buttonPanel.add(newF);
		buttonPanel.add(save);
		buttonPanel.add(deleteQuestion);
		buttonPanel.add(deleteAll);
		buttonPanel.add(reset);
		buttonPanel.add(exit);

		// Add sub-panels
		add(listPanel, BorderLayout.WEST);
		add(fieldPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	// For uniform labeling
	private JPanel createLabeledComponent(String labelText, JComponent component) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(labelText);
		label.setForeground(Color.LIGHT_GRAY);
		panel.add(label, BorderLayout.NORTH);
		panel.add(component, BorderLayout.CENTER);
		return panel;
	}

	// Called by controller to reload the list of questions
	public void setLoadQuestions(List<QuizQuestion> questions) {
		this.allQuestions = questions;  // Keep reference locally
		questionListModel.clear();
		for (QuizQuestion qq : questions) {
			questionListModel.addElement(qq.questionExplanation());
		}
	}

	// Called by the controller whenever a question is selected
	public void setQuestionFields(QuizQuestion question) {
		if (question == null) {
			// Clear the fields if no question
			setAnswerInput("");
			setRelatedWord("");
			setUncompleteWord("");
			setPictureURL("");
			return;
		}

		// Populate fields
		setAnswerInput(question.getAnswer());
		// Depending on type, you can fill in the relevant field:
		switch (question.getType()) {
			case "CompleteQuestion":
				// cast to your CompleteQuestion to get the uncompleteWord
				break;
			case "PictureQuestion":
				// cast to PictureQuestion to get imageURL
				break;
			case "SSharpQuestion":
				// cast to SSharpQuestion to get relatedWord
				break;
			case "CapitalizationQuestion":
				// ...
				break;
			default:
				// do nothing or handle error
		}
	}

	// Gray out (enable/disable) the fields based on question type
	public void updateFieldVisibility(String selectedType) {
		// Default everything to false
		inputAnswer.setEnabled(false);
		relatedWord.setEnabled(false);
		uncompleteWord.setEnabled(false);
		pictureURL.setEnabled(false);

		switch (selectedType) {
			case "Completion":
				inputAnswer.setEnabled(true);
				uncompleteWord.setEnabled(true);
				break;
			case "Capitalization":
				inputAnswer.setEnabled(true);
				break;
			case "Picture":
				inputAnswer.setEnabled(true);
				pictureURL.setEnabled(true);
				break;
			case "SSharp":
				inputAnswer.setEnabled(true);
				relatedWord.setEnabled(true);
				break;
		}
	}

	// Basic getters for field content
	public String getSelectedQuestionType() {
		return (String) questionType.getSelectedItem();
	}

	public String getAnswerInput() {
		return inputAnswer.getText();
	}

	public String getRelatedWord() {
		return relatedWord.getText();
	}

	public String getUncompleteWord() {
		return uncompleteWord.getText();
	}

	public String getPictureURL() {
		return pictureURL.getText();
	}

	// Basic setters
	public void setAnswerInput(String text) { inputAnswer.setText(text); }
	public void setRelatedWord(String text) { relatedWord.setText(text); }
	public void setUncompleteWord(String text) { uncompleteWord.setText(text); }
	public void setPictureURL(String text) { pictureURL.setText(text); }

	// Expose JList so controller can add a listener:
	public JList<String> getQuestionList() {
		return this.questionList;
	}

	// Expose buttons so controller can hook them
	public JButton getExitButton() { return exit; }
	public JButton getSaveButton() { return save; }
	public JButton getNewButton() { return newF; }
	public JButton getDeleteQuestionButton() { return deleteQuestion; }
	public JButton getDeleteAllButton() { return deleteAll; }
	public JButton getResetButton() { return reset; }
}
