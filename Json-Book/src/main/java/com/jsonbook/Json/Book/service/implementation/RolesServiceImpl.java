package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.RoleType;
import com.jsonbook.Json.Book.entity.Roles;
import com.jsonbook.Json.Book.repository.RoleRepository;
import com.jsonbook.Json.Book.service.RolesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
    private final RoleRepository roleRepository;

    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Roles addRole(Roles roles){
        return roleRepository.save(roles);
    }

    @Override
    public Roles findRoles(String role) {
        return roleRepository.findById(RoleType.valueOf(role)).get();
    }

    @Override
    public List<Roles> showAllRoles() {
        return roleRepository.findAll();
    }
}
