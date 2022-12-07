package app.tca.thecloutapp.controllers;

import app.tca.thecloutapp.dtos.AddRoleDto;
import app.tca.thecloutapp.entities.Role;
import app.tca.thecloutapp.entities.User;
import app.tca.thecloutapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
public class RolesController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public Role addRole(@RequestBody AddRoleDto addRoleDto) {
        Role newRole = new Role();
        newRole.setName(addRoleDto.getRoleName());
        newRole = roleRepository.save(newRole);
        return newRole;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> allRoles = roleRepository.findAll();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);

    }

}
