package com.jsonbook.Json.Book.entity;

import javax.persistence.*;

@Entity
@Table(name="group_list")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private int groupId;
    @Column(name="group_name")
    private String groupName;

    public GroupEntity(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
    public GroupEntity(){
    }
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


}
