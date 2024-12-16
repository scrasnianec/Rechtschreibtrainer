package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfo implements Serializable {

	private int totalPoints;
	private List<Integer> pointHistory;
	private static final String FILE_PATH = "./data/userInfo.ser";

	public UserInfo() {
		loadUserInfo();
	}

	private void loadUserInfo() {
		File file = new File(FILE_PATH);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
				UserInfo loadedInfo = (UserInfo) ois.readObject();
				this.totalPoints = loadedInfo.totalPoints; // Load the saved totalPoints
				this.pointHistory = loadedInfo.pointHistory != null ? loadedInfo.pointHistory : new ArrayList<>();
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error loading user info: " + e.getMessage());
				this.totalPoints = 0; // Default value in case of error
				this.pointHistory = new ArrayList<>();
			}
		} else {
			this.totalPoints = 0; // Default value if no file exists
			this.pointHistory = new ArrayList<>();
		}
	}

	private void saveUserInfo() {
		File dir = new File("./data");
		if (!dir.exists()) {
			dir.mkdirs(); // Create the directory if it doesn't exist
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_PATH)))) {
			oos.writeObject(this); // Save the current object
		} catch (IOException e) {
			System.err.println("Error saving user info: " + e.getMessage());
		}
	}

	public void addPoints(int points) {
		this.totalPoints += points;
		this.pointHistory.add(points); // Add to point history
		saveUserInfo();
	}

	public int getLevel() {
		return (int) Math.floor(Math.log(totalPoints) / Math.log(2));
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public int percentToNextLevel() {
		int currentLevel = getLevel();
		int currentLevelPoints = (int) Math.pow(2, currentLevel); // Points at the start of the current level
		int nextLevelPoints = (int) Math.pow(2, currentLevel + 1); // Points required to reach the next level

		// Points accumulated within the current level
		int pointsInCurrentLevel = totalPoints - currentLevelPoints;

		// Total points required to progress to the next level
		int pointsToNextLevel = nextLevelPoints - currentLevelPoints;

		// Calculate percentage progress
		return (int) ((pointsInCurrentLevel / (double) pointsToNextLevel) * 100);
	}

	public List<Integer> getPointHistory() {
		return new ArrayList<>(pointHistory); // Return a copy to preserve encapsulation
	}
}
