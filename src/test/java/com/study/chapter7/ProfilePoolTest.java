package com.study.chapter7;

import com.study.chapter2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import java.util.*;
import static org.hamcrest.CoreMatchers.*;

class ProfilePoolTest {

    private ProfilePool pool;
    private Profile smeltInt;
    private Profile langrsoft;
    private BooleanQuestion doTheyReimburseTuition;

    @BeforeEach
    public void setup() {
        pool = new ProfilePool();
        smeltInt = new Profile("Smelt Inc.");
        langrsoft = new Profile("Langrsoft");
        doTheyReimburseTuition = new BooleanQuestion("Reimburses tuition?");
    }

    private Criteria soleNeed(Question question, int value, Weight weight) {
        Criteria criteria = new Criteria();
        criteria.add(new Criterion(new Answer(question, value), weight));
        return criteria;
    }

    @Test
    @Disabled
    public void answersResultsInScoredOrder() {
        smeltInt.add(new Answer(doTheyReimburseTuition, Bool.FALSE));
        pool.add(smeltInt);
        langrsoft.add(new Answer(doTheyReimburseTuition, Bool.TRUE));
        pool.add(langrsoft);

        pool.score(soleNeed(doTheyReimburseTuition, Bool.TRUE, Weight.Important));
        List<Profile> ranked = pool.ranked();

        assertThat(ranked.toArray(), equalTo(new Profile[]{langrsoft, smeltInt}));
    }
}
