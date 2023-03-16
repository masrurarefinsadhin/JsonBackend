package com.jsonbook.Json.Book.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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

   @OneToMany(mappedBy = "groupEntity", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<RequestEntity> requests;

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
