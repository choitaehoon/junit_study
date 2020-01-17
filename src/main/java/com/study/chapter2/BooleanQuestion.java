package com.study.chapter2;

public class BooleanQuestion extends Question {
    public BooleanQuestion(String text) {
        super(text, new String[] { "No", "Yes" });
    }

    @Override
    public boolean match(int expected, int actual) {
        return expected == actual;
    }
}