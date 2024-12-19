import static org.junit.jupiter.api.Assertions.*;

import Model.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

class SaveLoadQuizFileTest {

    private SaveLoadQuizFile saveLoadQuizFile;
    private String testFilePath = "data/test_questions.json"; // Temporary file for testing

    @BeforeEach
    void setUp() {
        saveLoadQuizFile = new SaveLoadQuizFile(testFilePath);
    }

    @Test
    void testLoadQuestions_FileExists() {
        // Prepare sample data
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new CapitalizationQuestion("Correct Answer"));
        questions.add(new CompleteQuestion("hören", "geh__", "Gehör"));
        questions.add(new PictureQuestion("http://example.com/image.jpg", "Katze"));
        questions.add(new SSharpQuestion("essen", "aß"));
        saveLoadQuizFile.saveQuestions(questions);

        // Test loading the questions
        List<QuizQuestion> loadedQuestions = saveLoadQuizFile.loadQuestions();

        assertNotNull(loadedQuestions, "Questions should not be null");
        assertEquals(4, loadedQuestions.size(), "There should be four questions loaded");
        assertTrue(loadedQuestions.get(0) instanceof CapitalizationQuestion, "Loaded question should be of type CapitalizationQuestion");
        assertTrue(loadedQuestions.get(1) instanceof CompleteQuestion, "Loaded question should be of type CompleteQuestion");
        assertTrue(loadedQuestions.get(2) instanceof PictureQuestion, "Loaded question should be of type PictureQuestion");
        assertTrue(loadedQuestions.get(3) instanceof SSharpQuestion, "Loaded question should be of type SSharpQuestion");
    }

    @Test
    void testLoadQuestions_FileNotExists() {
        // Test loading when file does not exist (ensure no exception is thrown)
        SaveLoadQuizFile invalidSaveLoadQuizFile = new SaveLoadQuizFile("invalid_path.json");
        List<QuizQuestion> loadedQuestions = invalidSaveLoadQuizFile.loadQuestions();

        assertNull(loadedQuestions, "Questions should be null when file does not exist");
    }

    @Test
    void testSaveQuestions() {
        // Prepare sample data
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new CompleteQuestion("related", "uncomplete", "complete"));

        // Save the questions
        saveLoadQuizFile.saveQuestions(questions);

        // Reload to check if the data is correctly saved
        List<QuizQuestion> loadedQuestions = saveLoadQuizFile.loadQuestions();

        assertNotNull(loadedQuestions, "Questions should not be null after saving and loading");
        assertEquals(1, loadedQuestions.size(), "There should be one question loaded");
        assertTrue(loadedQuestions.get(0) instanceof CompleteQuestion, "Loaded question should be of type CompleteQuestion");
    }

    @Test
    void testSaveQuestions_EmptyList() {
        // Test saving an empty list
        saveLoadQuizFile.saveQuestions(new ArrayList<>());

        // Reload and verify that the file is empty
        List<QuizQuestion> loadedQuestions = saveLoadQuizFile.loadQuestions();

        assertNotNull(loadedQuestions, "Questions should not be null");
        assertTrue(loadedQuestions.isEmpty(), "The list should be empty");
    }

    @AfterEach
    void testFileDeletionAfterTest() {
        // Clean up test file after running tests
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
