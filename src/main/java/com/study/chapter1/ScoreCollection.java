package com.study.chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreCollection {
    private List<Scoreable> scores = new ArrayList<>();
    private static int SIZE_ZERO = 0;

    public void add(Scoreable scoreable) {
        Optional.ofNullable(scoreable).orElseThrow(IllegalArgumentException::new);

        scores.add(scoreable);
    }

    public int arithmeticMean() {
        if (scores.size() == SIZE_ZERO) {
            return SIZE_ZERO;
        }

        long total = scores.stream()
                .mapToInt(Scoreable::getScore)
                .sum();

        return (int)(total / scores.size());
    }
}
