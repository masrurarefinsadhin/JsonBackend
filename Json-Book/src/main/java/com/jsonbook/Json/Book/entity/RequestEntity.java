package com.jsonbook.Json.Book.entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@Entity
@Table(name = "request")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="requestId")
    private long id;
    @Column(name = "requestName")
    private String name;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "requestURL")
    private String url;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "requestHeader")
    private String header;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "requestBody")
    private String reqBody;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "resposeBody")
    private String resBody;

//    A request belongs to a Group
    @ManyToOne
    @JoinColumn(name="group_id", nullable= true)
    private GroupEntity groupEntity;

    // Again, A request have many responses
    @OneToMany(mappedBy = "requestEntity", cascade = CascadeType.ALL)
    private List<ResponsesEntity> responsesEntities;


    public RequestEntity(){
    }

    public RequestEntity(long id, String name, String url, String header, String reqBody, String resBody, GroupEntity groupEntity) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.header = header;
        this.reqBody = reqBody;
        this.resBody = resBody;
        this.groupEntity = groupEntity;

    }

    public long getId() {
        return id;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
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
