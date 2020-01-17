package com.study.chapter5;

import com.study.chapter2.Question;

public class PercentileQuestion extends Question {

    public PercentileQuestion(String text, String[] answerChoices) {
        super(text, answerChoices);
    }

    @Override
    public boolean match(int expected, int actual) {
        return expected <= actual;
    }
}
