package app.tca.thecloutapp.services;

import app.tca.thecloutapp.dtos.CarDetails;
import app.tca.thecloutapp.entities.Car;

import java.util.List;

public interface CarService {
    Car saveCar(Car car);
    CarDetails getCarDetails(Long id);

    List<Car> getCarAfterXYear(Long year);

    Long getCarSomething(Long id);

    String getCarModelName(Long id);
}
