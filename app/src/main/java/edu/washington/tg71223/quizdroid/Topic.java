package edu.washington.tg71223.quizdroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanner on 2/13/2017.
 */

public class Topic {

    private String name;
    private String longDescription;
    private String shortDescription;
    private List<Quiz> quizList = new ArrayList<Quiz>();

    public Topic(String name, String longDes, String shortDes, List<Quiz> quizes) {
        this.name = name;
        this.longDescription = "This is the long description for the " + name + "quiz. The long descriptions weren't provided but here you go!" ;
        this.shortDescription = shortDes;
        for (Quiz quiz : quizes) {
            quizList.add(quiz);
        }
    }

    public void addQuiz(Quiz quiz) {
        this.quizList.add(quiz);
    }

    public void addQuizList(List<Quiz> quizList) {
        for (Quiz quiz: quizList) {
            this.quizList.add(quiz);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public Quiz getQuiz(int index) {
        if (quizList.size() < index) {
            return null;
        }
        return quizList.get(index - 1);
    }

    public int getQuizAmt() {
        return this.quizList.size();
    }

    public List<Quiz> getQuizList() {
        return this.quizList;
    }
}
