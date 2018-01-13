package aero.s4a.escqrs.application;

import aero.s4a.escqrs.domain.Car;
import aero.s4a.escqrs.domain.CarRepository;
import aero.s4a.escqrs.domain.Location;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarCommandApplicationService {

    private final CarRepository carRepository;

    public CarCommandApplicationService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void moveCar(UUID carId, Location location) {
        final Car car = carRepository.findOne(carId);
        car.moveTo(location);
        carRepository.save(car);
    }
}
