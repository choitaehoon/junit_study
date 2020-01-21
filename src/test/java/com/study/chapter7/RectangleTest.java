package com.study.chapter7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.study.chapter7.ConstrainsSidesTo.constrainsSidesTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RectangleTest {

    private Rectangle rectangle;

    @AfterEach
    public void ensureInvariant() {
        assertThat(rectangle, constrainsSidesTo(100));
    }

    @Test
    public void answersArea() {
        rectangle = new Rectangle(new Point(5, 5), new Point(15, 10));
        assertThat(rectangle.area(), equalTo(50));
    }

    @Test
    public void allowDynamicallyChangingSize() {
        rectangle = new Rectangle(new Point(5, 5));
        rectangle.setOppositeCorner(new Point(130, 130));

        assertThat(rectangle.area(), equalTo(15625));
    }
}