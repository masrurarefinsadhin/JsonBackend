package com.jsonbook.Json.Book.controller;

import com.jsonbook.Json.Book.entity.Groups;
import com.jsonbook.Json.Book.entity.Roles;
import com.jsonbook.Json.Book.entity.User;
import com.jsonbook.Json.Book.repository.UserRepository;
import com.jsonbook.Json.Book.service.RolesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:35549"} )
@RequestMapping("/roles")
public class RolesController {
    private final RolesService rolesService;
    private final UserRepository userRepository;

    public RolesController(RolesService rolesService, UserRepository userRepository) {
        this.rolesService = rolesService;
        this.userRepository = userRepository;
    }
    @PostMapping("/create-new-role")
    private Roles createNewRole(@RequestBody Roles roles){
        return rolesService.addRole(roles);
    }

    @GetMapping ("/assign-role/{id}/{role}")
    private void assignRole(@PathVariable("id") Long id, @PathVariable("role")String role){
        User user= userRepository.findById(id).get();
        Roles roles=rolesService.findRoles(role);
        List<Roles> roleSet= user.getRoleAccess();
        roleSet.add(roles);
        user.setRoleAccess(roleSet);
        userRepository.save(user);
    }
    @GetMapping("/show-roles")
    private List<Roles> showAllRoles(){
        return rolesService.showAllRoles();
    }

}
