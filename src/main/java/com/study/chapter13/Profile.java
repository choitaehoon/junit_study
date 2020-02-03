package com.study.chapter13;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Profile {
   private Map<String,Answer> answers = new HashMap<>();
   private String id;

   public Profile(String id) {
      this.id = id;
   }
   
   public String getId() {
      return id;
   }

   public void add(Answer answer) {
      answers.put(answer.getQuestionText(), answer);
   }
   
   public MatchSet getMatchSet(Criteria criteria) {
      return new MatchSet(id, answers, criteria);
   }

   @Override
   public String toString() {
     return id;
   }

   public List<Answer> find(Predicate<Answer> pred) {
      return answers.values().stream()
            .filter(pred)
            .collect(Collectors.toList());
   }
}
