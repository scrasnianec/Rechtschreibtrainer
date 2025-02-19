import View.*;
import Controller.*;

class ViewTest {
    public static void main(String[] args) {
        Frame view = new Frame();
        view.setSize(800, 600);

        MainMenuController mainMenuController = new MainMenuController(view);
    }
}
