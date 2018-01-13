package aero.s4a.escqrs.application;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarQueryApplicationService {

    private final CarReadProjector carReadProjector;

    public CarQueryApplicationService(CarReadProjector carReadProjector) {
        this.carReadProjector = carReadProjector;
    }

    public CarQueryDTO getCar(UUID id) {
        return carReadProjector.getCarReadModel(id);
    }
}
