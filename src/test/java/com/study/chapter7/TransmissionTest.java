package com.study.chapter7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransmissionTest {

    private Transmission transmission;
    private Car car;

    @BeforeEach
    public void setup() {
        car = new Car();
        transmission = new Transmission(car);
        transmission.shift(Gear.DRIVE);
    }

    @Test
    public void remainsInDriveAfterAcceleration() {
        car.accelerateTo(35);

        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }

    @Test
    public void ignoresShiftToParkWhileInDrive() {
        car.accelerateTo(30);

        transmission.shift(Gear.PARK);

        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }

    @DisplayName("side effects 검사")
    @Test
    public void allowShiftToParkWhenNotMoving() {
        car.accelerateTo(30);
        car.brakeToStop();

        transmission.shift(Gear.PARK);

        assertThat(transmission.getGear(), equalTo(Gear.PARK));
    }
}