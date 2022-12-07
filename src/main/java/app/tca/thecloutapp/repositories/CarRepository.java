package app.tca.thecloutapp.repositories;

import app.tca.thecloutapp.dtos.CarDetails;
import app.tca.thecloutapp.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional(readOnly = true)
public interface CarRepository extends JpaRepository<Car,Long> {
//    @Transactional(readOnly = true)
    @Procedure("find_all_cars")
    List<Car> findCarByYear();
//    @Transactional(readOnly = true)
    @Procedure(name = "getCarDetails")
    CarDetails findCarById(@Param("car_id") Long car_id);

    @Procedure(value = "get_car_something")
    Long getCarSomething();

    @Procedure(value = "get_car_name")
    String getCarModelName(Long id);
}
