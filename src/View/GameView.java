package View;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameView extends JPanel {

	private JButton exit;
	private JButton next;
	private JTextField inputAnswer;
	private JLabel relatedWord;
	private JLabel uncompleteWord;
	private JLabel picture;
	private JLabel timer;

	public GameView() {
		FlatDarkLaf.setup();
		setLayout(new BorderLayout(10, 10));
		initializeComponents();
		layoutComponents();
	}

	private void initializeComponents() {
		exit = new JButton("Exit");
		next = new JButton("Next");
		inputAnswer = new JTextField(20);
		relatedWord = new JLabel("", SwingConstants.CENTER);
		uncompleteWord = new JLabel("", SwingConstants.CENTER);
		picture = new JLabel();
		timer = new JLabel("", SwingConstants.CENTER);

		// Styling components
		relatedWord.setFont(new Font("Arial", Font.BOLD, 16));
		uncompleteWord.setFont(new Font("Arial", Font.BOLD, 16));
		timer.setFont(new Font("Arial", Font.BOLD, 14));
		timer.setForeground(Color.LIGHT_GRAY);

		inputAnswer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
	}

	private void layoutComponents() {
		JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		topPanel.add(timer);
		topPanel.add(relatedWord);

		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		centerPanel.add(uncompleteWord, BorderLayout.CENTER);
		centerPanel.add(picture, BorderLayout.SOUTH);

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

	public void setPictureURL(URL url) {
		ImageIcon imageIcon = new ImageIcon(url);
		picture.setIcon(imageIcon);
	}

	public void setUncompleteWord(String word) {
		uncompleteWord.setText(word);
	}

	public void setTimer(int time) {
		timer.setText("Time Left: " + time + "s");
	}

	public void addHangmanWrong() {
		// Placeholder for adding visual feedback for wrong answers, e.g., updating a hangman image.
		picture.setText("Wrong attempt!");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Additional custom rendering can be added here if necessary.
	}

	public void endGameTimeLimit() {
		JOptionPane.showMessageDialog(this, "Time is up!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	}

	public JButton getExitButton() {
		return exit;
	}

	public JButton getNextButton() {
		return next;
	}
}
