package com.study.chapter2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfileTest {

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @BeforeEach
    public void setup() {
        profile = new Profile("Bull Hockey, Inc.");
        question = new BooleanQuestion(1, "Got bounses?");
        criteria = new Criteria();
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        profile.add(new Answer(question, Bool.FALSE));

        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        boolean matches = profile.matches(criteria);

        assertFalse(matches);
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        profile.add(new Answer(question, Bool.FALSE));

        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        boolean matches = profile.matches(criteria);

        assertTrue(matches);
    }
}