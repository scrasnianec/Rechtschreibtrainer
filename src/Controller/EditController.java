package Controller;

import View.EditView;
import Model.SaveLoadQuizFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditController implements ActionListener {

	private EditView editView;
	private SaveLoadQuizFile saveLoad;

	public EditController(EditView editView, SaveLoadQuizFile saveLoad) {
		this.editView = editView;
		this.saveLoad = saveLoad;
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
				// Logic for exiting the editor
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}
}
