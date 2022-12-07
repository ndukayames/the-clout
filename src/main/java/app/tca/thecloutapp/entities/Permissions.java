package app.tca.thecloutapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Permissions {
    @Id
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

}
