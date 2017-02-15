package edu.washington.tg71223.quizdroid;

import java.util.List;

/**
 * Created by Tanner on 2/13/2017.
 */

public class Quiz {
    private String questionText;
    private List<String> questionList;
    private int answer;

    public Quiz(String questionText, List<String> questionList, int answer) {
        this.questionText = questionText;
        this.questionList = questionList;
        this.answer = answer;
    }

    public int getAnswer() {
        return this.answer;
    }

    public List<String> getQuestions() {
        return this.questionList;
    }

    public String getQuestionText() {
        return this.questionText;
    }
}
