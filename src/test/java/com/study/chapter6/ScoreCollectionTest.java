package com.study.chapter6;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.study.chapter1.ScoreCollection;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.ExpectedException;

public class ScoreCollectionTest {

    private ScoreCollection collection;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeEach
    public void setup() {
        collection = new ScoreCollection();
    }

    @Test(expected=IllegalArgumentException.class)
    public void throwsExceptionWhenAddingNull() {
        collection.add(null);
    }

}
