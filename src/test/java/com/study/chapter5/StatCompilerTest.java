package com.study.chapter5;

import com.study.chapter2.BooleanQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

class StatCompilerTest {

    private StatCompiler compiler;
    private List<BooleanAnswer> answers;
    private Map<Integer, String> questions;

    @Mock
    private QuestionController controller;

    @InjectMocks
    private StatCompiler stats;

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

        stats = new StatCompiler();
        MockitoAnnotations.initMocks(this);
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

    @Test
    public void question_text_does_stuff() {
        when(controller.find(1))
                .thenReturn(new BooleanQuestion("text1"));
        when(controller.find(2))
                .thenReturn(new BooleanQuestion("text2"));
        List<BooleanAnswer> answer = answerAdd();

        Map<Integer, String> questionText = stats.questionText(answer);
        Map<Integer, String> expected = expectAdd();

        assertThat(questionText, equalTo(expected));
    }

    private List<BooleanAnswer> answerAdd() {
        List<BooleanAnswer> answer = new ArrayList<>();

        answer.add(new BooleanAnswer(1, true));
        answer.add(new BooleanAnswer(2, true));

        return answer;
    }

    private Map<Integer, String> expectAdd() {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "text1");
        map.put(2, "text2");

        return map;
    }

}