package aero.s4a.escqrs.application;

import aero.s4a.escqrs.domain.CarEvent;
import aero.s4a.escqrs.domain.CarMoved;
import aero.s4a.escqrs.domain.Location;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarReadProjector {

    private final Map<UUID, CarQueryDTO> readModels = new ConcurrentHashMap<>();

    @EventListener
    void onCarUpdate(CarEvent carEvent) {
        if (carEvent instanceof CarMoved) {
            final Location newLocation = ((CarMoved) carEvent).getNewLocation();
            final UUID carId = carEvent.getCarId();
            if (!readModels.containsKey(carId)) {
                readModels.put(carId, new CarQueryDTO(carId.toString()));
            }
            CarQueryDTO carQueryDTO = readModels.get(carId);
            carQueryDTO.addNewLocation(newLocation);
            return;
        }
        throw new IllegalStateException("unknown event");
    }

    public CarQueryDTO getCarReadModel(UUID carId) {
        return readModels.get(carId);
    }

}
