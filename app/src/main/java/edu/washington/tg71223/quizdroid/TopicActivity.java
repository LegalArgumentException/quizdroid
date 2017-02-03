package edu.washington.tg71223.quizdroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class TopicActivity extends Activity implements OverviewFragment.QuizFragmentListener {

    private final String TAG = "quizdroid";
    String[] descriptionArray;
    String[] questionAmountArray;
    String[] answers;
    int questionAmount;
    int currentQuestion = 1;
    String topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        // Set Model values (would optimally not all be located at top of file)
        int position = getIntent().getIntExtra("Position", 0);
        descriptionArray = getResources().getStringArray(R.array.descriptions);
        questionAmountArray = getResources().getStringArray(R.array.questionAmount);
        String answersString = getResources().getStringArray(R.array.answers)[position];
        answers = answersString.split(",");
        questionAmount = Integer.decode(questionAmountArray[position]);
        String description = descriptionArray[position];
        topic = getIntent().getStringExtra("TopicName");
        Log.i(TAG, "Position is: " + position + ", Description is: " + description + ", Topic is: " + topic
                + ", Question Amount is :" + questionAmount );

        //Create initial bundle with information for the overview fragment
        Bundle overviewBundle = new Bundle();
        overviewBundle.putString("description", description);
        overviewBundle.putInt("position", position);
        overviewBundle.putString("topic", topic);

        //Set current fragment to the overview fragment
        OverviewFragment of = new OverviewFragment();
        of.setArguments(overviewBundle);
        getFragmentManager().beginTransaction().add(R.id.frag_container, of).commit();
    }

    public void askQuestion() {
        QuestionFragment qf = new QuestionFragment();
        Bundle questionBundle = new Bundle();
        questionBundle.putString("topic", topic);
        questionBundle.putInt("currentQuestion", currentQuestion);
        questionBundle.putInt("questionAmount", questionAmount);
        questionBundle.putStringArray("answers", answers);
        qf.setArguments(questionBundle);
        FragmentTransaction fragMan = getFragmentManager().beginTransaction();
        fragMan.replace(R.id.frag_container, qf);
        fragMan.addToBackStack(null);
        fragMan.commit();
    }

//    public void askQuestion() {
//        QuestionFragment qf = new QuestionFragment();
//        FragmentTransaction fragMan = getFragmentManager().beginTransaction();
//        fragMan.replace(R.id.frag_container, qf);
//        fragMan.addToBackStack(null);
//        fragMan.commit();
//    }
}
