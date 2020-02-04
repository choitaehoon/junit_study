package com.study.chapter13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

class ProfileMatcherTest {

    private MatchListener listener;
    private ProfileMatcher matcher;
    private Profile matchingProfile;
    private Profile nonMatchingProfile;
    private BooleanQuestion question;
    private Criteria criteria;

    @BeforeEach
    public void setup() {
        listener = mock(MatchListener.class);
        matcher = new ProfileMatcher();
        question = new BooleanQuestion(1, "");
        criteria = new Criteria();

        matchingProfile = createMatchingProfile("matching");
        nonMatchingProfile = createNonMatchingProfile("nonMatching");
    }

    private Profile createMatchingProfile(String name) {
        Profile profile = new Profile(name);
        profile.add(matchingAnswer());
        return profile;
    }

    private Profile createNonMatchingProfile(String name) {
        Profile profile = new Profile(name);
        profile.add(nonMatchingAnswer());
        return profile;
    }

    private Answer matchingAnswer() {
        return new Answer(question, Bool.TRUE);
    }

    private Answer nonMatchingAnswer() {
        return new Answer(question, Bool.FALSE);
    }

    @Test
    public void process_notifies_listener_on_match() {
        matcher.add(matchingProfile);
        MatchSet set = matchingProfile.getMatchSet(criteria);

        matcher.process(listener, set);

        verify(listener).foundMatch(matchingProfile, set);
    }

    @Test
    public void process_does_not_notify_listener_when_no_match() {
        matcher.add(nonMatchingProfile);
        MatchSet set = nonMatchingProfile.getMatchSet(criteria);

        matcher.process(listener, set);

        verify(listener, never()).foundMatch(nonMatchingProfile, set);
    }

    @Test
    public void gathers_matching_profiles() {
        Set<String> processedSets =
                Collections.synchronizedSet(new HashSet<>());
        BiConsumer<MatchListener, MatchSet> processFunction =
                (listener, set) -> processedSets.add(set.getProfileId());
        List<MatchSet> matchSets = createMatchSets(100);

        matcher.findMatchingProfiles(
                criteria, listener, matchSets, processFunction);

        while (!matcher.getExecutor().isTerminated());

        assertThat(processedSets, equalTo(matchSets.stream()
            .map(MatchSet::getProfileId).collect(Collectors.toSet())));
    }

    private List<MatchSet> createMatchSets(int count) {
        List<MatchSet> sets = new ArrayList<>();
        for (int i = 0; i < count; i++)
            sets.add(new MatchSet(String.valueOf(i), null, null));
        return sets;
    }
}
