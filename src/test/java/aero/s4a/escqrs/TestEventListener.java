package aero.s4a.escqrs;

import aero.s4a.escqrs.domain.CarEvent;
import lombok.Getter;
import org.assertj.core.util.Lists;
import org.springframework.context.event.EventListener;

import java.util.List;

public class TestEventListener {

    @Getter
    private final List<CarEvent> events = Lists.newArrayList();

    @EventListener
    public void onEvent(CarEvent carEvent) {
        events.add(carEvent);
    }

}
