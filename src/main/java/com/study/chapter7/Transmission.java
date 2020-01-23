package com.study.chapter7;

import java.util.Optional;

public class Transmission {

    private Gear gear;
    private Moveable moveable;

    public Transmission(Moveable moveable) {
        this.moveable = moveable;
    }

    public void shift(Gear gear) {
        Optional.ofNullable(gear)
                .orElseThrow(() -> new GearNullException("Gear에 값이 없습니다"));

        if (moveable.currentSpeedInMph() > 0 && gear == Gear.PARK) return;
        this.gear = gear;
    }

    public Gear getGear() {
        return gear;
    }

}
