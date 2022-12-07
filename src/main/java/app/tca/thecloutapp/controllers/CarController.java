package app.tca.thecloutapp.controllers;

import app.tca.thecloutapp.dtos.CarDetails;
import app.tca.thecloutapp.entities.Car;
import app.tca.thecloutapp.services.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> createNewCar(@RequestBody Car car) {
        Car newCar = carService.saveCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDetails> getCarDetails(@PathVariable("id") Long id) {
        CarDetails foundCar = carService.getCarDetails(id);
        log.info("found it: {}", foundCar);
        return new ResponseEntity<>(foundCar, HttpStatus.ACCEPTED);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Car>> getCarAfterXYear(@PathVariable("year") Long year) {
        List<Car> foundCars = carService.getCarAfterXYear(year);
        return new ResponseEntity<>(foundCars, HttpStatus.ACCEPTED);
    }

    @GetMapping("/something/{id}")
    public ResponseEntity<Long> getCarSomething(@PathVariable("id") Long id) {
        Long foundCar = carService.getCarSomething(id);
        return new ResponseEntity<>(foundCar, HttpStatus.ACCEPTED);
    }

    @GetMapping("/modelName/{id}")
    public ResponseEntity<String> getCarModelName(@PathVariable("id") Long id) {
        String foundCar = carService.getCarModelName(id);
        return new ResponseEntity<>(foundCar, HttpStatus.ACCEPTED);
    }
}
