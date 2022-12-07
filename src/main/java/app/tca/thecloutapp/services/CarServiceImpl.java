package app.tca.thecloutapp.services;

import app.tca.thecloutapp.dtos.CarDetails;
import app.tca.thecloutapp.entities.Car;
import app.tca.thecloutapp.repositories.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public Car saveCar(Car car) {
        Car newCar = new Car();
        newCar.setModel(car.getModel());
        newCar.setYear(car.getYear());
        return carRepository.save(newCar);
    }

    @Override
    public CarDetails getCarDetails(Long id) {
        return carRepository.findCarById(id);
    }

    @Override
    public List<Car> getCarAfterXYear(Long year) {
        return carRepository.findCarByYear();
    }

    @Override
    public Long getCarSomething(Long id) {
        Long check = carRepository.getCarSomething();
        log.info("Checking: {}", check);
        return check;
    }

    @Override
    public String getCarModelName(Long id) {
        String check = carRepository.getCarModelName(id);
        log.info("Checking: {}", check);
        return check;
    }
}
