package edu.washington.tg71223.quizdroid;

import android.app.Application;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanner on 2/13/2017.
 */

public class QuizApp extends Application {

    private static TopicRepository instance = new TopicRepository(); // Make sure that's correctly for singleton

    private String data = "[\n" +
            "   {\n" +
            "      \"title\": \"Science!\",\n" +
            "      \"desc\": \"Because SCIENCE!\",\n" +
            "      \"questions\": [\n" +
            "         {\n" +
            "            \"text\": \"What is fire?\",\n" +
            "            \"answer\": \"1\",\n" +
            "            \"answers\": [\n" +
            "               \"One of the four classical elements\",\n" +
            "               \"A magical reaction given to us by God\",\n" +
            "               \"A band that hasn't yet been discovered\",\n" +
            "               \"Fire! Fire! Fire! heh-heh\"\n" +
            "            ]\n" +
            "         }\n" +
            "      ]\n" +
            "   },\n" +
            "   {\n" +
            "      \"title\": \"Marvel Super Heroes\",\n" +
            "      \"desc\": \"Avengers, Assemble!\",\n" +
            "      \"questions\": [\n" +
            "         {\n" +
            "            \"text\": \"Who is Iron Man?\",\n" +
            "            \"answer\": \"1\",\n" +
            "            \"answers\": [\n" +
            "               \"Tony Stark\",\n" +
            "               \"Obadiah Stane\",\n" +
            "               \"A rock hit by Megadeth\",\n" +
            "               \"Nobody knows\"\n" +
            "            ]\n" +
            "         },\n" +
            "         {\n" +
            "            \"text\": \"Who founded the X-Men?\",\n" +
            "            \"answer\": \"2\",\n" +
            "            \"answers\": [\n" +
            "               \"Tony Stark\",\n" +
            "               \"Professor X\",\n" +
            "               \"The X-Institute\",\n" +
            "               \"Erik Lensherr\"\n" +
            "            ]\n" +
            "         },\n" +
            "         {\n" +
            "            \"text\": \"How did Spider-Man get his powers?\",\n" +
            "            \"answer\": \"1\",\n" +
            "            \"answers\": [\n" +
            "               \"He was bitten by a radioactive spider\",\n" +
            "               \"He ate a radioactive spider\",\n" +
            "               \"He is a radioactive spider\",\n" +
            "               \"He looked at a radioactive spider\"\n" +
            "            ]\n" +
            "         }\n" +
            "      ]\n" +
            "   },\n" +
            "   {\n" +
            "      \"title\": \"Mathematics\",\n" +
            "      \"desc\": \"Did you pass the third grade?\",\n" +
            "      \"questions\": [\n" +
            "         {\n" +
            "            \"text\": \"What is 2+2?\",\n" +
            "            \"answer\": \"1\",\n" +
            "            \"answers\": [\n" +
            "               \"4\",\n" +
            "               \"22\",\n" +
            "               \"An irrational number\",\n" +
            "               \"Nobody knows\"\n" +
            "            ]\n" +
            "         }\n" +
            "      ]\n" +
            "   }\n" +
            "]";

    public static TopicRepository getRepository() {
        return instance;
    }

    public QuizApp() throws JSONException {
        Log.i("QuizApp", "QuizApp is being loaded and ran");

        try {
            final JSONArray quizData = new JSONArray(data);
            final int n = quizData.length();
            for (int i = 0; i < n; i++) {
                JSONObject obj = quizData.getJSONObject(i);
                parseJSON(obj);
            }
            Log.i("QuizApp", quizData.toString());

        } catch (Exception e) {
            Log.e("QuizApp", e.toString());
        }
    }

    private void parseJSON(JSONObject obj) throws JSONException {
        Topic topic;
        String title = obj.getString("title");
        String desc = obj.getString("desc");
        List<Quiz> quizzes = new ArrayList<Quiz>();
        for(int i = 0; i < obj.getJSONArray("questions").length(); i++) {
            JSONObject quizData = obj.getJSONArray("questions").getJSONObject(i);
            JSONArray answerArray = quizData.getJSONArray("answers");
            String quizString = quizData.getString("text");
            int quizAnswer = Integer.decode(quizData.getString("answer"));
            List<String> quizArray = new ArrayList<String>();
            for(int j = 0; j < answerArray.length(); j++) {
                quizArray.add(answerArray.getString(j));
            }
            Quiz questions = new Quiz(quizString, quizArray, quizAnswer);
            quizzes.add(questions);
            Log.i("QuizApp", "Text: " + quizString + ", Answer: " + quizAnswer + ", Amount of Answers: " + quizArray.size());
        }
        topic = new Topic(title, "", desc, quizzes);
        instance.addTopic(topic);

    }


}
