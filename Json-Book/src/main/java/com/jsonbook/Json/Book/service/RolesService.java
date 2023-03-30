package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.entity.Roles;

import java.util.List;

public interface RolesService {
    Roles addRole(Roles roles);
    Roles findRoles(String role);
    List<Roles> showAllRoles();
}
