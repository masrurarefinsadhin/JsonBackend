package com.jsonbook.Json.Book.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@EnableJpaRepositories
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mobile")
    private String mobile;

    @Column(name="address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_access", joinColumns= @JoinColumn(name="id"), inverseJoinColumns =@JoinColumn(name = "group_id") )
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "groupId")
    @JsonIdentityReference(alwaysAsId = true)
    List<Groups> groupAccess= new ArrayList<>() ;

//	@Column(name="isActive")
//	private boolean isActive;

    public User() {

    }


    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGroupAccess(List<Groups> groupAccess) {
        this.groupAccess = groupAccess;
    }

    public User(long id, String email, String password, String firstName, String middleName, String lastName, String mobile, String address, List<Groups> groupAccess) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.address = address;
        this.groupAccess = groupAccess;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public List<Groups> getGroupAccess() {
        return groupAccess;
    }
}