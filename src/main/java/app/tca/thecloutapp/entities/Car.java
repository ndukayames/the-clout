package app.tca.thecloutapp.entities;

import app.tca.thecloutapp.dtos.CarDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "getCarDetails",
        procedureName = "get_car_details",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "car_id", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "model", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "year", type = Long.class)
            }
    )
})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String model;

    @Column
    private Long year;

    // standard getters and setters
}
