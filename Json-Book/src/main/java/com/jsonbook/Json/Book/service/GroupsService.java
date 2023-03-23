package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.entity.Groups;

import java.util.List;


public interface GroupsService {
    List<Groups> findAllGroups();
    Groups addGroups(Groups groups);
    void deleteGroups(long id);
    Groups updateGroups(Groups groups);
    void deleteGroupRefrence(long id);

}

