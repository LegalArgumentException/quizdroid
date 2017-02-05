package edu.washington.tg71223.quizdroid;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.washington.tg71223.quizdroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerFragment extends Fragment {

    QuizFragmentListener mCallback;
    View view;
    private int questionAmount;
    private int currentQuestion;
    private int correct;
    private boolean answerCorrect;

    interface QuizFragmentListener {
        public void askQuestion(boolean answerCorrect);
    }

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (QuizFragmentListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement QuizFragmentListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_answer, container, false);

        currentQuestion = getArguments().getInt("currentQuestion");
        questionAmount = getArguments().getInt("questionAmount");
        int answerPos = getArguments().getInt("answerPos");
        int chosenPos = getArguments().getInt("chosenPos");
        int correct = getArguments().getInt("correct");
        answerCorrect = (answerPos == chosenPos);
        if(answerCorrect) {
            correct++;
        }
        Log.i("AnswerFragment", String.valueOf(answerPos));

        Button button = (Button) view.findViewById(R.id.nextQuestionButton);
        if(currentQuestion == questionAmount) {
            button.setText("Finish");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.askQuestion(answerCorrect);
                }
            });
        }

        TextView questionInfo = (TextView) view.findViewById(R.id.questionInfo);
        questionInfo.setText("You are currently on question: " + currentQuestion + " of " + questionAmount + " and your answer was : Answer #" + chosenPos);
        TextView currentScore = (TextView) view.findViewById(R.id.currentScore);
        currentScore.setText("You currently have answered " + correct + " out of " + questionAmount + " questions correctly");
        for(int i = 1; i <= 4; i++) {
            TextView option = new TextView(getActivity());
            LinearLayout answerLayout = (LinearLayout) view.findViewById(R.id.answerContainer);
            option.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    5
            ));
            if(i == answerPos) {
                option.setText(i + ".) Correct choice Placeholder");
                option.setBackgroundColor(getResources().getColor(R.color.colorCorrect));
            } else {
                option.setText(i + ".) Incorrect Choice Placeholder");
                option.setBackgroundColor(getResources().getColor(R.color.colorIncorrect));
            }
            answerLayout.addView(option);
        }
        return view;
    }

}
