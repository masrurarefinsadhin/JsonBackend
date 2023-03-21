package com.jsonbook.Json.Book.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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

//	@Column(name="isActive")
//	private boolean isActive;

    public User() {

    }

    public User(String email, String password, String firstName, String middleName, String lastName, String mobile, String address) {
        super();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.address = address;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }





}