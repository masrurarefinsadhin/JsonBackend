package com.jsonbook.Json.Book.entity;

import com.jsonbook.Json.Book.RequestBodyType;

import javax.persistence.*;
@Entity
@Table(name="basic_authorization")
public class BasicAuthorization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "basic_authorization_id")
    private Long basicId;

    public BasicAuthorization(Long basicId, String basicUsername, String basicPassword, Requests requests) {
        this.basicId = basicId;
        this.basicUsername = basicUsername;
        this.basicPassword = basicPassword;
        this.requests = requests;
    }

    @Column(name="basic_authorization_username",nullable = false)
    private String basicUsername;

    public BasicAuthorization() {

    }

    public void setBasicId(Long basicId) {
        this.basicId = basicId;
    }

    public void setBasicUsername(String basicUsername) {
        this.basicUsername = basicUsername;
    }

    public void setBasicPassword(String basicPassword) {
        this.basicPassword = basicPassword;
    }

    public void setRequests(Requests requests) {
        this.requests = requests;
    }

    public Long getBasicId() {
        return basicId;
    }

    public String getBasicUsername() {
        return basicUsername;
    }

    public String getBasicPassword() {
        return basicPassword;
    }

    public Requests getRequests() {
        return requests;
    }

    @Column(name="basic_authorization_password",nullable = false)
    private String basicPassword;


    @OneToOne
    @JoinColumn(name="request_id",nullable = false)
    private Requests requests;
}
