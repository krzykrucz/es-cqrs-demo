package aero.s4a.escqrs.infrastructure;

import aero.s4a.escqrs.domain.Car;
import aero.s4a.escqrs.domain.CarRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private final InMemoryDb db;

    public CarRepositoryImpl(InMemoryDb db) {
        this.db = db;
    }

    @Override
    public void save(Car car) {
        db.store(car);
    }

    @Override
    public Car findOne(UUID carId) {
        return db.fetch(carId);
    }
}
