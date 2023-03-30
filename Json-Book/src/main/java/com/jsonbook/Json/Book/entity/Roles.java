package com.jsonbook.Json.Book.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jsonbook.Json.Book.JwtAlgoType;
import com.jsonbook.Json.Book.RoleType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@EnableJpaRepositories
public class Roles {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name="role_id")
    private RoleType roleType;



    @Column(name="role_description")
    private String description;

    @ManyToMany(mappedBy = "roleAccess")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    List<User> userAccess= new ArrayList<>();


    public Roles() {

    }
    public Roles(RoleType roleType, String description, List<User> userAccess) {
        this.roleType = roleType;
        this.description = description;
        this.userAccess = userAccess;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public String getDescription() {
        return description;
    }

    public List<User> getUserAccess() {
        return userAccess;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserAccess(List<User> userAccess) {
        this.userAccess = userAccess;
    }
}
