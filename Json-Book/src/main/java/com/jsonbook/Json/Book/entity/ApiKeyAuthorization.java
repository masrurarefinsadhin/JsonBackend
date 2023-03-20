package com.jsonbook.Json.Book.entity;

import com.jsonbook.Json.Book.HeaderParamType;
import com.jsonbook.Json.Book.RequestBodyType;

import javax.persistence.*;

@Entity
@Table(name = "apikey_authorization")
public class ApiKeyAuthorization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "api_id")
    private Long apiId;

    @Column(name="api_key",nullable = false)
    private String apiKey;

    @Column(name="api_value",nullable = false)
    private String apiValue;

    @Enumerated(EnumType.STRING)
    @Column(name="api_addto")
    private HeaderParamType headerParamType;

    @ManyToOne
    @JoinColumn(name="request_id",nullable = false)
    private Requests requests;
}
