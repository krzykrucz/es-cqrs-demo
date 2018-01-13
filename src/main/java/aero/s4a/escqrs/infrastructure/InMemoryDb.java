package aero.s4a.escqrs.infrastructure;

import aero.s4a.escqrs.domain.Car;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class InMemoryDb {

    private final Map<UUID, Car> cars = new HashMap<>();

    public void store(Car car) {
        cars.put(car.getId(), car);
    }

    public Car fetch(UUID carId) {
        return cars.get(carId);
    }
}
