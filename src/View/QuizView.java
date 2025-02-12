package View;

import Controller.QuizController;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class QuizView extends JPanel {

	private JButton exit;
	private JButton next;
	private JTextField inputAnswer;

	// Changed from JLabel to JTextArea:
	private JTextPane question;


	private JLabel picture;
	private JLabel loadingLabel;
	private JLabel feedbackMessage;

	// Simple image cache
	private static final Map<URL, ImageIcon> imageCache = new HashMap<>();
	private BufferedImage originalImage;

	private String errorMessage = "";

	public QuizView(QuizController quizController) {
		FlatDarkLaf.setup();
		setLayout(new BorderLayout(2, 2));
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
		// Initialize JTextPane for the question with center alignment:
		question = new JTextPane();question = new JTextPane();
		question.setEditable(false);
		question.setOpaque(false);

		StyledDocument doc = question.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		question.setFont(new Font("Arial", Font.BOLD, 16));
		question.setText("DEBUG");
		question.setEditable(false);
		question.setOpaque(false);
		question.setFont(new Font("Arial", Font.BOLD, 16));

		inputAnswer = new JTextField(20);

		picture = new JLabel();
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setVerticalAlignment(SwingConstants.CENTER);

		loadingLabel = new JLabel("Loading image...", SwingConstants.CENTER);
		loadingLabel.setVisible(false);

		feedbackMessage = new JLabel("DEBUG", SwingConstants.CENTER);
		feedbackMessage.setFont(new Font("Arial", Font.BOLD, 14));

		// Optional: style the input field
		inputAnswer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(2, 2, 2, 2)));
	}

	private void layoutComponents() {
		// Top panel with question text and feedback message
		JPanel topPanel = new JPanel(new BorderLayout(5, 5));
		topPanel.add(question, BorderLayout.CENTER);           // Place JTextArea in the center
		topPanel.add(feedbackMessage, BorderLayout.SOUTH);     // Feedback message below

		// Center panel with image and loading label
		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		centerPanel.add(picture, BorderLayout.CENTER);
		centerPanel.add(loadingLabel, BorderLayout.SOUTH);

		// Bottom panel with input field and buttons
		JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
		bottomPanel.add(new JPanel(new FlowLayout()).add(inputAnswer), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.add(next);
		buttonPanel.add(exit);

		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add panels to the main panel
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// Initially hide the picture and loading label
		picture.setVisible(false);
		loadingLabel.setVisible(false);
	}

	public String getAnswerInput() {
		return inputAnswer.getText();
	}

	public void setPictureURL(String url) {
		if (url == null || url.isEmpty()) {
			picture.setVisible(false);
			loadingLabel.setVisible(false);
			return;
		}

		picture.setIcon(null);
		loadingLabel.setVisible(false);
		originalImage = null;

		picture.setVisible(true);
		loadingLabel.setVisible(true);

		// Check cache
		try {
			URL imageUrl = new URL(url);
			if (imageCache.containsKey(imageUrl)) {
				picture.setIcon(imageCache.get(imageUrl));
				loadingLabel.setVisible(false);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			picture.setText("Failed to load image.");
			loadingLabel.setVisible(false);
			return;
		}

		// Load image in the background
		new SwingWorker<BufferedImage, Void>() {
			@Override
			protected BufferedImage doInBackground() throws Exception {
				try {
					return ImageIO.read(new URL(url));
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}

			@Override
			protected void done() {
				try {
					originalImage = get();
					if (originalImage != null) {
						Image scaledImage = getScaledImage(originalImage, picture.getWidth(), picture.getHeight());
						ImageIcon icon = new ImageIcon(scaledImage);
						picture.setIcon(icon);
						// Cache
						imageCache.put(new URL(url), icon);
					} else {
						picture.setText("Image not available.");
					}
				} catch (Exception e) {
					System.err.println("Error loading image: " + e.getMessage());
					picture.setText("Failed to load image.");
				} finally {
					loadingLabel.setVisible(false);
				}
			}
		}.execute();

		// Handle resizing
		picture.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (originalImage != null) {
					Image scaledImage = getScaledImage(originalImage, picture.getWidth(), picture.getHeight());
					picture.setIcon(new ImageIcon(scaledImage));
				}
			}
		});
	}

	private Image getScaledImage(BufferedImage srcImg, int maxWidth, int maxHeight) {
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();

		double widthRatio = (double) maxWidth / srcWidth;
		double heightRatio = (double) maxHeight / srcHeight;
		double scale = Math.min(widthRatio, heightRatio);

		if (scale > 1.0) {
			scale = 1.0; // don't scale up images
		}

		int newWidth = (int) (srcWidth * scale);
		int newHeight = (int) (srcHeight * scale);

		return srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	}

	public void setOnlyQuestion(String questionText) {
		question.setText(questionText);
		feedbackMessage.setText(errorMessage);

		removeAll();

		// Show question + feedback in the center
		JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		centerPanel.add(question, BorderLayout.CENTER);       // Our JTextArea
		centerPanel.add(feedbackMessage);

		// Bottom panel with input and buttons
		JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
		bottomPanel.add(new JPanel(new FlowLayout()).add(inputAnswer), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.add(next);
		buttonPanel.add(exit);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		revalidate();
		repaint();
	}

	public void setPictureQuestion(String questionText, String imageURL) {
		question.setText(questionText);
		feedbackMessage.setText(errorMessage);

		removeAll();

		JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		topPanel.add(question, BorderLayout.CENTER);
		topPanel.add(feedbackMessage);

		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		centerPanel.add(picture, BorderLayout.CENTER);
		centerPanel.add(loadingLabel, BorderLayout.SOUTH);

		// Set picture from URL
		setPictureURL(imageURL);

		// Bottom panel with input and buttons
		JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
		bottomPanel.add(new JPanel(new FlowLayout()).add(inputAnswer), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.add(next);
		buttonPanel.add(exit);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		revalidate();
		repaint();
	}

	public JButton getExitButton() {
		return exit;
	}

	public JButton getNextButton() {
		return next;
	}

	public JTextField getInputAnswerField() {
		return inputAnswer;
	}

	public void clearInput() {
		inputAnswer.setText("");
	}

	public void setFeedbackMessage(String message) {
		feedbackMessage.setText(message);
		errorMessage = message;
		revalidate();
		repaint();
	}

	public void addToFeedbackMessage(String message) {
		feedbackMessage.setText(feedbackMessage.getText() + "\n" + message);
	}

	public void setMessageColor(Color color) {
		feedbackMessage.setForeground(color);
	}

	public void clearFeedbackMessage() {
		feedbackMessage.setText("");
	}

	public void setFocusToInput() {
		inputAnswer.requestFocus();
	}

	public void enableRestartQuizButton() {
		next.setText("Restart Quiz");
		next.setActionCommand("RESTART_QUIZ");
	}

	public void resetNextButton() {
		next.setText("Next");
		next.setActionCommand("NEXT");
	}
}
