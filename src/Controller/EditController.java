package Controller;

import View.EditView;
import Model.SaveLoadQuizFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditController implements ActionListener {

	private EditView editView;
	private SaveLoadQuizFile saveLoad;
	private MainMenuController mainMenuController;


	public EditController(EditView editView, SaveLoadQuizFile saveLoad, MainMenuController mainMenuController) {
		this.editView = editView;
		this.saveLoad = saveLoad;
		this.mainMenuController = mainMenuController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "LOAD":
				// Logic for loading questions
				break;
			case "SAVE":
				// Logic for saving questions
				break;
			case "NEW":
				// Logic for creating a new question
				break;
			case "EXIT":
				stopEditor();
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}

	public void startEditor() {
		mainMenuController.hideMainMenu();
		mainMenuController.addPanel(editView);
		editView.setVisible(true);
	}

	public void stopEditor() {
		mainMenuController.showMainMenu();
		mainMenuController.removePanel(editView);
	}


}
