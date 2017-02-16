package edu.washington.tg71223.quizdroid;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.getDataDirectory;

/**
 * Created by Tanner on 2/13/2017.
 */

public class QuizApp extends Application {

    private static TopicRepository instance = new TopicRepository(); // Make sure that's correctly for singleton
    static QuizApp obj;

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

    String data1 = "[\n" +
            "    { \"title\":\"Music\",\n" +
            "      \"desc\":\"For the mucically inclined\",\n" +
            "      \"questions\":[\n" +
            "        {\n" +
            "          \"text\":\"What is a paradiddle?\",\n" +
            "          \"answer\":\"2\",\n" +
            "          \"answers\":[\n" +
            "            \"When a trumpet player comes behind someone and blows the horn SUPER loud\",\n" +
            "            \"The best goddamn drum rudiment in the business\",\n" +
            "            \"A band that hasn't yet been discovered\",\n" +
            "            \"Two paragliders\"\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"text\":\"Who started the fire?\",\n" +
            "          \"answer\":\"3\",\n" +
            "          \"answers\":[\n" +
            "            \"An arsonist\",\n" +
            "            \"Not I said the mouse\",\n" +
            "            \"We didn't - it's been always burning since the world's been turning\",\n" +
            "            \"Wait, is this Elton John?\"\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    { \"title\":\"Pizza\", \"desc\": \"Are you Top of the Toppings?\",\n" +
            "      \"questions\":[\n" +
            "        {\n" +
            "          \"text\":\"Which is the acceptable size to eat alone in a brief lapse of judgement\",\n" +
            "          \"answer\":\"4\",\n" +
            "          \"answers\":[\n" +
            "            \"Small\",\n" +
            "            \"Medium\",\n" +
            "            \"Large\",\n" +
            "            \"Raw Dough\"\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"text\":\"What is pizza called in Seattle?\",\n" +
            "          \"answer\":\"2\",\n" +
            "          \"answers\":[\n" +
            "            \"Hipster Disc\",\n" +
            "            \"Do you have gluten-free oregano?\",\n" +
            "            \"Pizza\",\n" +
            "            \"Freshman event attendance insurance\"\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"text\":\"What's my favorite pizza?\",\n" +
            "          \"answer\":\"2\",\n" +
            "          \"answers\":[\n" +
            "            \"Peperoni and bacon\",\n" +
            "            \"Chicken and white sauce\",\n" +
            "            \"Memes and dreams\",\n" +
            "            \"Sandwich\"\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    { \"title\":\"Free Answer\", \"desc\":\"The answer is 4\",\n" +
            "      \"questions\":[\n" +
            "         {\n" +
            "           \"text\":\"What is the answer?\",\n" +
            "           \"answer\":\"1\",\n" +
            "           \"answers\":[\n" +
            "             \"4\",\n" +
            "             \"Four\",\n" +
            "             \"Fore\",\n" +
            "             \"sqrt(sixteen)\"\n" +
            "           ]\n" +
            "         }\n" +
            "      ]\n" +
            "   }\n" +
            "]";

    public static TopicRepository getRepository() {
        return instance;
    }

    public QuizApp() {
        Log.i("QuizApp", "QuizApp is being loaded and ran");
    }

    public void onCreate() {
        super.onCreate();

        try {
            Log.i("QuizApp", "Data: " + getDataFromFile());
        } catch (IOException e) {
            Log.i("QuizApp", "IOException is: " + e.toString());
        }
        try {
            data = getDataFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            //Log.i("QuizApp", "Text: " + quizString + ", Answer: " + quizAnswer + ", Amount of Answers: " + quizArray.size());
        }
        topic = new Topic(title, "", desc, quizzes);
        instance.addTopic(topic);

    }

    private String getDataFromFile() throws IOException {
        FileInputStream fis = openFileInput("questions.json");
        Log.i("QuizApp", fis.toString());
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }


}
