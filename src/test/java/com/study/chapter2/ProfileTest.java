package com.study.chapter2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfileTest {

    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;


    @BeforeEach
    public void setup() {
        profile = new Profile("Bull Hockey, Inc.");
        question = new BooleanQuestion("Got bounses?");
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

    @Test
    public void assertDoubleEquals() {
        assertThat(2.32 * 3, equalTo(6.96));
    }

    @DisplayName("부동 소수점이 잘 읽히지 않는 테스트")
    @Test
    public void assertWithTolerance() {
        assertTrue(Math.abs(2.32 * 3) - 6.96 < 0.005);
    }

    @DisplayName("가독성 좋은 부동 소수점 테스트")
    @Test
    public void assertDoublesCloseTo() {
        assertThat(2.32 * 3, closeTo(6.96, 0.0005));
    }

}