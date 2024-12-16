package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SaveLoadQuizFile {
	private String path;
	private final Gson gson;

	public SaveLoadQuizFile(String path) {
		this.path = path;
		this.gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public List<QuizQuestion> loadQuestions() {
		try (FileReader reader = new FileReader(path)) {
			Type listType = new TypeToken<List<QuizQuestion>>() {}.getType();
			return gson.fromJson(reader, listType);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveQuestions(List<QuizQuestion> questionList) {
		try (FileWriter writer = new FileWriter(path)) {
			gson.toJson(questionList, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
