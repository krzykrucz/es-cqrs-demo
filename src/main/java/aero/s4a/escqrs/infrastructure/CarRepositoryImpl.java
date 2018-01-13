package aero.s4a.escqrs.infrastructure;

import aero.s4a.escqrs.domain.Car;
import aero.s4a.escqrs.domain.CarRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private final InMemoryDb db;

    private final ApplicationEventPublisher publisher;

    public CarRepositoryImpl(InMemoryDb db, ApplicationEventPublisher publisher) {
        this.db = db;
        this.publisher = publisher;
    }

    @Override
    public void save(Car car) {
        db.store(car);
        car.getNewEventsAndCommit()
                .forEach(publisher::publishEvent);
    }

    @Override
    public Car findOne(UUID carId) {
        return db.fetch(carId);
    }
}
