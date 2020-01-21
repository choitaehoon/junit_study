package com.study.chapter7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class SparseArrayTest {

    private SparseArray<Object> array;

    @BeforeEach
    public void setup() {
        array = new SparseArray<>();
    }

    @Test
    public void handlesInsertionInDescendingOrder() {
        array.put(7, "seven");
        array.checkInvariants();
        array.put(6, "six");
        array.checkInvariants();

        assertThat(array.get(6), equalTo("six"));
        assertThat(array.get(7), equalTo("seven"));
    }
}