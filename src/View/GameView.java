package View;

import Controller.GameController;
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

public class GameView extends JPanel {

    // === Quiz Components ===
    private JButton exit;
    private JButton next;
    private JTextField inputAnswer;
    private JTextPane question;
    private JTextPane feedbackMessage;

    // Components for picture questions (optional)
    private JLabel picture;
    private JLabel loadingLabel;
    private BufferedImage originalImage;
    private static final Map<URL, ImageIcon> imageCache = new HashMap<>();

    // === Hangman Panel ===
    private HangmanPanel hangmanPanel;

    public GameView(GameController gameController) {
        FlatDarkLaf.setup();
        setLayout(new BorderLayout(5, 5));

        initializeQuizComponents();
        JPanel quizPanel = createQuizPanel();

        // Create the hangman drawing panel
        hangmanPanel = new HangmanPanel();

        // Add hangman panel (on the left) and quiz panel (center)
        add(hangmanPanel, BorderLayout.WEST);
        add(quizPanel, BorderLayout.CENTER);

        // Wire controller listeners
        exit.addActionListener(gameController);
        next.addActionListener(gameController);
        inputAnswer.addActionListener(gameController);

        exit.setActionCommand("EXIT");
        next.setActionCommand("NEXT");
        inputAnswer.setActionCommand("NEXT");
    }

