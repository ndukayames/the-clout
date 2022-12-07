package app.tca.thecloutapp.services;


import app.tca.thecloutapp.dtos.ChangePasswordDto;
import app.tca.thecloutapp.dtos.LoginDto;
import app.tca.thecloutapp.dtos.SignupDto;
import app.tca.thecloutapp.entities.User;
import app.tca.thecloutapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User userlogin(LoginDto loginDto) {
        // check if user exists
        User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()).get();

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // compare password
        if(passwordEncoder.matches(loginDto.getPassword(),user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invaid password, please try again");
    }

    @Override
    public User userSignup(SignupDto signupDto) {
        return null;
    }

    @Override
    public String changeUserPassword(ChangePasswordDto changePasswordDto, Long userId) {
        // find user
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            // change password
            User presentUser = user.get();
            presentUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
            userRepository.save(presentUser);

            return "Password changed successfully";
        } else {
            throw new RuntimeException("Error changing user password");
        }
    }
}
