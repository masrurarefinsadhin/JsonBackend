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

}
