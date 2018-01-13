package aero.s4a.escqrs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public class CarMoved implements CarEvent {

    private final Instant generatedTime;

    private final UUID carId;

    @Getter
    private final Location newLocation;

    @Override
    public Instant generatedAt() {
        return generatedTime;
    }

    @Override
    public UUID getCarId() {
        return carId;
    }
}
