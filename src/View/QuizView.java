package View;

import Controller.QuizController;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class QuizView extends JPanel {

	private JButton exit;
	private JButton next;
	private JTextField inputAnswer;
	private JLabel relatedWord;
	private JLabel uncompleteWord;
	private JLabel picture;

	public QuizView(QuizController quizController) {
		FlatDarkLaf.setup();
		setLayout(new BorderLayout(10, 10));
		initializeComponents();
		layoutComponents();

		// Add action listeners
		exit.addActionListener(quizController);
		next.addActionListener(quizController);
		inputAnswer.addActionListener(quizController);

		// Set action commands
		exit.setActionCommand("EXIT");
		next.setActionCommand("NEXT");
		inputAnswer.setActionCommand("NEXT");
	}

	private void initializeComponents() {
		exit = new JButton("Exit");
		next = new JButton("Next");
		inputAnswer = new JTextField(20);
		relatedWord = new JLabel("", SwingConstants.CENTER);
		uncompleteWord = new JLabel("", SwingConstants.CENTER);
		picture = new JLabel();

		// Styling components
		relatedWord.setFont(new Font("Arial", Font.BOLD, 16));
		uncompleteWord.setFont(new Font("Arial", Font.BOLD, 16));

		inputAnswer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	}

	private void layoutComponents() {
		JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		topPanel.add(relatedWord);
		topPanel.add(uncompleteWord);

		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		centerPanel.add(picture, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
		bottomPanel.add(inputAnswer, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.add(next);
		buttonPanel.add(exit);

		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	public String getAnswerInput() {
		return inputAnswer.getText();
	}

	public void setRelatedWord(String word) {
		relatedWord.setText(word);
	}

	public void setUncompleteWord(String word) {
		uncompleteWord.setText(word);
	}

	public void setPictureURL(URL url) {
		if (url != null) {
			ImageIcon imageIcon = new ImageIcon(url);
			picture.setIcon(imageIcon);
		} else {
			picture.setIcon(null); // Clear the icon if the URL is null
		}
	}



	public JButton getExitButton() {
		return exit;
	}

	public JButton getNextButton() {
		return next;
	}

	public Object getInputAnswerField() {
		return inputAnswer;
	}
}
