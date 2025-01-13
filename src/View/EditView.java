package View;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;

public class EditView extends JPanel {

	private JComboBox<String> questionType;
	private JTextField inputAnswer;
	private JTextField relatedWord;
	private JTextField uncompleteWord;
	private JTextField pictureURL;

	private JButton exit;
	private JButton save;
	private JButton newF;
	private JButton load;
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
		save = new JButton("Save");
		newF = new JButton("New Question");
		load = new JButton("Load File");
		loadQuestion = new JComboBox<>();

		// Style components
		questionType.setFont(new Font("Arial", Font.PLAIN, 14));
		inputAnswer.setFont(new Font("Arial", Font.PLAIN, 14));
		relatedWord.setFont(new Font("Arial", Font.PLAIN, 14));
		uncompleteWord.setFont(new Font("Arial", Font.PLAIN, 14));
		pictureURL.setFont(new Font("Arial", Font.PLAIN, 14));

		exit.setFont(new Font("Arial", Font.BOLD, 14));
		save.setFont(new Font("Arial", Font.BOLD, 14));
		newF.setFont(new Font("Arial", Font.BOLD, 14));
		load.setFont(new Font("Arial", Font.BOLD, 14));
		loadQuestion.setFont(new Font("Arial", Font.PLAIN, 14));

		// Set border color and padding
		inputAnswer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		relatedWord.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		uncompleteWord.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		pictureURL.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		exit.setActionCommand("EXIT");
		save.setActionCommand("SAVE");
		newF.setActionCommand("NEW");
		load.setActionCommand("LOAD");
	}

	private void layoutComponents() {
		JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

		inputPanel.add(createLabeledComponent("Question Type:", questionType));
		inputPanel.add(createLabeledComponent("Answer Input:", inputAnswer));
		inputPanel.add(createLabeledComponent("Related Word:", relatedWord));
		inputPanel.add(createLabeledComponent("Uncomplete Word:", uncompleteWord));
		inputPanel.add(createLabeledComponent("Picture URL:", pictureURL));

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
		buttonPanel.add(load);
		buttonPanel.add(newF);
		buttonPanel.add(save);
		buttonPanel.add(exit);

		JPanel loadQuestionPanel = new JPanel(new BorderLayout());
		loadQuestionPanel.add(new JLabel("Loaded Questions:"), BorderLayout.NORTH);
		loadQuestionPanel.add(loadQuestion, BorderLayout.CENTER);

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		add(loadQuestionPanel, BorderLayout.EAST);
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

	public JButton getLoadButton() {
		return load;
	}

	public JComboBox<String> getLoadQuestionComboBox() {
		return loadQuestion;
	}
}
