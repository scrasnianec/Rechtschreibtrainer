package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfo implements Serializable {

	private int totalPoints;
	private List<Integer> pointHistory;
	public static final String FILE_PATH = "./resources/userInfo.ser";

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
		File dir = new File("./resources");
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

	public int getTotalPoints() {
		return totalPoints;
	}

	public int getLevel() {
		if (totalPoints == 0) return 0; // Avoid division by zero
		if (totalPoints < 38) {
			return (int) Math.floor(Math.log(totalPoints) / Math.log(2));
		} else {
			double a = 20.0;
			double b = 1.13;
			return (int) Math.floor(Math.log(totalPoints / a) / Math.log(b));
		}
	}

	public int percentToNextLevel() {
		if (totalPoints == 0) return 0; // Avoid division by zero
		int currentLevel = getLevel();

		if (totalPoints < 38) {
			// For totalPoints < 38, using the base-2 formula:
			int currentLevelPoints = (int) Math.pow(2, currentLevel);
			int nextLevelPoints = (int) Math.pow(2, currentLevel + 1);
			int pointsInCurrentLevel = totalPoints - currentLevelPoints;
			int pointsToNextLevel = nextLevelPoints - currentLevelPoints;
			return (int) ((pointsInCurrentLevel / (double) pointsToNextLevel) * 100);
		} else {
			// For totalPoints >= 38, using the 'a' and 'b' formula:
			double a = 20.0;
			double b = 1.13;
			double currentLevelPoints = a * Math.pow(b, currentLevel);
			double nextLevelPoints = a * Math.pow(b, currentLevel + 1);
			double pointsInCurrentLevel = totalPoints - currentLevelPoints;
			double pointsToNextLevel = nextLevelPoints - currentLevelPoints;
			return (int) ((pointsInCurrentLevel / pointsToNextLevel) * 100);
		}
	}


	public List<Integer> getPointHistory() {
		return new ArrayList<>(pointHistory); // Return a copy to preserve encapsulation
	}
}
