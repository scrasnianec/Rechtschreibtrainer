package Model;

import com.google.gson.*;
import java.lang.reflect.Type;

public class QuizQuestionDeserializer implements JsonDeserializer<QuizQuestion> {
    @Override
    public QuizQuestion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Example logic to determine the subclass based on unique fields
        if (jsonObject.has("imageURL")) {
            // It's a PictureQuestion
            return context.deserialize(jsonObject, PictureQuestion.class);
        } else if (jsonObject.has("uncompleteWord")) {
            // It's a CompleteQuestion
            return context.deserialize(jsonObject, CompleteQuestion.class);
        } else if (jsonObject.has("relatedWord")) {
            // It's an SSharpQuestion
            return context.deserialize(jsonObject, SSharpQuestion.class);
        } else if (jsonObject.has("correctAnswer")) {
            // It's a CapitalizationQuestion
            return context.deserialize(jsonObject, CapitalizationQuestion.class);
        } else {
            throw new JsonParseException("Unable to determine the type of QuizQuestion from JSON: " + jsonObject);
        }
    }
}
