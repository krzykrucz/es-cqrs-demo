package aero.s4a.escqrs.infrastructure;

import aero.s4a.escqrs.domain.CarEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.google.common.collect.Maps.newHashMap;

@Component
class EventStore {

    private final Map<UUID, CarEventStream> cars = newHashMap();

    void saveEvents(UUID carId, List<CarEvent> events) {
        if (!cars.containsKey(carId)) {
            cars.put(carId, new CarEventStream(carId));
        }
        cars.get(carId)
                .append(events);
    }

    CarEventStream getAllBy(UUID carId) {
        return cars.get(carId);
    }

}
