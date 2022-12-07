package app.tca.thecloutapp.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {
    public String originalPassword;
    public String newPassword;
    public String newPassword2;
}
