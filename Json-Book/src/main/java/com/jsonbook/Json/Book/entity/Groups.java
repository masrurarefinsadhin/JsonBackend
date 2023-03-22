package com.jsonbook.Json.Book.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "group_id")
    private long groupId;

    @Column(name="group_name",nullable = false)
    private String groupName;

    @OneToMany(mappedBy = "groups",cascade = CascadeType.ALL)
    private List<Requests> requests;

    @ManyToMany(mappedBy = "groupAccess",fetch = FetchType.LAZY)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    List<User> userAccess= new ArrayList<>();

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setRequests(List<Requests> requests) {
        this.requests = requests;
    }

    public void setUserAccess(List<User> userAccess) {
        this.userAccess = userAccess;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Requests> getRequests() {
        return requests;
    }

    public List<User> getUserAccess() {
        return userAccess;
    }

    public Groups(){
    }

    public Groups(long groupId, String groupName, List<Requests> requests, List<User> userAccess) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.requests = requests;
        this.userAccess = userAccess;
    }
}
