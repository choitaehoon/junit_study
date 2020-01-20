package com.study.chapter7;

import com.study.chapter2.Criteria;
import com.study.chapter2.Profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProfilePool {

    private List<Profile> profiles = new ArrayList<>();

    public void add(Profile profile) {
        profiles.add(profile);
    }

    public void score(Criteria criteria) {
        for (Profile profile : profiles) {
            profile.matches(criteria);
        }
    }

    public List<Profile> ranked() {
        profiles.sort((p1, p2) -> Integer.compare(p2.score(), p1.score()));
        return profiles;
    }

}
