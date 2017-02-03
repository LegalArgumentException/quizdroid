package edu.washington.tg71223.quizdroid;


import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.washington.tg71223.quizdroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {


    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        final RadioGroup submitRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        Button submitButton = (Button) view.findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = submitRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedButton = (RadioButton) v.findViewById(selectedId);

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
