package com.study.chapter2;

import com.study.chapter9.MatchSet;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public boolean matches(Criteria criteria) {
        MatchSet matchSet = new MatchSet(answers, criteria);
        score = matchSet.getScore();
        return matchSet.matches();
    }

    public void add(Answer answer) {
        answers.put(answer.getQuestionText(), answer);
    }

    public MatchSet getMatchSet(Criteria criteria) {
        return new MatchSet(answers, criteria);
    }

    public List<Answer> find(Predicate<Answer> pred) {
        return answers.values().stream()
                .filter(pred)
                .collect(Collectors.toList());
    }
}
