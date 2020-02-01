package com.study.chapter12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    private Profile profile;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNotRelocation;

    @BeforeEach
    public void setup() {
        profile = new Profile();
        Question question = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation =
                new Answer(question, Bool.TRUE);
        answerThereIsNotRelocation =
                new Answer(question, Bool.FALSE);
    }

    @Test
    public void matches_nothing_when_profile_empty() {
        Criterion criterion =
                new Criterion(answerThereIsRelocation, Weight.DontCare);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matches_when_profile_contains_matching_answer() {
        profile.add(answerThereIsRelocation);
        Criterion criterion =
                new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }

    @Test
    public void does_notMatch_when_noMatching_answer() {
        profile.add(answerThereIsNotRelocation);
        Criterion criterion =
                new Criterion(answerThereIsNotRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertFalse(result);
    }

    @Test
    public void matches_when_contains_multiple_answers() {
        profile.add(answerThereIsRelocation);
        profile.add(answerThereIsNotRelocation);
        Criterion criterion =
                new Criterion(answerThereIsRelocation, Weight.Important);

        boolean result = profile.matches(criterion);

        assertTrue(result);
    }
}
