package aero.s4a.escqrs.infrastructure;

import aero.s4a.escqrs.domain.CarEvent;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class CarEventStream {

    private UUID carId;

    private List<CarEvent> events;

    public void append(List<CarEvent> events) {
        this.events.addAll(events);
    }
}
