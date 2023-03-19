package com.jsonbook.Json.Book.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Type;


@Entity
@Table(name="group_list")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private long groupId;
    @Column(name="group_name")
    private String groupName;

    // a group has many requests
    // mappedBy = groupEntity as it is mapped with RequestEntity class
    @OneToMany(mappedBy = "groupEntity", cascade = CascadeType.ALL)
    private List<RequestEntity> requestEntities;



    public GroupEntity(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }


    public GroupEntity(){
    }
    public long getGroupId() {
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
