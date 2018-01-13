package aero.s4a.escqrs.domain;

import java.util.UUID;

public interface CarRepository {

    void save(Car car);

    Car findOne(UUID carId);
}
