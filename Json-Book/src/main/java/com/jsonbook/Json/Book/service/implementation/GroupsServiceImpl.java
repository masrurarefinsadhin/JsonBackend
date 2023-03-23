package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.Groups;
import com.jsonbook.Json.Book.repository.GroupsRepository;
import com.jsonbook.Json.Book.service.GroupsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsServiceImpl implements GroupsService {
    private final GroupsRepository groupsRepository;

    public GroupsServiceImpl(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }


    @Override
    public List<Groups> findAllGroups() {
        return groupsRepository.findAll();
    }
    @Override
    public Groups addGroups(Groups groups){
        return groupsRepository.save(groups);
    }

    @Override
    public void deleteGroups(long id) {
        groupsRepository.deleteById(id);
    }

    @Override
    public Groups updateGroups(Groups groups) {
        return groupsRepository.save(groups);
    }

    public void deleteGroupRefrence(long id){
        groupsRepository.deleteGroupRefrence(id);
    }
}

