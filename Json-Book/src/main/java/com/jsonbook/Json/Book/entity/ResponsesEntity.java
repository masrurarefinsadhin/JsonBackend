package com.jsonbook.Json.Book.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.Instant;

//defining entity
@Entity
// table name
@Table(name= "responses_table_2")
public class ResponsesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //responses_id => primary key
    @Column(name="responses_id")
    private Long responsesId;

    @Column(name="response_status")
    private String responseStatus;

    // Lob defines blob
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name= "response_body", nullable = true)
    private String responseBody;

    // requested at
    @Column(name = "requested_at", nullable = true)
    private Instant requestedAt;

    // responded at
    @Column(name="responded_at", nullable= true)
    private Instant respondedAt;

    @Column(name= "time_in_mils")
    private Integer timeInMils;

    // foreign key mapping: a response belongs to a request
    //
    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name="requestId", nullable= true)
    private RequestEntity requestEntity;
//    @JsonProperty("requestID") private void unpackNested(Integer requestID) {
//        this.requestEntity = new RequestEntity();
//        requestEntity.setId(requestID);
//    }

    // Constructor with no parameters
    public ResponsesEntity() {
    }

    // constructor with parameters
    public ResponsesEntity(long responsesId, String responseStatus, String responseBody, Instant requestedAt, Instant respondedAt, Integer timeInMils, RequestEntity requestEntity) {
        this.responsesId = responsesId;
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
        this.requestedAt = requestedAt;
        this.respondedAt = respondedAt;
        this.timeInMils = timeInMils;
        this.requestEntity = requestEntity;
    }


    public Long getResponsesId() {
        return responsesId;
    }

    public void setResponsesId(Long responsesId) {
        this.responsesId = responsesId;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Instant requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Instant getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(Instant respondedAt) {
        this.respondedAt = respondedAt;
    }

    public Integer getTimeInMils() {
        return timeInMils;
    }

    public void setTimeInMils(Integer timeInMils) {
        this.timeInMils = timeInMils;
    }

    public RequestEntity getRequestEntity() {
        return requestEntity;
    }

    public void setRequestEntity(RequestEntity requestEntity) {
        this.requestEntity = requestEntity;
    }
}
