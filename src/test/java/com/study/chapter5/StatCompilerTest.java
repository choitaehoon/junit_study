package com.study.chapter5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class StatCompilerTest {

    private StatCompiler compiler;
    private List<BooleanAnswer> answers;
    private Map<Integer, String> questions;

    @BeforeEach
    public void setup() {
        compiler = new StatCompiler();
        answers = new ArrayList<>();
        questions = new HashMap<>();

        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, false));

        answers.add(new BooleanAnswer(2, true));
        answers.add(new BooleanAnswer(2, true));

        questions.put(1, "Tuition reimbursement?");
        questions.put(2, "Relocation package?");
    }

    @DisplayName("데이터베이스에 의존된 코드 제외 시키고 메모리상 해쉬 맵만 사용하여 속도 높임")
    @Test
    public void responsesByQuestionAnswersCountsByQuestionText() {
        Map<String, Map<Boolean, AtomicInteger>> responses = compiler.responsesByQuestion(answers, questions);

        assertThat(responses.get("Tuition reimbursement?").get(Boolean.TRUE).get(), equalTo(3));
        assertThat(responses.get("Tuition reimbursement?").get(Boolean.FALSE).get(), equalTo(1));
        assertThat(responses.get("Relocation package?").get(Boolean.TRUE).get(), equalTo(2));
        assertThat(responses.get("Relocation package?").get(Boolean.FALSE).get(), equalTo(0));
    }

}