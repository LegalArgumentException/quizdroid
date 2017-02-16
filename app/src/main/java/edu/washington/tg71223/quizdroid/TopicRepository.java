package edu.washington.tg71223.quizdroid;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanner on 2/13/2017.
 */

public class TopicRepository {

    private List<Topic> topics = new ArrayList<Topic>();

    public TopicRepository() {
        Log.i("QuizApp", "Repository has been instantiated");
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void addTopicList(List<Topic> topicList) {
        for (Topic topic : topicList) {
            topics.add(topic);
        }
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public String[] getTopicNames() {
        String[] topicArray = new String[topics.size()];
        int index = 0;
        for(Topic topic : topics) {
            topicArray[index] = topic.getName();
            index++;
            Log.i("QuizApp", "Topic Amount: " + topics.size() + ", Index: " + index + ", Topic Name: " + topic.getName());
        }
        return topicArray;
    }

    public boolean topicExists(String topicName) {
        for(Topic topic : topics) {
            if(topic.getName().toLowerCase().equals(topicName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Topic getTopic(String topicName) {
        for(Topic topic : topics) {
            if(topic.getName().toLowerCase().equals(topicName.toLowerCase())) {
                return topic;
            }
        }
        return null;
    }

    public void clearTopics() {
        topics = new ArrayList<Topic>();
    }

}
