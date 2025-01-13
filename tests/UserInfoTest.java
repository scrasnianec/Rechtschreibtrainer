import Model.UserInfo;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

class UserInfoTest {

    private static final String FILE_PATH = ".data/userInfo.ser";

    @BeforeEach
    void setUp() {
        // Delete the file before each test to ensure a clean environment
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void tearDown() {
        // Clean up the file after each test
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testInitialTotalPointsWithNoFile() {
        UserInfo userInfo = new UserInfo();
        Assertions.assertEquals(0, userInfo.getPointHistory().size(), "Point history should be empty when no file exists.");
    }

    @Test
    void testAddPoints() {
        UserInfo userInfo = new UserInfo();
        userInfo.addPoints(10);
        List<Integer> pointHistory = userInfo.getPointHistory();
        Assertions.assertEquals(1, pointHistory.size(), "Point history should have one entry.");
        Assertions.assertEquals(10, pointHistory.get(0), "Points added should match the first entry in point history.");
    }

    @Test
    void testPersistenceAfterAddingPoints() {
        UserInfo userInfo = new UserInfo();
        userInfo.addPoints(20);

        // Create a new UserInfo instance to simulate loading from file
        UserInfo loadedUserInfo = new UserInfo();
        List<Integer> pointHistory = loadedUserInfo.getPointHistory();
        Assertions.assertEquals(1, pointHistory.size(), "Point history should persist after saving and loading.");
        Assertions.assertEquals(20, pointHistory.get(0), "Persisted point history should match the added points.");
    }

    @Test
    void testLoadUserInfoWhenFileExists() {
        UserInfo original = new UserInfo();
        original.addPoints(50);

        // Reload data
        UserInfo loaded = new UserInfo();
        List<Integer> pointHistory = loaded.getPointHistory();
        Assertions.assertEquals(1, pointHistory.size(), "Loaded point history size should match.");
        Assertions.assertEquals(50, pointHistory.get(0), "Loaded point history should match saved points.");
    }

    @Test
    void testGetLevel() {
        UserInfo userInfo = new UserInfo();
        userInfo.addPoints(16); // 2^4 points
        Assertions.assertEquals(4, userInfo.getLevel(), "Level calculation should be correct.");
    }

    @Test
    void testPercentToNextLevel() {
        UserInfo userInfo = new UserInfo();
        userInfo.addPoints(18); // Between levels 4 (16 points) and 5 (32 points)

        int expectedPercentage = (int) ((2 / 16.0) * 100); // 2 points into the next level out of 16
        Assertions.assertEquals(expectedPercentage, userInfo.percentToNextLevel(), "Percentage to next level should be correct.");
    }

    @Test
    void testSaveUserInfoCreatesFile() {
        UserInfo userInfo = new UserInfo();
        userInfo.addPoints(10);

        File file = new File(FILE_PATH);
        Assertions.assertTrue(file.exists(), "File should be created after saving user info.");
    }

    @Test
    void testSaveUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.addPoints(10);



    }

    @Test
    void testErrorHandlingDuringLoad() {
        // Create an invalid file
        File file = new File(FILE_PATH);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Invalid data");
        } catch (IOException e) {
            Assertions.fail("Failed to set up invalid file for testing.");
        }

        UserInfo userInfo = new UserInfo();
        Assertions.assertEquals(0, userInfo.getPointHistory().size(), "Point history should be empty on load error.");
    }

    @Test
    void testErrorHandlingDuringSave() {
        // Create a directory instead of a file to simulate save error
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        file.mkdir();

        UserInfo userInfo = new UserInfo();
        Assertions.assertDoesNotThrow(() -> userInfo.addPoints(10), "Saving should not throw exception even if error occurs.");

        // Cleanup
        file.delete();
    }
}