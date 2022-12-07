package app.tca.thecloutapp.controllers;

import app.tca.thecloutapp.configs.jwt.JwtUtils;
import app.tca.thecloutapp.dtos.ChangePasswordDto;
import app.tca.thecloutapp.dtos.LoginDto;
import app.tca.thecloutapp.dtos.SignupDto;
import app.tca.thecloutapp.entities.Role;
import app.tca.thecloutapp.entities.User;
import app.tca.thecloutapp.models.ResponseModel;
import app.tca.thecloutapp.models.SuccessResponse;
import app.tca.thecloutapp.repositories.RoleRepository;
import app.tca.thecloutapp.repositories.UserRepository;
import app.tca.thecloutapp.services.CustomAuthenticationProvider;
import app.tca.thecloutapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("signin")
    public ResponseEntity<ResponseModel> authenticateUser(HttpServletRequest request, @RequestBody LoginDto loginDto) {
        try {
            String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Request info: {}", principal);
            User user = userService.userlogin(loginDto);
            String jwt = jwtUtils.generateJwtToken(user.getUsername());
            ResponseModel responseModel = new SuccessResponse<User>(true, "logged in successfully", jwt, user);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } catch (RuntimeException ex) {
            ResponseModel responseModel = new ResponseModel(false, ex.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.FORBIDDEN);
        }
    }

    @PatchMapping("change-password/{userId}")
    public ResponseEntity<ResponseModel> changeUserPassword( @RequestBody ChangePasswordDto changePasswordDto, @PathVariable("userId") Long userId) {
        try {
            String result = userService.changeUserPassword(changePasswordDto, userId);
            ResponseModel responseModel = new SuccessResponse<User>(true, result );
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } catch (RuntimeException ex) {
            ResponseModel responseModel = new ResponseModel(false, ex.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/api/user")
//    public ResponseEntity<Object> userAccess(Authentication principal) {
//        UserDetails userDetails = (UserDetails) principal.getPrincipal();
//
//        logger.info("Request info: {} {}", userDetails.getUsername(), userDetails.getPassword());
//        return new ResponseEntity<>(userDetails, HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signUpDto) {

        // add check for username exists in a DB
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // find role
        Optional<Role> userRole = roleRepository.findByName(signUpDto.getRole().getName());
        if(userRole.isEmpty()) {
            return new ResponseEntity<>("Role doesn't exist!", HttpStatus.BAD_REQUEST);
        }
        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRoles(userRole.get());

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
