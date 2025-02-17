import View.*;
import Controller.*;

class ViewTest {
    public static void main(String[] args) {
        // Create Mockfile
//        MockQuizFileCreator.main(null);
//        MockUserInfoFileCreator.main(null);

        // Create the view
        Frame view = new Frame();
        view.setSize(800, 600);

        // Initialize the application
        MainMenuController mainMenuController = new MainMenuController(view);
    }
}
