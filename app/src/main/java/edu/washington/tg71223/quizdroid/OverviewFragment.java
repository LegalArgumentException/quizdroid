package edu.washington.tg71223.quizdroid;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {


    QuizFragmentListener mCallback;

    public interface QuizFragmentListener {
        void askQuestion(boolean answerCorrect);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (QuizFragmentListener) getActivity();
            Log.i("@strings/app_tag", "callback created: " + mCallback);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement QuizFragmentListener");
        }
    }

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        Button startButton = (Button) view.findViewById(R.id.start_button);
        startButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("@string/app_tag", "Button is working");
                        mCallback.askQuestion(false);
                    }
                }
        );
        Log.i("OverviewFragment", getArguments().getString("topic"));

        TextView quizTitle = (TextView) view.findViewById(R.id.overview_text);
        quizTitle.setText("You are about to take a quiz on" + getArguments().getString("topic"));
        TextView description = (TextView) view.findViewById(R.id.description_text);
        description.setText(getArguments().getString("description"));

        return view;
    }
}
