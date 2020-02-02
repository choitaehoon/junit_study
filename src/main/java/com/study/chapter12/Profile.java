package com.study.chapter12;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    private final Map<String, Answer> answers = new HashMap<>();

    public boolean matches(Criterion criterion) {
        return criterion.getWeight() == Weight.DontCare ||
                criterion.getAnswer().match(getMatchingProfileAnswer(criterion));
    }

    public boolean matches(Criteria criteria) {
        for (Criterion criterion : criteria) {
            if (matches(criterion)) {
                return true;
            }
            else if (criterion.getWeight() == Weight.MustMatch) {
                return false;
            }
        }

        return false;
    }

    private Answer getMatchingProfileAnswer(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

}
