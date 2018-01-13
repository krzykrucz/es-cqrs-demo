package aero.s4a.escqrs;

import aero.s4a.escqrs.application.CarCommandApplicationService;
import aero.s4a.escqrs.application.CarQueryApplicationService;
import aero.s4a.escqrs.application.CarQueryDTO;
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
@SpringBootTest(classes = {TestConfig.class, EsCqrsApplication.class})
public class EsCqrsApplicationTests {

    private static final Location LOCATION = new Location(51, 19);
    private static final Location LOCATION_2 = new Location(52, 19);
    @Autowired
    TestEventListener testEventListener;
    @Autowired
    private CarCommandApplicationService carCommandApplicationService;
    @Autowired
    private CarQueryApplicationService carQueryApplicationService;
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
        assertEquals(carRepository.findOne(id).getMileage(), 10);
        assertEquals(testEventListener.getEvents().get(0).getCarId(), id);
    }

    @Test
    public void testCarsReadModel() {
        //given
        UUID id = UUID.randomUUID();
        carRepository.save(new Car(id));

        //when
        carCommandApplicationService.moveCar(id, LOCATION);
        carCommandApplicationService.moveCar(id, LOCATION_2);
        final CarQueryDTO carDTO = carQueryApplicationService.getCar(id);

        //then
        assertEquals(carDTO.getHistoricalRoute().get(0), LOCATION);
        assertEquals(carDTO.getHistoricalRoute().get(1), LOCATION_2);
    }

}
