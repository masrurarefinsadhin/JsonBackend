package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.entity.GroupEntity;

import java.util.List;

public interface GroupService {
    List<GroupEntity> findAllGroup();
    GroupEntity addGroup(GroupEntity groupEntity);
    void deleteGroup(int id);

    GroupEntity updateGroup(GroupEntity groupEntity);

}
