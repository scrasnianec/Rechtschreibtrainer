package Controller;

import View.StatisticsView;
import Model.UserInfo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsController implements ActionListener {

	private StatisticsView statisticsView;
	private UserInfo userInfo;

	public StatisticsController(StatisticsView statisticsView, UserInfo userInfo) {
		this.statisticsView = statisticsView;
		this.userInfo = userInfo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "REFRESH":
				// Logic for refreshing statistics
				break;
			case "EXIT":
				// Logic for exiting the statistics view
				break;
			default:
				throw new UnsupportedOperationException("Unknown command: " + command);
		}
	}
}
