package aero.s4a.escqrs.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Location {

    private final double latitude;

    private final double longitude;

    public int distanceTo(Location anotherLocation) {
        // TODO implement
        return 10;
    }
}
