package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QuizReset {
    public static void resetQuizFile(){
        String mockFilePath = "./data/questions_data.json";

        // Instanziiere SaveLoadQuizFile mit dem Pfad
        SaveLoadQuizFile saveLoadQuizFile = new SaveLoadQuizFile();

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

        mockQuestions.add(new PictureQuestion("https://as2.ftcdn.net/v2/jpg/02/71/09/23/1000_F_271092321_KWzz3JmciFYtzJRyTJE5ZMKSu4p1ATrK.jpg", "Hund"));
        mockQuestions.add(new PictureQuestion("https://thumbs.dreamstime.com/b/mountain-10204465.jpg", "Berg"));
        mockQuestions.add(new PictureQuestion("https://t3.ftcdn.net/jpg/02/43/25/90/360_F_243259090_crbVsAqKF3PC2jk2eKiUwZHBPH8Q6y9Y.jpg", "Strand"));
        mockQuestions.add(new PictureQuestion("https://www.shutterstock.com/image-vector/sun-icon-symbol-isolated-on-600nw-1915412905.jpg", "Sonne"));

        mockQuestions.add(new SSharpQuestion("biss", "beißen"));
        mockQuestions.add(new SSharpQuestion("fasste", "fassen"));
        mockQuestions.add(new SSharpQuestion("ließ", "lassen"));
        mockQuestions.add(new SSharpQuestion("gepresst", "pressen"));

        saveLoadQuizFile.saveQuestions(mockQuestions);

    }
}
