package com.study.chapter12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    private Profile profile;
    private Answer answer;

    @BeforeEach
    public void setup() {
        profile = new Profile();
        Question question = new BooleanQuestion(1, "Relocation package?");
        answer = new Answer(question, Bool.TRUE);
    }

    @Test
    public void matches_nothing_when_profile_empty() {
        Criterion criterion = new Criterion(answer, Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matches_when_profile_contains_matching_answer() {
        profile.add(answer);
        Criterion criterion = new Criterion(answer, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

}
