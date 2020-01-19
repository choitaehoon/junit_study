package com.study.chapter6;

import com.study.chapter2.Answer;
import com.study.chapter2.Bool;
import com.study.chapter2.BooleanQuestion;
import com.study.chapter2.Profile;
import com.study.chapter5.PercentileQuestion;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;

public class NewtonTest {

    private Profile profile;

    static class Newton {
        private static final double TOLERANCE = 1E-16;

        public static double squareRoot(double n) {
            double approx = n;
            while (Math.abs(approx - n / approx) > TOLERANCE * approx) {
                approx = (n / approx + approx) / 2.0;
            }
            return approx;
        }
    }
    
    private long run(int times, Runnable func) {
        long start = System.nanoTime();
        for (int i = 0; i<times; ++i)
            func.run();
        long stop = System.nanoTime();
        return (stop - start) / 1000000;
    }

    @Before
    public void createProfile() {
        profile = new Profile("Bull Hockey, Inc.");
    }

    int[] ids(Collection<Answer> answers) {
        return answers.stream()
            .mapToInt(a -> a.getQuestion().getId()).toArray();
    }

    @Test
    public void squareRoot() {
        double result = Newton.squareRoot(250.0);
        assertThat(result * result, closeTo(250.0, Newton.TOLERANCE));
    }

    @Test
    public void squareRootVerifiedUsingLibrary() {
        assertThat(Newton.squareRoot(1969.0), closeTo(Math.sqrt(1969.0), Newton.TOLERANCE));
    }

}
