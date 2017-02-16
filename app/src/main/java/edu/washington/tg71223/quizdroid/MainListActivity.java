package edu.washington.tg71223.quizdroid;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.w3c.dom.Text;

public class MainListActivity extends AppCompatActivity {

    QuizApp quizApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        quizApp = (QuizApp) getApplicationContext();

        // Array of list items for adapter
        String[] listValues = quizApp.getRepository().getTopicNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listValues);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                TextView textView = (TextView) view;
                String itemValue = (String) textView.getText();
                //Log.i("@string/app_tag",itemValue + " button has been clicked");

                Intent i = new Intent(MainListActivity.this, TopicActivity.class);
                i.putExtra("Position", position);
                startActivity(i);
            }
        });
    }
}
