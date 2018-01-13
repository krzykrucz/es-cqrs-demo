package aero.s4a.escqrs.application;

import aero.s4a.escqrs.domain.Location;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

@Getter
public class CarQueryDTO {

    CarQueryDTO(String carId) {
        this.carId = carId;
    }

    private final String carId;

    private final List<Location> historicalRoute = Lists.newArrayList();

    public void addNewLocation(Location location) {
        historicalRoute.add(location);
    }
}
