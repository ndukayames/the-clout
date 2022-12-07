package app.tca.thecloutapp.controllers;

import app.tca.thecloutapp.entities.Permissions;
import app.tca.thecloutapp.entities.Role;
import app.tca.thecloutapp.entities.User;
import app.tca.thecloutapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false, name = "role") String roleName) {
        List<User> allUsers;
        if(roleName != null) {
            log.info("role name: {}", roleName);
            allUsers = userRepository.findByRoleName(roleName);
        } else {
            allUsers = userRepository.findAll();
        }
//        Permissions permissions = new Permissions();
//        permissions.getName();
//        Role role = new Role();
//        role.
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("find")
    public ResponseEntity<List<User>> findUsersByRole() {
        List<User> allUsers = userRepository.findAll();
//        Permissions permissions = new Permissions();
//        permissions.getName();
//        Role role = new Role();
//        role.
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
