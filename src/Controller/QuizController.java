package Controller;

import View.QuizView;
import Model.QuizSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizController implements ActionListener {

	private QuizView quizView;
	private QuizSet quizSet;

	public QuizController(QuizView quizView, QuizSet quizSet) {
		this.quizView = quizView;
		this.quizSet = quizSet;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "NEXT":
				// Logic for showing the next quiz question
				break;
			case "EXIT":
				// Logic for exiting the quiz
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}
}
