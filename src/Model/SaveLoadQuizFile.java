package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.xml.crypto.Data;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.io.File;


public class SaveLoadQuizFile {
	private final Gson gson;

	public SaveLoadQuizFile() {
		this.gson = new GsonBuilder()
				.registerTypeAdapter(QuizQuestion.class, new QuizQuestionDeserializer())
				.setPrettyPrinting()
				.create();
	}

	public List<QuizQuestion> loadQuestions() {
		File file = new File(DataPath.QUIZ_PATH);
		if(!file.exists()) {
			QuizReset.resetQuizFile();
			loadQuestions();
		}

		try (FileReader reader = new FileReader(DataPath.QUIZ_PATH)) {
			Type listType = new TypeToken<List<QuizQuestion>>() {}.getType();
			return gson.fromJson(reader, listType);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveQuestions(List<QuizQuestion> questionList) {
		File file = new File(DataPath.QUIZ_PATH);
		if(!file.exists()) {
			if(file.getParentFile().mkdirs()){
				System.out.println("Directory is created!");
			}
		}

		try (FileWriter writer = new FileWriter(DataPath.QUIZ_PATH)) {
			gson.toJson(questionList, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
