package com.jsonbook.Json.Book.entity;

public class RequestFormDto {
    private Requests requests;
    private String forms;

    public RequestFormDto(){}
    public RequestFormDto(Requests requests, String forms) {
        this.requests = requests;
        this.forms = forms;
    }

    public void setRequests(Requests requests) {
        this.requests = requests;
    }

    public void setForms(String forms) {
        this.forms = forms;
    }

    public Requests getRequests() {
        return requests;
    }

    public String getForms() {
        return forms;
    }
}
