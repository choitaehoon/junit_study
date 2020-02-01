package com.study.chapter12;

import java.util.HashMap;
import java.util.Map;

public class Profile {

    private final Map<String, Answer> answers = new HashMap<>();

    public boolean matches(Criterion criterion) {
        Answer answer = getMatchingProfileAnswer(criterion);
        return answer != null && answer.match(criterion.getAnswer());
    }

    private Answer getMatchingProfileAnswer(Criterion criterion) {
        return answers.get(criterion.getAnswer().getQuestionText());
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }
}
