package Model;

import java.util.ArrayList;
import java.util.List;

public class QuizReset {
    public static void resetQuizFile(){
        String mockFilePath = DataPath.QUIZ_PATH;

        SaveLoadQuizFile saveLoadQuizFile = new SaveLoadQuizFile();

        List<QuizQuestion> mockQuestions = new ArrayList<>();

        mockQuestions.add(new CapitalizationQuestion("Das Wetter ist heute schön."));
        mockQuestions.add(new CapitalizationQuestion("Java ist eine objektorientierte Programmiersprache."));
        mockQuestions.add(new CapitalizationQuestion("Die Hauptstadt von Deutschland ist Berlin."));
        mockQuestions.add(new CapitalizationQuestion("Am Montag beginnt die Schule wieder."));
        mockQuestions.add(new CapitalizationQuestion("Das iPhone wurde von Apple entwickelt."));
        mockQuestions.add(new CapitalizationQuestion("Im Herbst fallen die Blätter von den Bäumen."));
        mockQuestions.add(new CapitalizationQuestion("Der Eiffelturm steht in Paris, der Hauptstadt Frankreichs."));
        mockQuestions.add(new CapitalizationQuestion("Mathematik und Physik sind wichtige Fächer."));
        mockQuestions.add(new CapitalizationQuestion("Die SPÖ und die ÖVP sind Parteien in Österreich."));
        mockQuestions.add(new CapitalizationQuestion("Im Juli fahren viele Familien in den Sommerurlaub."));
        mockQuestions.add(new CapitalizationQuestion("Mein Onkel arbeitet als Arzt in einem Krankenhaus."));
        mockQuestions.add(new CapitalizationQuestion("Die Erde dreht sich um die Sonne."));
        mockQuestions.add(new CapitalizationQuestion("Herr Müller ist unser neuer Deutschlehrer."));
        mockQuestions.add(new CapitalizationQuestion("Viele Kinder lesen gerne Märchen."));
        mockQuestions.add(new CapitalizationQuestion("Im Jahr 1989 fiel die Berliner Mauer."));
        mockQuestions.add(new CapitalizationQuestion("Das Kunsthistorisches Museum Wien ist sehr bekannt."));
        mockQuestions.add(new CapitalizationQuestion("Die UNO setzt sich für den Weltfrieden ein."));
        mockQuestions.add(new CapitalizationQuestion("Das Brandenburger Tor ist eine Sehenswürdigkeit in Berlin."));
        mockQuestions.add(new CapitalizationQuestion("Mein Lieblingsbuch ist Harry Potter und der Stein der Weisen."));
        mockQuestions.add(new CapitalizationQuestion("ÖBB steht für Österreichische Bundesbahnen."));
        mockQuestions.add(new CapitalizationQuestion("Am Wochenende besuche ich oft meine Großeltern."));
        mockQuestions.add(new CapitalizationQuestion("Der Rhein ist einer der längsten Flüsse Europas."));
        mockQuestions.add(new CapitalizationQuestion("Jeden Sonntag backt meine Mutter einen Apfelkuchen."));
        mockQuestions.add(new CapitalizationQuestion("Goethe und Schiller sind berühmte deutsche Dichter."));
        mockQuestions.add(new CapitalizationQuestion("Der Duden ist ein bekanntes Wörterbuch."));

        mockQuestions.add(new CompleteQuestion("der Wettlauf – die ________", "Wettläufe"));
        mockQuestions.add(new CompleteQuestion("das Band – zwei _______", "Bänder"));
        mockQuestions.add(new CompleteQuestion("die Maus – mehrere _______", "Mäuse"));
        mockQuestions.add(new CompleteQuestion("die Säure – die _______", "Säuren"));
        mockQuestions.add(new CompleteQuestion("das Kreuz – mehrere _______", "Kreuze"));
        mockQuestions.add(new CompleteQuestion("der Baum – hohe _______", "Bäume"));
        mockQuestions.add(new CompleteQuestion("der Gast – die _______", "Gäste"));
        mockQuestions.add(new CompleteQuestion("der Strauch – mehrere _______", "Sträucher"));
        mockQuestions.add(new CompleteQuestion("der Arzt – mehrere _______", "Ärzte"));
        mockQuestions.add(new CompleteQuestion("der Zaun – hohe _______", "Zäune"));
        mockQuestions.add(new CompleteQuestion("der Acker – viele _______", "Äcker"));
        mockQuestions.add(new CompleteQuestion("der Ast – dicke _______", "Äste"));
        mockQuestions.add(new CompleteQuestion("das Licht – die _______", "Lichter"));
        mockQuestions.add(new CompleteQuestion("die Hand – zwei _______", "Hände"));
        mockQuestions.add(new CompleteQuestion("der Schuh – mehrere _______", "Schuhe"));
        mockQuestions.add(new CompleteQuestion("das Buch – viele _______", "Bücher"));
        mockQuestions.add(new CompleteQuestion("der Vogel – kleine _______", "Vögel"));
        mockQuestions.add(new CompleteQuestion("der Stuhl – mehrere _______", "Stühle"));
        mockQuestions.add(new CompleteQuestion("der Spiegel – große _______", "Spiegel"));
        mockQuestions.add(new CompleteQuestion("das Bild – schöne _______", "Bilder"));
        mockQuestions.add(new CompleteQuestion("die Stadt – große _______", "Städte"));
        mockQuestions.add(new CompleteQuestion("das Blatt – viele _______", "Blätter"));
        mockQuestions.add(new CompleteQuestion("der Tisch – mehrere _______", "Tische"));
        mockQuestions.add(new CompleteQuestion("die Blume – bunte _______", "Blumen"));
        mockQuestions.add(new CompleteQuestion("der Hut – viele _______", "Hüte"));
        mockQuestions.add(new CompleteQuestion("die Kuh – einige _______", "Kühe"));

        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1453235421161-e41b42ebba05?q=80&w=3456&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Schneemann"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1593351415075-3bac9f45c877?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8Ym9hdHxlbnwwfHwwfHx8MA%3D%3D", "Boot"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1503792501406-2c40da09e1e2?q=80&w=3008&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Schere"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1638900999395-22595e1785f6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8YnJvb218ZW58MHx8MHx8fDA%3D", "Besen"));
        mockQuestions.add(new PictureQuestion("https://m.media-amazon.com/images/I/71VTqVnY5YS._AC_.jpg", "Waage"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1595434091143-b375ced5fe5c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8Y29mZWV8ZW58MHx8MHx8fDA%3D", "Kaffee"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1548032885-b5e38734688a?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8b2NlYW58ZW58MHx8MHx8fDA%3D", "Meer"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1601004890684-d8cbf643f5f2?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8c3RyYXdiZXJyeXxlbnwwfHwwfHx8MA%3D%3D", "Erdbeere"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1610137312377-f2334bc3b279?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8aG90JTIwdGVhfGVufDB8fDB8fHww", "Tee"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1516132006923-6cf348e5dee2?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8bGFrZXxlbnwwfHwwfHx8MA%3D%3D", "See"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1519852476561-ec618b0183ba?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cml2ZXJ8ZW58MHx8MHx8fDA%3D", "Fluss"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1577373644244-ff9935a13a2b?q=80&w=2203&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Schüssel"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1613952936263-61dc8cddf6c7?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGJhcnJlbHxlbnwwfHwwfHx8MA%3D%3D", "Fass"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1614362705324-8da11fd16754?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8a25pZmV8ZW58MHx8MHx8fDA%3D", "Messer"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1645111092351-20261ec1cd84?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8R2llJUMzJTlGa2FubmV8ZW58MHx8MHx8fDA%3D", "Gießkanne"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1575361204480-aadea25e6e68?q=80&w=3871&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Fußball"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1576712967455-c8d22580e9be?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3dlZXRzfGVufDB8fDB8fHww", "Süßigkeiten"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/flagged/photo-1579410137922-543ed48d263e?q=80&w=3880&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Walnuss"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1521078015641-9a722072c6ef?q=80&w=3870&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", "Tasse"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1615842978998-54a9182892b2?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fGxvY2t8ZW58MHx8MHx8fDA%3D", "Schloss"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1618354691490-0de58a2c4c93?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fGJvdHRsZSUyMG1vY2t1cHxlbnwwfHwwfHx8MA%3D%3D", "Flasche"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1538469990728-da0dd350066d?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8U3RyYSVDMyU5RmV8ZW58MHx8MHx8fDA%3D", "Straße"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1560782205-4dd83ceb0270?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHVkZGxlfGVufDB8fDB8fHww", "Pfütze"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1519582149095-fe7d19b2a3d2?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8d2F0ZXJmYWxsfGVufDB8fDB8fHww", "Wasserfall"));
        mockQuestions.add(new PictureQuestion("https://images.unsplash.com/photo-1597517697687-acc0c17b2603?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8bW9zc3xlbnwwfHwwfHx8MA%3D%3D", "Moos"));

        mockQuestions.add(new SSharpQuestion("biss", "beißen"));
        mockQuestions.add(new SSharpQuestion("fasste", "fassen"));
        mockQuestions.add(new SSharpQuestion("ließ", "lassen"));
        mockQuestions.add(new SSharpQuestion("gepresst", "pressen"));
        mockQuestions.add(new SSharpQuestion("nieste", "niesen"));
        mockQuestions.add(new SSharpQuestion("gerast", "rasen"));
        mockQuestions.add(new SSharpQuestion("stieß", "stoßen"));
        mockQuestions.add(new SSharpQuestion("vergaß", "vergessen"));
        mockQuestions.add(new SSharpQuestion("blies", "blasen"));
        mockQuestions.add(new SSharpQuestion("wusste", "wissen"));
        mockQuestions.add(new SSharpQuestion("geflossen", "fließen"));
        mockQuestions.add(new SSharpQuestion("bremste", "bremsen"));
        mockQuestions.add(new SSharpQuestion("geküsst", "küssen"));
        mockQuestions.add(new SSharpQuestion("musste", "müssen"));
        mockQuestions.add(new SSharpQuestion("fraß", "fressen"));
        mockQuestions.add(new SSharpQuestion("gelesen", "lesen"));
        mockQuestions.add(new SSharpQuestion("nieselte", "nieseln"));
        mockQuestions.add(new SSharpQuestion("brauste", "brausen"));
        mockQuestions.add(new SSharpQuestion("riss", "reißen"));
        mockQuestions.add(new SSharpQuestion("maß", "messen"));
        mockQuestions.add(new SSharpQuestion("schoss", "schießen"));
        mockQuestions.add(new SSharpQuestion("floss", "fließen"));
        mockQuestions.add(new SSharpQuestion("goss", "gießen"));
        mockQuestions.add(new SSharpQuestion("schmiss", "schmeißen"));
        mockQuestions.add(new SSharpQuestion("vermaß", "vermessen"));
        mockQuestions.add(new SSharpQuestion("schloss", "schließen"));

        saveLoadQuizFile.saveQuestions(mockQuestions);
    }
}
