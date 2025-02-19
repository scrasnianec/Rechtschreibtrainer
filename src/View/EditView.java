package View;

import Controller.EditController;
import Model.CapitalizationQuestion;
import Model.CompleteQuestion;
import Model.QuizQuestion;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditView extends JPanel {

	public static final String[] QUESTION_TYPES = {"Wortformen ergänzen", "Satz korrigieren", "Bild benennen", "S, SS oder ß"};
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
		questionType = new JComboBox<>(QUESTION_TYPES);

		// JList + scroll pane
		questionListModel = new DefaultListModel<>();
		questionList = new JList<>(questionListModel);
		questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		questionScrollPane = new JScrollPane(questionList);

		exit = new JButton("Exit");
		save = new JButton("Frage speichern");
		newF = new JButton("Neue Frage");
		deleteQuestion = new JButton("Frage löschen");
		deleteAll = new JButton("Alle Fragen löschen");
		reset = new JButton("Alle Fragen zurücksetzen");

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
		listPanel.setBorder(BorderFactory.createTitledBorder("Geladene Fragen"));
		listPanel.add(questionScrollPane, BorderLayout.CENTER);

		// Center panel: Fields for editing
		JPanel fieldPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		fieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		fieldPanel.add(createLabeledComponent("Fragetyp:", questionType));
		fieldPanel.add(createLabeledComponent("Antwort:", inputAnswer));
		fieldPanel.add(createLabeledComponent("Verwandtes Wort:", relatedWord));
		fieldPanel.add(createLabeledComponent("Unvollständiger Teil:", uncompleteWord));
		fieldPanel.add(createLabeledComponent("URL von Bild:", pictureURL));

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
			String readableNames = "";
			String type = qq.getType();
			if (type.equals("CompleteQuestion")) {
				readableNames = QUESTION_TYPES[0];
			} else if (type.equals("CapitalizationQuestion")) {
				readableNames = QUESTION_TYPES[1];
			} else if (type.equals("PictureQuestion")) {
				readableNames = QUESTION_TYPES[2];
			} else if (type.equals("SSharpQuestion")) {
				readableNames = QUESTION_TYPES[3];
			} else {
				readableNames = "Unknown";
			}
			questionListModel.addElement(readableNames);
		}
	}

	// Gray out (enable/disable) the fields based on question type
	public void updateFieldVisibility(String selectedType) {
		// Default everything to false
		inputAnswer.setEnabled(false);
		relatedWord.setEnabled(false);
		uncompleteWord.setEnabled(false);
		pictureURL.setEnabled(false);

        if (selectedType.equals(QUESTION_TYPES[0])) {
            inputAnswer.setEnabled(true);
            uncompleteWord.setEnabled(true);
        } else if (selectedType.equals(QUESTION_TYPES[1])) {
            inputAnswer.setEnabled(true);
        } else if (selectedType.equals(QUESTION_TYPES[2])) {
            inputAnswer.setEnabled(true);
            pictureURL.setEnabled(true);
        } else if (selectedType.equals(QUESTION_TYPES[3])) {
            inputAnswer.setEnabled(true);
            relatedWord.setEnabled(true);
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
