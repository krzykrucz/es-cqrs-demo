package aero.s4a.escqrs.infrastructure;

import aero.s4a.escqrs.domain.CarEvent;
import com.google.common.collect.ArrayListMultimap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
class EventStore {

    private final ArrayListMultimap<UUID, CarEvent> cars = ArrayListMultimap.create();

    void saveEvents(UUID carId, List<CarEvent> events) {
        cars.putAll(carId, events);
    }

    List<CarEvent> getAllBy(UUID carId) {
        return cars.get(carId);
    }

}
