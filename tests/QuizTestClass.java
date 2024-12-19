import Model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTestClass {
    @Test
    void testCapitalizationQuestion() {
        CapitalizationQuestion question = new CapitalizationQuestion("TestAnswer");
        assertTrue(question.validateAnswer("TestAnswer"));
        assertFalse(question.validateAnswer("WrongAnswer"));
        assertEquals("Stellen Sie sicher, dass Ihre Antwort die gleiche Groß- und Kleinschreibung wie das Original hat.", question.questionExplanation());
    }

    @Test
    void testCompleteQuestion() {
        CompleteQuestion question = new CompleteQuestion("Haus", "H_us", "Haus");
        assertTrue(question.validateAnswer("Haus"));
        assertFalse(question.validateAnswer("Maus"));
        assertEquals("Ergänzen Sie das unvollständige Wort korrekt, basierend auf das verwandte Wort: Haus", question.questionExplanation());
    }

    @Test
    void testPictureQuestion() {
        PictureQuestion question = new PictureQuestion("http://example.com/image.jpg", "Cat");
        assertTrue(question.validateAnswer("Cat"));
        assertTrue(question.validateAnswer("cat")); // Case-insensitive
        assertFalse(question.validateAnswer("Dog"));
        assertEquals("Beschreiben Sie, was Sie auf dem Bild sehen. URL: http://example.com/image.jpg", question.questionExplanation());
    }

    @Test
    void testSSharpQuestion() {
        SSharpQuestion question = new SSharpQuestion("gehen", "Gehen");
        assertTrue(question.validateAnswer("gehen"));
        assertTrue(question.validateAnswer("Gehen"));
        assertFalse(question.validateAnswer("laufen"));
        assertFalse(question.validateAnswer("Geh"));
        assertEquals("Geben Sie die Infinitivform des Wortes ein: gehen", question.questionExplanation());
        assertEquals("SSharpQuestion", question.getType());
    }

}
