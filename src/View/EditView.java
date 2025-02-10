package View;

import Model.QuizQuestion;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditView extends JPanel {

	private JComboBox<String> questionType;
	private JTextField inputAnswer;
	private JTextField relatedWord;
	private JTextField uncompleteWord;
	private JTextField pictureURL;

	private JButton exit;
	private JButton save;
	private JButton newF;
	private JButton deleteQuestion;
	private JButton deleteAll;
	private JButton reset;
	private JComboBox<String> loadQuestion;

	public EditView() {
		FlatDarkLaf.setup();
		setLayout(new BorderLayout(10, 10));
		initializeComponents();
		layoutComponents();
	}

	private void initializeComponents() {
		questionType = new JComboBox<>(new String[]{"Completion", "Capitalization", "Picture", "SSharp"});
		inputAnswer = new JTextField(20);
		relatedWord = new JTextField(20);
		uncompleteWord = new JTextField(20);
		pictureURL = new JTextField(20);

		exit = new JButton("Exit");
		save = new JButton("Save Question");
		newF = new JButton("New Question");
		deleteQuestion = new JButton("Delete Question");
		deleteAll = new JButton("Delete All Questions");
		reset = new JButton("Reset File");
		loadQuestion = new JComboBox<>();

		questionType.setFont(new Font("Arial", Font.PLAIN, 14));
		relatedWord.setFont(new Font("Arial", Font.PLAIN, 14));
		uncompleteWord.setFont(new Font("Arial", Font.PLAIN, 14));
		pictureURL.setFont(new Font("Arial", Font.PLAIN, 14));
		inputAnswer.setFont(new Font("Arial", Font.PLAIN, 14));

		exit.setFont(new Font("Arial", Font.BOLD, 14));
		save.setFont(new Font("Arial", Font.BOLD, 14));
		newF.setFont(new Font("Arial", Font.BOLD, 14));
		deleteQuestion.setFont(new Font("Arial", Font.BOLD, 14));
		deleteAll.setFont(new Font("Arial", Font.BOLD, 14));
		reset.setFont(new Font("Arial", Font.BOLD, 14));
		loadQuestion.setFont(new Font("Arial", Font.PLAIN, 14));

		exit.setActionCommand("EXIT");
		save.setActionCommand("SAVE");
		newF.setActionCommand("NEW");
		reset.setActionCommand("RESET");
		deleteQuestion.setActionCommand("DELETE_QUESTION");
		deleteAll.setActionCommand("DELETE_ALL");
	}

	private void layoutComponents() {
		JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		inputPanel.add(createLabeledComponent("Question Type:", questionType));
		inputPanel.add(createLabeledComponent("Answer Input:", inputAnswer));
		inputPanel.add(createLabeledComponent("Related Word:", relatedWord));
		inputPanel.add(createLabeledComponent("Uncomplete Word:", uncompleteWord));
		inputPanel.add(createLabeledComponent("Picture URL:", pictureURL));

		JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
		buttonPanel.add(newF);
		buttonPanel.add(save);
		buttonPanel.add(deleteQuestion);
		buttonPanel.add(deleteAll);
		buttonPanel.add(reset);
		buttonPanel.add(exit);

		JPanel loadQuestionPanel = new JPanel(new BorderLayout(10, 10));
		loadQuestionPanel.setBorder(BorderFactory.createTitledBorder("Loaded Questions"));
		loadQuestionPanel.add(loadQuestion, BorderLayout.CENTER);

		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel.add(inputPanel, BorderLayout.CENTER);
		centerPanel.add(loadQuestionPanel, BorderLayout.EAST);

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(centerPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private JPanel createLabeledComponent(String labelText, JComponent component) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(labelText);
		label.setForeground(Color.LIGHT_GRAY);
		panel.add(label, BorderLayout.NORTH);
		panel.add(component, BorderLayout.CENTER);
		return panel;
	}

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

	public void setAnswerInput(String answer) {
		inputAnswer.setText(answer);
	}

	public void setRelatedWord(String word) {
		relatedWord.setText(word);
	}

	public void setUncompleteWord(String word) {
		uncompleteWord.setText(word);
	}

	public void setPictureURL(String url) {
		pictureURL.setText(url);
	}

	public JButton getExitButton() {
		return exit;
	}

	public JButton getSaveButton() {
		return save;
	}

	public JButton getNewButton() {
		return newF;
	}

	public JButton getDeleteQuestionButton() {
		return deleteQuestion;
	}

	public JButton getDeleteAllButton() {
		return deleteAll;
	}

	public JButton getResetButton() {
		return reset;
	}

	public JComboBox<String> getLoadQuestionComboBox() {
		return loadQuestion;
	}

	public void setLoadQuestions(List<QuizQuestion> questions) {
		loadQuestion.removeAllItems();
		for (QuizQuestion question : questions) {
			loadQuestion.addItem(question.questionExplanation());
		}

		// Ensure the combo box resizes dynamically
		loadQuestion.setPrototypeDisplayValue("Select a Question...");
		this.revalidate();
		this.repaint();
	}
}
