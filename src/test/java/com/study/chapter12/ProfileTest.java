package com.study.chapter12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    private Profile profile;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNotRelocation;
    private BooleanQuestion questionReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;
    private Answer answerReimbursesTuition;
    private Criteria criteria;

    @BeforeEach
    public void setup() {
        profile = new Profile();
        Question question = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation =
                new Answer(question, Bool.TRUE);
        answerThereIsNotRelocation =
                new Answer(question, Bool.FALSE);

        questionReimbursesTuition =
                new BooleanQuestion(1, "Reimburses tuition?");
        answerDoesNotReimburseTuition =
                new Answer(questionReimbursesTuition, Bool.FALSE);
        answerReimbursesTuition =
                new Answer(questionReimbursesTuition, Bool.TRUE);
    }

    @BeforeEach
    public void createCriteria() {
        criteria = new Criteria();
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

    @Test
    public void does_not_match_when_none_of_multiple_criteria_match() {
        profile.add(answerDoesNotReimburseTuition);
        Criteria criteria = new Criteria();
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        boolean result = profile.matches(criteria);

        assertFalse(result);
    }

    @Test
    public void matches_when_any_of_multiple_criteria_match() {
        profile.add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        boolean result = profile.matches(criteria);

        assertTrue(result);
    }

    @Test
    public void does_not_match_when_any_must_meet_criteria_not_met() {
        profile.add(answerThereIsRelocation);
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        boolean result = profile.matches(criteria);

        assertFalse(result);
    }

    @Test
    public void matches_when_criterion_is_dont_care() {
        profile.add(answerDoesNotReimburseTuition);
        Criterion criterion = new Criterion(answerReimbursesTuition, Weight.DontCare);

        assertTrue(profile.matches(criterion));
    }

}
