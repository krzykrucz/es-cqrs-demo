package aero.s4a.escqrs.domain;

import lombok.Getter;

import java.util.UUID;

/**
 * Aggregate root
 */
@Getter
public class Car {

    private final UUID id;

    private Location currentLocation;

    private int mileage = 0;

    public Car(UUID id) {
        this.id = id;
    }

    public void moveTo(Location location) {
        this.mileage += location.distanceTo(currentLocation);
        this.currentLocation = location;

    }
}
