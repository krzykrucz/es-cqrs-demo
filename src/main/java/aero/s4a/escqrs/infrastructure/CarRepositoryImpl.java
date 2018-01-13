package aero.s4a.escqrs.infrastructure;

import aero.s4a.escqrs.domain.Car;
import aero.s4a.escqrs.domain.CarEvent;
import aero.s4a.escqrs.domain.CarRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private final EventStore eventStore;

    private final ApplicationEventPublisher publisher;

    public CarRepositoryImpl(EventStore eventStore, ApplicationEventPublisher publisher) {
        this.eventStore = eventStore;
        this.publisher = publisher;
    }

    @Override
    public void save(Car car) {
        final List<CarEvent> newEvents = car.getNewEventsAndCommit();
        eventStore.saveEvents(car.getId(), newEvents);
        newEvents.forEach(publisher::publishEvent);
    }

    @Override
    public Car findOne(UUID carId) {
        final CarEventStream eventStream = eventStore.getAllBy(carId);
        return Car.from(carId, eventStream.getEvents());
    }
}