    private void initializeQuizComponents() {
        exit = new JButton("Exit");
        next = new JButton("Next");

        // Question label
        question = new JTextPane();
        question.setEditable(false);
        question.setOpaque(false);
        question.setFont(new Font("Arial", Font.BOLD, 16));

        StyledDocument docQuestion = question.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        docQuestion.setParagraphAttributes(0, docQuestion.getLength(), center, false);

        // Feedback message label
        feedbackMessage = new JTextPane();
        feedbackMessage.setEditable(false);
        feedbackMessage.setOpaque(false);
        feedbackMessage.setFont(new Font("Arial", Font.BOLD, 16));

        StyledDocument docFeedback = feedbackMessage.getStyledDocument();
        docFeedback.setParagraphAttributes(0, docFeedback.getLength(), center, false);

        inputAnswer = new JTextField(20);
        inputAnswer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(2, 2, 2, 2)));

        // Picture component for picture questions
        picture = new JLabel();
        picture.setHorizontalAlignment(SwingConstants.CENTER);
        picture.setVerticalAlignment(SwingConstants.CENTER);
        picture.setVisible(false);

        loadingLabel = new JLabel("Loading image...", SwingConstants.CENTER);
        loadingLabel.setVisible(false);
    }

    private JPanel createQuizPanel() {
        JPanel quizPanel = new JPanel(new BorderLayout(5, 5));

        // Top panel for the question, picture (if any), and feedback message
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(question, BorderLayout.NORTH);
        topPanel.add(picture, BorderLayout.CENTER);
        topPanel.add(feedbackMessage, BorderLayout.SOUTH);

        // Bottom panel for answer input and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(inputAnswer);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(next);
        buttonPanel.add(exit);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        quizPanel.add(topPanel, BorderLayout.NORTH);
        quizPanel.add(bottomPanel, BorderLayout.SOUTH);
        return quizPanel;
    }

    // --- Methods for picture questions ---
    public void setOnlyQuestion(String questionText) {
        question.setText(questionText);
        picture.setVisible(false);
        loadingLabel.setVisible(false);
    }

    public void setPictureQuestion(String questionText, String imageURL) {
        question.setText(questionText);
        picture.setVisible(true);
        setPictureURL(imageURL);
    }

    public void setPictureURL(String url) {
        if (url == null || url.isEmpty()) {
            picture.setVisible(false);
            loadingLabel.setVisible(false);
            return;
        }
        picture.setIcon(null);
        loadingLabel.setVisible(true);
        originalImage = null;
        picture.setVisible(true);

        try {
            URL imageUrl = new URL(url);
            if (imageCache.containsKey(imageUrl)) {
                picture.setIcon(imageCache.get(imageUrl));
                loadingLabel.setVisible(false);
                return;
            }
            // Use SwingWorker to load image asynchronously
            new SwingWorker<BufferedImage, Void>() {
                @Override
                protected BufferedImage doInBackground() throws Exception {
                    return ImageIO.read(imageUrl);
                }

                @Override
                protected void done() {
                    try {
                        originalImage = get();
                        if (originalImage != null) {
                            // Use invokeLater to ensure layout is complete.
                            SwingUtilities.invokeLater(() -> {
                                int width = picture.getWidth();
                                int height = picture.getHeight();

                                // Fallback if component size is not yet available.
                                if (width <= 0 || height <= 0) {
                                    Dimension pref = picture.getPreferredSize();
                                    width = (pref.width > 0) ? pref.width : 400;
                                    height = (pref.height > 0) ? pref.height : 400;
                                }

                                Image scaledImage = getScaledImage(originalImage, width, height);
                                ImageIcon imageIcon = new ImageIcon(scaledImage);
                                picture.setIcon(imageIcon);
                                imageCache.put(imageUrl, imageIcon);
                            });
                        } else {
                            picture.setText("Image not available.");
                        }
                    } catch (Exception e) {
                        picture.setText("Failed to load image.");
                    } finally {
                        loadingLabel.setVisible(false);
                    }
                }
            }.execute();

            picture.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    if (originalImage != null) {
                        Image scaledImage = getScaledImage(originalImage, picture.getWidth(), picture.getHeight());
                        picture.setIcon(new ImageIcon(scaledImage));
                    }
                }
            });
        } catch (Exception ex) {
            picture.setText("Invalid image URL.");
            loadingLabel.setVisible(false);
        }
    }

    private Image getScaledImage(BufferedImage srcImg, int maxWidth, int maxHeight) {
        int srcWidth = srcImg.getWidth();
        int srcHeight = srcImg.getHeight();
        double widthRatio = (double) maxWidth / srcWidth;
        double heightRatio = (double) maxHeight / srcHeight;
        double scale = Math.min(widthRatio, heightRatio);
        if (scale > 1.0) {
            scale = 1.0;
        }
        int newWidth = (int) (srcWidth * scale);
        int newHeight = (int) (srcHeight * scale);
        return srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    // --- Methods for quiz interaction ---
    public String getAnswerInput() {
        return inputAnswer.getText();
    }

    public void clearInput() {
        inputAnswer.setText("");
    }

    public void setFeedbackMessage(String message) {
        feedbackMessage.setText(message);
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

    public JButton getExitButton() {
        return exit;
    }

    public JButton getNextButton() {
        return next;
    }

    public JTextField getInputField() {
        return inputAnswer;
    }

    public void enableRestartQuizButton() {
        next.setText("Restart Quiz");
        next.setActionCommand("RESTART_QUIZ");
    }

    public void resetNextButton() {
        next.setText("Next");
        next.setActionCommand("NEXT");
    }

    // --- Methods for Hangman ---
    public void addHangmanStep() {
        hangmanPanel.addStep();
    }

    public void resetHangman() {
        hangmanPanel.reset();
    }

    // --- Inner Class: HangmanPanel ---
    private class HangmanPanel extends JPanel {
        private int steps = 0;
        private final int MAX_STEPS = 6;

        public HangmanPanel() {
            setPreferredSize(new Dimension(300, 400));
            setBackground(Color.WHITE);
        }

        public void addStep() {
            if (steps < MAX_STEPS) {
                steps++;
                repaint();
            }
        }

        public void reset() {
            steps = 0;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int baseX = panelWidth / 4;
            int baseY = panelHeight * 3 / 4;
            int poleTopY = baseY - panelHeight / 2;
            int beamEndX = baseX + panelWidth / 2;

            // Draw gallows:
            g2.drawLine(baseX - 20, baseY, baseX + 20, baseY);
            g2.drawLine(baseX, baseY, baseX, poleTopY);
            g2.drawLine(baseX, poleTopY, beamEndX, poleTopY);
            g2.drawLine(beamEndX, poleTopY, beamEndX, poleTopY + 30);

            int headRadius = 20;
            int headCenterX = beamEndX;
            int headCenterY = poleTopY + 30 + headRadius;

            if (steps >= 1) { // head
                g2.drawOval(headCenterX - headRadius, headCenterY - headRadius, headRadius * 2, headRadius * 2);
            }
            if (steps >= 2) { // body
                int bodyStartY = headCenterY + headRadius;
                int bodyEndY = bodyStartY + 50;
                g2.drawLine(headCenterX, bodyStartY, headCenterX, bodyEndY);
            }
            if (steps >= 3) { // left arm
                int bodyY = headCenterY + headRadius + 10;
                g2.drawLine(headCenterX, bodyY, headCenterX - 20, bodyY + 20);
            }
            if (steps >= 4) { // right arm
                int bodyY = headCenterY + headRadius + 10;
                g2.drawLine(headCenterX, bodyY, headCenterX + 20, bodyY + 20);
            }
            if (steps >= 5) { // left leg
                int bodyEndY = headCenterY + headRadius + 50;
                g2.drawLine(headCenterX, bodyEndY, headCenterX - 20, bodyEndY + 30);
            }
            if (steps >= 6) { // right leg
                int bodyEndY = headCenterY + headRadius + 50;
                g2.drawLine(headCenterX, bodyEndY, headCenterX + 20, bodyEndY + 30);
            }
        }
    }
}
