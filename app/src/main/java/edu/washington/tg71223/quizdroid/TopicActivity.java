package edu.washington.tg71223.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class TopicActivity extends Activity implements OverviewFragment.QuizFragmentListener, AnswerFragment.QuizFragmentListener {

    private final String TAG = "quizdroid";
//    String[] descriptionArray;
//    String[] questionAmountArray;
//    String[] answers;
    QuizApp quizApp;
    Topic currentTopic;
    private int questionAmount;
    private int currentQuestion = 1;
    private int correct = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        // Set Model values (would optimally not all be located at top of file)
        int position = getIntent().getIntExtra("Position", 0);
        quizApp = (QuizApp) getApplicationContext();
        List<Topic> topics = quizApp.getRepository().getTopics();
        currentTopic = topics.get(position);
//        descriptionArray = getResources().getStringArray(R.array.descriptions);
//        questionAmountArray = getResources().getStringArray(R.array.questionAmount);
//        String answersString = getResources().getStringArray(R.array.answers)[position];
//        answers = answersString.split(",");
//        questionAmount = Integer.decode(questionAmountArray[position]);
//        String description = descriptionArray[position];

        Log.i(TAG, "Position is: " + position + ", Description is: " + currentTopic.getShortDescription() + ", Topic is: " + currentTopic.getName()
                + ", Question Amount is :" + currentTopic.getQuizAmt() );

        //Create initial bundle with information for the overview fragment
        Bundle overviewBundle = new Bundle();
        overviewBundle.putString("topic", currentTopic.getName());
        overviewBundle.putString("description", currentTopic.getShortDescription());

        //Set current fragment to the overview fragment
        OverviewFragment of = new OverviewFragment();
        of.setArguments(overviewBundle);
        getFragmentManager().beginTransaction().add(R.id.frag_container, of).commit();
    }

    public void askQuestion(boolean answerCorrect) {
        if(answerCorrect) {
            correct++;
        }
        Quiz currentQuiz = currentTopic.getQuizList().get(currentQuestion - 1);
        QuestionFragment qf = new QuestionFragment();
        Bundle questionBundle = new Bundle();
        questionBundle.putString("topic", currentTopic.getName());
        questionBundle.putInt("currentQuestion", currentQuestion);
        questionBundle.putInt("questionAmount", currentTopic.getQuizAmt());
        questionBundle.putInt("answerPos", currentQuiz.getAnswer());
        questionBundle.putInt("correct", correct);
        qf.setArguments(questionBundle);
        FragmentTransaction fragMan = getFragmentManager().beginTransaction();
        fragMan.replace(R.id.frag_container, qf);
        fragMan.addToBackStack(null);
        fragMan.commit();
        currentQuestion++;
    }
}
