import Model.QuizSet;
import Model.SaveLoadQuizFile;
import org.junit.jupiter.api.Test;
import View.*;
import Controller.*;

class ViewTest {
    public static void main(String[] args) {
        // Create the view
        Frame view = new Frame();
        QuizController quizController = new QuizController(new QuizSet(new SaveLoadQuizFile("data/mock_quiz_questions.json")));
        view.add(quizController.getView());

        // Set the view visible
        view.setVisible(true);

    }
}
