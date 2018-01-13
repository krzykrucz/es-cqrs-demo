package aero.s4a.escqrs;

import aero.s4a.escqrs.application.CarCommandApplicationService;
import aero.s4a.escqrs.domain.Car;
import aero.s4a.escqrs.domain.CarRepository;
import aero.s4a.escqrs.domain.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsCqrsApplicationTests {

    private static final Location LOCATION = new Location(51, 19);

    @Autowired
    private CarCommandApplicationService carCommandApplicationService;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testCars() {
        //given
        UUID id = UUID.randomUUID();
        carRepository.save(new Car(id));

        //when
        carCommandApplicationService.moveCar(id, LOCATION);

        //then
        assertEquals(carRepository.findOne(id).getCurrentLocation(), LOCATION);
    }

}
