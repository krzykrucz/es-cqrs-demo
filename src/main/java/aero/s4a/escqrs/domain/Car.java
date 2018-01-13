package aero.s4a.escqrs.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate root
 */
@Getter
public class Car {

    private final UUID id;

    private Location currentLocation;

    private int mileage = 0;

    private List<CarEvent> newEvents = Lists.newArrayList();

    public Car(UUID id) {
        this.id = id;
    }

    public static Car from(UUID id, List<CarEvent> historicalEvents) {
        final Car car = new Car(id);
        historicalEvents.forEach(car::apply);
        return car;
    }

    public void moveTo(Location location) {
        final CarEvent carMovedEvent = new CarMoved(Instant.now(), this.id, location);
        applyNew(carMovedEvent);
    }

    public List<CarEvent> getNewEventsAndCommit() {
        final List<CarEvent> eventsList = ImmutableList.copyOf(newEvents);
        newEvents.clear();
        return eventsList;
    }

    private void applyNew(CarEvent event) {
        apply(event);
        newEvents.add(event);
    }

    private void apply(CarEvent event) {
        if (event instanceof CarMoved) {
            apply((CarMoved) event);
        }
    }

    private void apply(CarMoved carMovedEvent) {
        final Location location = carMovedEvent.getNewLocation();
        this.mileage += location.distanceTo(currentLocation);
        this.currentLocation = location;
    }
}
