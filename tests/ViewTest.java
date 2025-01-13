import Model.QuizSet;
import Model.SaveLoadQuizFile;
import Model.UserInfo;
import org.junit.jupiter.api.Test;
import View.*;
import Controller.*;

class ViewTest {
    public static void main(String[] args) {
        // Create the view
        Frame view = new Frame();
        view.setSize(800, 600);

        // Initialize the application
        UserInfo userInfo = new UserInfo();
        StatisticsView statisticsView = new StatisticsView();

        SaveLoadQuizFile save = new SaveLoadQuizFile("data/mock_quiz_questions.json");
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(view);

    }
}
