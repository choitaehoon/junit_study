package com.study.chapter13;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class ProfileMatcher {
   private Map<String, Profile> profiles = new HashMap<>(); 
   private static final int DEFAULT_POOL_SIZE = 4;

   public void add(Profile profile) {
      profiles.put(profile.getId(), profile);
   }
    
   public void findMatchingProfiles(
         Criteria criteria, MatchListener listener) {
      ExecutorService executor = 
            Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);

      for (MatchSet set: collectMatchSets(criteria)) {
         Runnable runnable = () -> process(listener, set);
         executor.execute(runnable);
      }
      executor.shutdown();
   }

   List<MatchSet> collectMatchSets(Criteria criteria) {
      return profiles.values().stream()
                     .map(profile -> profile.getMatchSet(criteria))
                     .collect(Collectors.toList());
   }

   void process(MatchListener listener, MatchSet matchSet) {
      if (matchSet.matches())
         listener.foundMatch(profiles.get(matchSet.getProfileId()), matchSet);
   }
}
