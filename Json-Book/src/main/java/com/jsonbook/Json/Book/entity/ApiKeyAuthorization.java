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

    public Long getApiId() {
        return apiId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiValue(String apiValue) {
        this.apiValue = apiValue;
    }

    public void setHeaderParamType(HeaderParamType headerParamType) {
        this.headerParamType = headerParamType;
    }

    public void setRequests(Requests requests) {
        this.requests = requests;
    }

    public String getApiValue() {
        return apiValue;
    }

    public HeaderParamType getHeaderParamType() {
        return headerParamType;
    }

    public Requests getRequests() {
        return requests;
    }
    public ApiKeyAuthorization(){}
    public ApiKeyAuthorization(Long apiId, String apiKey, String apiValue, HeaderParamType headerParamType, Requests requests) {
        this.apiId = apiId;
        this.apiKey = apiKey;
        this.apiValue = apiValue;
        this.headerParamType = headerParamType;
        this.requests = requests;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="api_addto")
    private HeaderParamType headerParamType;

    @OneToOne
    @JoinColumn(name="request_id",nullable = false)
    private Requests requests;
}
