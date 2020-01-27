package com.study.chapter9;

import com.study.chapter2.Answer;
import com.study.chapter2.Criteria;
import com.study.chapter2.Question;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MatchSetTest {

    private Criteria criteria;
    private Question questionReimbursesTuition;

    private Map<String, Answer> answers;

    @Before
    public void createAnswers() {
        answers = new HashMap<>();
    }

    @Before
    public void createCriteria() {
        criteria = new Criteria();
    }

    @Before
    public void createQuestionsAndAnswers() {

    }

    private void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    private MatchSet createMatchSet() {
        return new MatchSet(answers, criteria);
    }
}