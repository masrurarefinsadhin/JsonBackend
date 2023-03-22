package com.jsonbook.Json.Book.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import com.jsonbook.Json.Book.AuthorizationType;
import com.jsonbook.Json.Book.RequestBodyType;
@Entity
@Table(name="requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private long requestId;

    @Column(name = "request_name",nullable = false)
    private String requestName;

    @Enumerated(EnumType.STRING)
    @Column(name="request_method",nullable = false)
    private RequestMethod requestMethod;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "request_url",nullable = false)
    private String url;
    /*@Column(name = "request_header")
    @Type(type = "org.hibernate.type.PostgresHstoreType")
    private Map<String,String> requestHeader;*/
    @Column(name = "request_header")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String requestHeader;;
    /*@Column(name = "request_param")
    @Type(type = "org.hibernate.type.PostgresHstoreType")
    private Map<String,String> requestParam;*/
    @Column(name = "request_param")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String requestParam;

    @Enumerated(EnumType.STRING)
    @Column(name="request_authentication_type")
    private AuthorizationType authenticationType;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name="request_bearer_token")
    private String requestBearerToken;

    @Enumerated(EnumType.STRING)
    @Column(name="request_body_type")
    private RequestBodyType requestBodyType;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name="request_body_raw")
    private String requestBodyRaw;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="group_id" , nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "groupId")
    @JsonIdentityReference(alwaysAsId = true)
    private Groups groups;

    @OneToMany(mappedBy = "requests", cascade = CascadeType.ALL)
    private List<ResponsesEntity> responsesEntities;
    @OneToMany(mappedBy = "requests", cascade = CascadeType.ALL)
    private List<Forms> forms;
    @OneToOne(mappedBy = "requests", cascade = CascadeType.ALL)
    private BasicAuthorization basicAuthorizations;

    @OneToOne(mappedBy = "requests", cascade = CascadeType.ALL)
    private ApiKeyAuthorization apiKeyAuthorizations;

    @OneToOne(mappedBy = "requests", cascade = CascadeType.ALL)
    private JwtBearer jwtBearers;

    public Requests(){}

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public void setAuthenticationType(AuthorizationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public void setRequestBearerToken(String requestBearerToken) {
        this.requestBearerToken = requestBearerToken;
    }

    public void setRequestBodyType(RequestBodyType requestBodyType) {
        this.requestBodyType = requestBodyType;
    }

    public void setRequestBodyRaw(String requestBodyRaw) {
        this.requestBodyRaw = requestBodyRaw;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public long getRequestId() {
        return requestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public AuthorizationType getAuthenticationType() {
        return authenticationType;
    }

    public String getRequestBearerToken() {
        return requestBearerToken;
    }

    public RequestBodyType getRequestBodyType() {
        return requestBodyType;
    }

    public String getRequestBodyRaw() {
        return requestBodyRaw;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Groups getGroups() {
        return groups;
    }

    public Requests(long requestId, String requestName, RequestMethod requestMethod, String url, String requestHeader, String requestParam, AuthorizationType authenticationType, String requestBearerToken, RequestBodyType requestBodyType, String requestBodyRaw, ZonedDateTime createdAt, ZonedDateTime updatedAt, Groups groups) {
        this.requestId = requestId;
        this.requestName = requestName;
        this.requestMethod = requestMethod;
        this.url = url;
        this.requestHeader = requestHeader;
        this.requestParam = requestParam;
        this.authenticationType = authenticationType;
        this.requestBearerToken = requestBearerToken;
        this.requestBodyType = requestBodyType;
        this.requestBodyRaw = requestBodyRaw;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.groups = groups;
    }
}
