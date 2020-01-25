package com.study.chapter9;

import com.study.chapter2.Answer;
import com.study.chapter2.Criteria;
import com.study.chapter2.Criterion;

import java.util.Map;

public class MatchSet {

    private Map<String, Answer> answer;
    private int score = 0;

    public MatchSet(Map<String, Answer> answer, Criteria criteria) {
        this.answer = answer;
        calculateScore(criteria);
    }

    public boolean matches(Criteria criteria) {
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

    private Answer answerMatching(Criterion criterion) {
        return answer.get(criterion.getAnswer().getQuestionText());
    }

    public int getScore() {
        return score;
    }

}
