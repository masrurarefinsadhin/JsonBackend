package com.jsonbook.Json.Book.entity;

import javax.persistence.*;
import java.util.List;

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

    public Groups(){
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Groups(long groupId, String groupName){
        this.groupId=groupId;
        this.groupName=groupName;
    }

    public long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
