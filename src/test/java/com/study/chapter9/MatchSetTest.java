package com.study.chapter9;

import com.study.chapter2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MatchSetTest {

    private Criteria criteria;
    private Question questionReimbursesTuition;

    private Map<String, Answer> answers;

    private Question questionIsThereRelocation;
    private Answer answerReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;

    @BeforeEach
    public void createAnswers() {
        answers = new HashMap<>();
    }

    @BeforeEach
    public void createCriteria() {
        criteria = new Criteria();
    }

    @BeforeEach
    public void createQuestionsAndAnswers() {
        questionReimbursesTuition =
                new BooleanQuestion("Reimburses tuition?");
        answerReimbursesTuition =
                new Answer(questionReimbursesTuition, Bool.TRUE);
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);
    }

    private void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    private MatchSet createMatchSet() {
        return new MatchSet(answers, criteria);
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerReimbursesTuition, Weight.MustMatch));

        assertFalse(createMatchSet().matches());
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        add(answerReimbursesTuition);
        criteria.add(new Criterion(answerReimbursesTuition, Weight.DontCare));

        assertTrue(createMatchSet().matches());
    }
}