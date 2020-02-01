package com.study.chapter12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    @Test
    public void match_agains_null_answer_returns_false() {
        assertFalse(new Answer(new BooleanQuestion(0, ""), Bool.TRUE).match(null));
    }
}