package com.jsonbook.Json.Book.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jsonbook.Json.Book.FormType;
import com.jsonbook.Json.Book.RequestBodyType;

import javax.persistence.*;

@Entity
@Table(name="forms")
public class Forms {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "form_id")
    private Long formId;

    @Column(name="form_key",nullable = false)
    private String formKey;

    @Column(name="form_value",nullable = false)
    private String formValue;

    @Enumerated(EnumType.STRING)
    @Column(name="form_type")
    private RequestBodyType requestBodyType;

    @ManyToOne
    @JoinColumn(name="request_id",nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "requestId")
    @JsonIdentityReference(alwaysAsId = true)
    private Requests requests;

    public Forms(){
    }


    public Forms(Long formId, String formKey, String formValue, RequestBodyType requestBodyType, Requests requests) {
        this.formId = formId;
        this.formKey = formKey;
        this.formValue = formValue;
        this.requestBodyType = requestBodyType;
        this.requests = requests;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public void setFormValue(String formValue) {
        this.formValue = formValue;
    }

    public void setRequestBodyType(RequestBodyType requestBodyType) {
        this.requestBodyType = requestBodyType;
    }

    public void setRequests(Requests requests) {
        this.requests = requests;
    }

    public Long getFormId() {
        return formId;
    }

    public String getFormKey() {
        return formKey;
    }

    public String getFormValue() {
        return formValue;
    }

    public RequestBodyType getRequestBodyType() {
        return requestBodyType;
    }

    @Override
    public String toString() {
        return "Forms{" +
                "formId=" + formId +
                ", formKey='" + formKey + '\'' +
                ", formValue='" + formValue + '\'' +
                ", requestBodyType=" + requestBodyType +
                ", requests=" + requests +
                '}';
    }

    public Requests getRequests() {
        return requests;
    }
}
