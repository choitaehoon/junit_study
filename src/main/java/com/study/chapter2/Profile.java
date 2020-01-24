package com.study.chapter2;

import java.util.*;

public class Profile {
    private Map<String, Answer> answers = new HashMap<>();
    private int score;
    private String name;

    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public boolean matches(Criteria criteria) {
        calculateScore(criteria);

        if (doesNotMeetAnyMustMatchCriterion(criteria)) {
            return false;
        }

        return anyMatches(criteria);
    }

    private void calculateScore(Criteria criteria) {
        score = 0;
        for (Criterion criterion : criteria) {
            if (criterion.matches(answerMatching(criterion))) {
                score += criterion.getWeight().getValue();
            }
        }
    }

    private boolean doesNotMeetAnyMustMatchCriterion(Criteria criteria) {
        for (Criterion criterion : criteria) {
            boolean match = criterion.matches(answerMatching(criterion));
            if (!match && criterion.getWeight() == Weight.MustMatch) {
                return true;
            }
        }

        return false;
    }

    public int score() {
        return score;
    }

    private Answer answerMatching(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    private boolean anyMatches(Criteria criteria) {
        boolean anyMatches = false;
        for (Criterion criterion : criteria) {
            anyMatches |= criterion.matches(answerMatching(criterion));
        }

        return anyMatches;
    }

}
