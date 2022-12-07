package app.tca.thecloutapp.dtos;

import app.tca.thecloutapp.entities.Role;
import lombok.Data;

@Data
public class SignupDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private Role role;
}
