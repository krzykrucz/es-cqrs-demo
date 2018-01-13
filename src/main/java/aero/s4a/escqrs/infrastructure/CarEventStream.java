package aero.s4a.escqrs.infrastructure;

import aero.s4a.escqrs.domain.CarEvent;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

@Getter
class CarEventStream {

    private final UUID carId;

    private List<CarEvent> events = newArrayList();

    private long version;

    CarEventStream(UUID carId) {
        this.carId = carId;
    }

    public void append(List<CarEvent> events) {
        this.events.addAll(events);
        this.version++;
    }
}
