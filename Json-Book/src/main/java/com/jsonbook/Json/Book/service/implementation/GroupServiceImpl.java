package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.GroupEntity;
import com.jsonbook.Json.Book.repository.GroupRepository;
import com.jsonbook.Json.Book.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;

    public GroupServiceImpl(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GroupEntity> findAllGroup() {
        return repository.findAll();
    }
    @Override
    public GroupEntity addGroup(GroupEntity groupEntity){
        return repository.save(groupEntity);
    }

    @Override
    public void deleteGroup(long id) {
        repository.deleteById(id);
    }

    @Override
    public GroupEntity updateGroup(GroupEntity groupEntity) {
        return repository.save(groupEntity);
    }
}
