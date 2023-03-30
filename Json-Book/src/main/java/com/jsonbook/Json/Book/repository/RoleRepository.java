package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.RoleType;
import com.jsonbook.Json.Book.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, RoleType> {

}
