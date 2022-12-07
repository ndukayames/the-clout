package app.tca.thecloutapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 60)
    private String name;

//    @OneToMany
//    @JoinColumn(name = "roles")
//    private List<Permissions> permissions;
}
