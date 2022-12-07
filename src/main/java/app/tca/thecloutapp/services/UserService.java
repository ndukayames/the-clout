package app.tca.thecloutapp.services;

import app.tca.thecloutapp.dtos.ChangePasswordDto;
import app.tca.thecloutapp.dtos.LoginDto;
import app.tca.thecloutapp.dtos.SignupDto;
import app.tca.thecloutapp.entities.User;

public interface UserService {
    User userlogin(LoginDto loginDto);
    User userSignup(SignupDto signupDto);

    String changeUserPassword(ChangePasswordDto changePasswordDto, Long userId);
}
