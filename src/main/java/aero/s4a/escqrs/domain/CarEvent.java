package aero.s4a.escqrs.domain;

import java.time.Instant;
import java.util.UUID;

public interface CarEvent {

    Instant generatedAt();

    UUID getCarId();
}
