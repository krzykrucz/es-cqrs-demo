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

    public void moveTo(Location location) {
        this.mileage += location.distanceTo(currentLocation);
        this.currentLocation = location;
        final CarEvent carMovedEvent = new CarMoved(Instant.now(), this.id, location);
        newEvents.add(carMovedEvent);
    }

    public List<CarEvent> getNewEventsAndCommit() {
        final List<CarEvent> eventsList = ImmutableList.copyOf(newEvents);
        newEvents.clear();
        return eventsList;
    }
}
