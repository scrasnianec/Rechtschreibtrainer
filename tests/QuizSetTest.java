import Model.DataPath;
import Model.QuizQuestion;
import Model.QuizSet;
import Model.SaveLoadQuizFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class QuizSetTest {

    private QuizSet quizSet;
    private SaveLoadQuizFile saveLoadQuizFile;

    @BeforeEach
    public void setUp() {
        // Mock-Dateipfad zur JSON-Datei
        if (!(new File(DataPath.QUIZ_PATH).exists())) throw new RuntimeException("Mock File not Found");
        saveLoadQuizFile = new SaveLoadQuizFile();
        quizSet = new QuizSet();
    }

    @Test
    public void testLoadQuestionsFromFile() {
        List<QuizQuestion> questions = saveLoadQuizFile.loadQuestions();
        assertNotNull(questions, "Die Fragenliste sollte nicht null sein.");
        assertFalse(questions.isEmpty(), "Die Fragenliste sollte nicht leer sein.");
    }

    @Test
    public void testGetRandomQuestionFromSet() {
        QuizQuestion randomQuestion = quizSet.getRandomQuestionFromSet();
        assertNotNull(randomQuestion, "Es sollte eine zufällige Frage aus dem Set zurückgegeben werden.");
        assertTrue(randomQuestion instanceof QuizQuestion, "Das zurückgegebene Objekt sollte eine QuizQuestion sein.");
    }

    @Test
    public void testAddHistoryEntry() {
        quizSet.addHistoryEntry(true);
        quizSet.addHistoryEntry(false);
        quizSet.addHistoryEntry(true);
        // Keine direkte Methode zum Testen von setHistory, aber wir können calculatePointsEarned testen
        int points = quizSet.calculatePointsEarned();
        assertEquals(2, points, "Die Punkteberechnung sollte 2 betragen (2x true = 2 Punkte).");
    }

    @Test
    public void testCalculatePointsEarned() {
        quizSet.addHistoryEntry(true);
        quizSet.addHistoryEntry(true);
        quizSet.addHistoryEntry(false);
        quizSet.addHistoryEntry(false);
        int points = quizSet.calculatePointsEarned();
        assertEquals(2, points, "Die Punkteberechnung sollte 2 betragen (2x true = 2 Punkte).");
    }
}
