package com.study.chapter9;

import com.study.chapter2.Answer;
import com.study.chapter2.Criteria;
import com.study.chapter2.Criterion;
import com.study.chapter2.Weight;

import java.util.Map;

public class MatchSet {

    private Map<String, Answer> answer;
    private int score = 0;
    private Criteria criteria;

    public MatchSet(Map<String, Answer> answer, Criteria criteria) {
        this.answer = answer;
        this.criteria = criteria;
        calculateScore();
    }

    public boolean matches() {
        if (doesNotMeetAnyMustMatchCriterion()) {
            return false;
        }

        return anyMatches();
    }

    private void calculateScore() {
        score = 0;
        for (Criterion criterion : criteria) {
            if (criterion.matches(answerMatching(criterion))) {
                score += criterion.getWeight().getValue();
            }
        }
    }

    private boolean doesNotMeetAnyMustMatchCriterion() {
        for (Criterion criterion : criteria) {
            boolean match = criterion.matches(answerMatching(criterion));
            if (!match && criterion.getWeight() == Weight.MustMatch) {
                return true;
            }
        }

        return false;
    }

    private boolean anyMatches() {
        boolean anyMatches = false;
        for (Criterion criterion : criteria) {
            anyMatches |= criterion.matches(answerMatching(criterion));
        }

        return anyMatches;
    }

    private Answer answerMatching(Criterion criterion) {
        return answer.get(criterion.getAnswer().getQuestionText());
    }

    public int getScore() {
        return score;
    }

}
