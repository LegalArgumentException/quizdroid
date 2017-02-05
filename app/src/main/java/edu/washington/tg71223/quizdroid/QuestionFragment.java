package edu.washington.tg71223.quizdroid;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import edu.washington.tg71223.quizdroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    private int answerPos;
    private int questionAmount;
    private int correct;
    private int currentQuestion;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        answerPos = getArguments().getInt("answerPos");
        questionAmount = getArguments().getInt("questionAmount");
        correct = getArguments().getInt("correct");
        currentQuestion = getArguments().getInt("currentQuestion");
        final RadioGroup submitRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        Button submitButton = (Button) view.findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = submitRadioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {
                    RadioButton selectedButton = (RadioButton) getView().findViewById(selectedId);
                    int chosenPos = Integer.decode(selectedButton.getTag().toString());
                    Bundle answerBundle = new Bundle();
                    answerBundle.putInt("chosenPos", chosenPos);
                    answerBundle.putInt("answerPos", answerPos);
                    answerBundle.putInt("questionAmount", questionAmount);
                    answerBundle.putInt("correct", correct);
                    answerBundle.putInt("currentQuestion", currentQuestion);

                    AnswerFragment af = new AnswerFragment();
                    af.setArguments(answerBundle);
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.frag_container, af);
                    tx.addToBackStack(null);
                    tx.commit();
                } else {
                    Toast.makeText(getActivity(), "Please choose an answer before submitting", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void onRadioClick(View v) {
        Button submitButton = (Button) v.findViewById(R.id.submit);
        submitButton.setVisibility(View.VISIBLE);
    }
}
