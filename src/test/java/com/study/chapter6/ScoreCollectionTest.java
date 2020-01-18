package com.study.chapter6;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.study.chapter1.ScoreCollection;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;

public class ScoreCollectionTest {

    private ScoreCollection collection;

    @Before
    public void setup() {
        collection = new ScoreCollection();
    }

    @Test(expected=IllegalArgumentException.class)
    public void throwsExceptionWhenAddingNull() {
        collection.add(null);
    }

    @Test
    public void answersZeroWhenNoElementsAdded() {
        assertThat(collection.arithmeticMean(), equalTo(0));
    }

    @DisplayName("큰 정수를 다룰 때 Integer.MAX_VALUE 초과 할 경우")
    @Test
    public void dealsWithIntegerOverflow() {
        collection.add(() -> Integer.MAX_VALUE);
        collection.add(() -> 1);

        assertThat(collection.arithmeticMean(), equalTo(1073741824));
    }

    @Test
    public void doesNotProperlyHandleIntegerOverflow() {
        collection.add(() -> Integer.MAX_VALUE);
        collection.add(() -> 1);

        assertTrue(collection.arithmeticMean() < 0);
    }

}
