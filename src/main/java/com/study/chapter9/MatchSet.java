package com.study.chapter9;

import com.study.chapter2.Criteria;
import com.study.chapter2.Criterion;
import com.study.chapter2.Weight;

public class MatchSet {

    private AnswerCollection answer;
    private Criteria criteria;

    public MatchSet(AnswerCollection answers, Criteria criteria) {
        this.answer = answers;
        this.criteria = criteria;
    }

    public boolean matches() {
        if (doesNotMeetAnyMustMatchCriterion()) {
            return false;
        }

        return anyMatches();
    }

    private boolean doesNotMeetAnyMustMatchCriterion() {
        for (Criterion criterion : criteria) {
            boolean match = criterion.matches(answer.answerMatching(criterion));
            if (!match && criterion.getWeight() == Weight.MustMatch) {
                return true;
            }
        }

        return false;
    }

    private boolean anyMatches() {
        boolean anyMatches = false;
        for (Criterion criterion : criteria) {
            anyMatches |= criterion.matches(answer.answerMatching(criterion));
        }

        return anyMatches;
    }

    public int getScore() {
        int score = 0;
        for (Criterion criterion : criteria) {
            if (criterion.matches(answer.answerMatching(criterion))) {
                score += criterion.getWeight().getValue();
            }
        }
        return score;
    }

}
