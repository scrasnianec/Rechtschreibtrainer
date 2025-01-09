import Model.*;

import java.util.ArrayList;
import java.util.List;

public class MockQuizFileCreator {

    public static void main(String[] args) {
        final String url = "https://loremflickr.com/320/240";
        // Pfad zur Mock-Datei
        String mockFilePath = "./data/mock_quiz_questions.json";

        // Instanziiere SaveLoadQuizFile mit dem Pfad
        SaveLoadQuizFile saveLoadQuizFile = new SaveLoadQuizFile(mockFilePath);

        // Liste von Beispiel-Quizfragen erstellen
        List<QuizQuestion> mockQuestions = new ArrayList<>();

        // Beispiel-Quizfragen hinzufügen
        mockQuestions.add(new CapitalizationQuestion("Das Wetter ist heute schön."));
        mockQuestions.add(new CapitalizationQuestion("Java ist eine objektorientierte Programmiersprache."));
        mockQuestions.add(new CapitalizationQuestion("Die Hauptstadt von Deutschland ist Berlin."));

        mockQuestions.add(new CompleteQuestion("Auto", "A___", "Auto"));
        mockQuestions.add(new CompleteQuestion("Baum", "B__m", "Baum"));
        mockQuestions.add(new CompleteQuestion("Hund", "H__d", "Hund"));
        mockQuestions.add(new CompleteQuestion("Blume", "Blu__", "Blume"));

        mockQuestions.add(new PictureQuestion(url, "Hund"));
        mockQuestions.add(new PictureQuestion(url, "Berg"));
        mockQuestions.add(new PictureQuestion(url, "Strand"));
        mockQuestions.add(new PictureQuestion(url, "Sonne"));

        mockQuestions.add(new SSharpQuestion("spielen", "spielen"));
        mockQuestions.add(new SSharpQuestion("sehen", "sehen"));
        mockQuestions.add(new SSharpQuestion("fahren", "fahren"));
        mockQuestions.add(new SSharpQuestion("essen", "essen"));

        saveLoadQuizFile.saveQuestions(mockQuestions);

        System.out.println("Mock-Datei wurde erfolgreich erstellt: " + mockFilePath);
    }
}
