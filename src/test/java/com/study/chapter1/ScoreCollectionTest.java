package com.study.chapter1;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ScoreCollectionTest {

    @Test
    public void 두수를_더하고_2를_나눈_결과() {

        //given
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        //when
        int actualResult = collection.arithmeticMean();

        //then
        assertThat(actualResult, equalTo(6));
    }
}