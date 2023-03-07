package com.jsonbook.Json.Book.entity;


import javax.persistence.*;

@Entity
@Table(name = "request")

public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="requestId")
    private long id;
    @Column(name = "requestName")
    private String name;

    @Column(name = "requestURL")
    private String url;

    @Column(name = "requestHeader")
    private String header;
    @Column(name = "requestBody")
    private String reqBody;
    @Column(name = "resposeBody")
    private String resBody;

    public RequestEntity(){

    }


    public RequestEntity(long id, String name, String url, String header, String reqBody, String resBody) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.header = header;
        this.reqBody = reqBody;
        this.resBody = resBody;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    public String getResBody() {
        return resBody;
    }

    public void setResBody(String resBody) {
        this.resBody = resBody;
    }
}
