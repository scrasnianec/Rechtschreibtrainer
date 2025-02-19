import Model.*;

import java.util.Arrays;

public class MockUserInfoFileCreator {

    public static void main(String[] args) {
        String mockFilePath = UserInfo.FILE_PATH;
        UserInfo mockUserInfo = new UserInfo();

        // Set mock data for the UserInfo object
        mockUserInfo.addPoints(5); // Add 10 points
        mockUserInfo.addPoints(4); // Add 20 points
        mockUserInfo.addPoints(0); // Add 30 points
        mockUserInfo.addPoints(5); // Add 30 points
        mockUserInfo.addPoints(5); // Add 30 points
        mockUserInfo.addPoints(4); // Add 30 points
        mockUserInfo.addPoints(3); // Add 30 points

        // Display total points and point history to verify
        System.out.println("Total Points: " + mockUserInfo.getTotalPoints());
        System.out.println("Point History: " + Arrays.toString(mockUserInfo.getPointHistory().toArray()));

        // Calculate and display the user level and percentage to the next level
        System.out.println("User Level: " + mockUserInfo.getLevel());
        System.out.println("Percent to Next Level: " + mockUserInfo.percentToNextLevel() + "%");

        System.out.println("Mock UserInfo data saved at: " + mockFilePath);
    }
}
