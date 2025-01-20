package View;

import Controller.QuizController;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
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
	private JLabel question;
	private JLabel picture;
	private JLabel loadingLabel; // New label for loading indicator
	private JLabel feedbackMessage; // New label for feedback messages

	// Simple image cache
	private static final Map<URL, ImageIcon> imageCache = new HashMap<>();
	private BufferedImage originalImage; // To store the original image

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

		question = new JLabel("DEBUG", SwingConstants.CENTER);
		inputAnswer = new JTextField(20);
		picture = new JLabel();
		picture.setHorizontalAlignment(SwingConstants.CENTER);
		picture.setVerticalAlignment(SwingConstants.CENTER);

		loadingLabel = new JLabel("Loading image...", SwingConstants.CENTER);
		loadingLabel.setVisible(false); // Initially hidden

		// Styling components
		question.setFont(new Font("Arial", Font.BOLD, 16));

		inputAnswer.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY, 1),
				BorderFactory.createEmptyBorder(2, 2, 2, 2)));

		feedbackMessage = new JLabel("DEBUG", SwingConstants.CENTER); // Initialize feedback message label
		feedbackMessage.setFont(new Font("Arial", Font.BOLD, 14));
	}

	private void layoutComponents() {
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(question, BorderLayout.NORTH);
		topPanel.add(feedbackMessage, BorderLayout.SOUTH); // Add feedback message below the question

		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		centerPanel.add(picture, BorderLayout.CENTER);
		centerPanel.add(loadingLabel, BorderLayout.SOUTH); // Add loading label below the picture

		JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
		bottomPanel.add(new JPanel(new FlowLayout()).add(inputAnswer), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.add(next);
		buttonPanel.add(exit);

		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

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

	/**
	 * Asynchronously loads and sets an image from the provided URL.
	 * If the URL is null, hides the picture component.
	 *
	 * @param url The URL of the image to load.
	 */
	public void setPictureURL(String url) {
		// Hide picture and loading label if no URL is provided
		if (url == null || url.isEmpty()) {
			picture.setVisible(false);
			loadingLabel.setVisible(false);
			return;
		}

		// Clear any existing image
		picture.setIcon(null);
		loadingLabel.setVisible(false);
		originalImage = null; // Reset original image

		picture.setVisible(true); // Show picture space if URL is provided
		loadingLabel.setVisible(true); // Show loading indicator

		if (imageCache.containsKey(url)) {
			// Use cached image
			picture.setIcon(imageCache.get(url));
			loadingLabel.setVisible(false);
			return;
		}

		// Use SwingWorker to load the image in the background
		new SwingWorker<BufferedImage, Void>() {
			@Override
			protected BufferedImage doInBackground() throws Exception {
				try {
					// Read the image from the URL
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
						// Scale the image to fit the label while maintaining aspect ratio
						Image scaledImage = getScaledImage(originalImage, picture.getWidth(), picture.getHeight());
						ImageIcon imageIcon = new ImageIcon(scaledImage);
						picture.setIcon(imageIcon);
						// Cache the loaded image
						imageCache.put(new URL(url), imageIcon);
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

		// Add a component listener to handle resizing
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

	/**
	 * Scales an image to fit within the specified width and height while maintaining aspect ratio.
	 *
	 * @param srcImg The source BufferedImage.
	 * @param maxWidth The maximum width.
	 * @param maxHeight The maximum height.
	 * @return The scaled Image.
	 */
	private Image getScaledImage(BufferedImage srcImg, int maxWidth, int maxHeight) {
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();

		double widthRatio = (double) maxWidth / srcWidth;
		double heightRatio = (double) maxHeight / srcHeight;
		double scale = Math.min(widthRatio, heightRatio);

		if (scale > 1.0) {
			scale = 1.0; // Don't scale up images
		}

		int newWidth = (int) (srcWidth * scale);
		int newHeight = (int) (srcHeight * scale);

		return srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	}

	public void setOnlyQuestion(String questionText) {
		// Set the question text
		question.setText(questionText);
		feedbackMessage.setText(errorMessage);

		// Update layout for only question display
		removeAll(); // Clear existing components

		// Add question to the center of the BorderLayout
		JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		centerPanel.add(question, BorderLayout.NORTH); // Center the question
		centerPanel.add(feedbackMessage, BorderLayout.SOUTH); // Add feedback message below the question

		// Add input and buttons at the bottom
		JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
		bottomPanel.add(new JPanel(new FlowLayout()).add(inputAnswer), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.add(next);
		buttonPanel.add(exit);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Re-add panels to the layout
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// Refresh UI
		revalidate();
		repaint();
	}

	public void setPictureQuestion(String questionText, String imageURL) {
		// Set the question text
		question.setText(questionText);
		feedbackMessage.setText(errorMessage);

		// Update layout for question and picture
		removeAll(); // Clear existing components

		JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		topPanel.add(question, BorderLayout.NORTH); // Question at the top
		topPanel.add(feedbackMessage, BorderLayout.SOUTH); // Feedback message below the question

		JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
		centerPanel.add(picture, BorderLayout.CENTER);
		centerPanel.add(loadingLabel, BorderLayout.SOUTH); // Add loading label below picture

		// Set picture from URL
		setPictureURL(imageURL);

		// Add input and buttons at the bottom
		JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
		bottomPanel.add(new JPanel(new FlowLayout()).add(inputAnswer), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.add(next);
		buttonPanel.add(exit);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Re-add panels to the layout
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// Refresh UI
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

	private String errorMessage = "";

	public void setFeedbackMessage(String message) {
		feedbackMessage.setText(message);
		errorMessage = message; // Keep the variable for debugging purposes, if needed
		revalidate();
		repaint(); // Ensure the UI reflects changes
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

}
