package com.jsonbook.Json.Book.entity;

import com.jsonbook.Json.Book.HeaderParamType;
import com.jsonbook.Json.Book.JwtAlgoType;
import com.jsonbook.Json.Book.RequestBodyType;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "jwt_bearer")
public class JwtBearer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "jwt_id")
    private Long jwtId;

    @Enumerated(EnumType.STRING)
    @Column(name="jwt_algo")
    private JwtAlgoType jwtAlgoType;
    @Enumerated(EnumType.STRING)
    @Column(name="jwt_addto")
    private HeaderParamType headerParamType;

    @Column(name="jwt_secret")
    private String jwtSecret;

    @Column (name = "jwt_secret_encoded")
    private Boolean jwtEncoded;


    @Column(name="jwt_payload")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String jwtPayload;

    @OneToOne
    @JoinColumn(name="request_id",nullable = false)
    private Requests requests;

    public JwtBearer() {
    }

    public JwtBearer(Long jwtId, JwtAlgoType jwtAlgoType, HeaderParamType headerParamType, String jwtSecret, Boolean jwtEncoded, String jwtPayload, Requests requests) {
        this.jwtId = jwtId;
        this.jwtAlgoType = jwtAlgoType;
        this.headerParamType = headerParamType;
        this.jwtSecret = jwtSecret;
        this.jwtEncoded = jwtEncoded;
        this.jwtPayload = jwtPayload;
        this.requests = requests;
    }

    public Long getJwtId() {
        return jwtId;
    }

    public void setJwtId(Long jwtId) {
        this.jwtId = jwtId;
    }

    public JwtAlgoType getJwtAlgoType() {
        return jwtAlgoType;
    }

    public void setJwtAlgoType(JwtAlgoType jwtAlgoType) {
        this.jwtAlgoType = jwtAlgoType;
    }

    public HeaderParamType getHeaderParamType() {
        return headerParamType;
    }

    public void setHeaderParamType(HeaderParamType headerParamType) {
        this.headerParamType = headerParamType;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Boolean getJwtEncoded() {
        return jwtEncoded;
    }

    public void setJwtEncoded(Boolean jwtEncoded) {
        this.jwtEncoded = jwtEncoded;
    }

    public String getJwtPayload() {
        return jwtPayload;
    }

    public void setJwtPayload(String jwtPayload) {
        this.jwtPayload = jwtPayload;
    }

    public Requests getRequests() {
        return requests;
    }

    public void setRequests(Requests requests) {
        this.requests = requests;
    }
}
