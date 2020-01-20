package com.study.chapter7;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class BearingTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void throwsOnNegativeNumber() {
        exception.expect(BearingOutOfRangeException.class);
        exception.expectMessage("BearingOutOfRangeException");

        new Bearing(-1);
    }

    @Test
    public void throwsWhenBearingTooLarge() {
        new Bearing(Bearing.MAX + 1);
    }

    @Test
    public void answersValidBearing() {
        int value = new Bearing(15).angleBetween(new Bearing(12));
        assertTrue(value > 0 && value < Bearing.MAX );
    }

    @Test
    public void angleBetweenIsNegativeWhenThisBearingSmaller() {
        assertThat(new Bearing(12).angleBetween(new Bearing(15)), equalTo(-3));
    }
}