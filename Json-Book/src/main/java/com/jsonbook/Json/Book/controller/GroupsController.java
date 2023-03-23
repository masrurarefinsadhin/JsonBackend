package com.jsonbook.Json.Book.controller;

import com.jsonbook.Json.Book.entity.Groups;
import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.entity.User;
import com.jsonbook.Json.Book.exception.ResourceNotFoundException;
import com.jsonbook.Json.Book.repository.GroupsRepository;
import com.jsonbook.Json.Book.repository.RequestsRepository;
import com.jsonbook.Json.Book.repository.UserRepository;
import com.jsonbook.Json.Book.service.GroupsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:35549"})
@RestController
@RequestMapping("/groups")
public class GroupsController {
    private final GroupsService groupsService;
    private final RequestsRepository requestsRepository;
    private final UserRepository userRepository;
    private final GroupsRepository groupsRepository;

    public GroupsController(GroupsService groupsService, RequestsRepository requestsRepository, UserRepository userRepository, GroupsRepository groupsRepository) {
        this.groupsService = groupsService;
        this.requestsRepository = requestsRepository;
        this.userRepository = userRepository;
        this.groupsRepository = groupsRepository;
    }


    @GetMapping
    public List<Groups> findAllGroups() {
        /*return groupsService.findAllGroups().stream()
                .map(group -> new Groups(group.getGroupId(), group.getGroupName(),null,null))
                .collect(Collectors.toList());*/
        return groupsService.findAllGroups();
    }

    @GetMapping("/{id}")
    public List<Groups> findAllGroupsByUser(@PathVariable("id") long id) {
        User user= userRepository.findById(id).get();
        /*return userRepository.findById(id).get().getGroupAccess()
                .stream()
                .map(group -> new Groups(group.getGroupId(), group.getGroupName(), null, null))
                .collect(Collectors.toList());*/
        return userRepository.findById(id).get().getGroupAccess();
    }
    @GetMapping("/requests/{id}")
    public List<Requests> findAllRequestByUser(@PathVariable("id")long id){
        User user= userRepository.findById(id).get();
        return userRepository.findById(id).get().getGroupAccess()
                .stream().flatMap(group->group.getRequests().stream()).collect(Collectors.toList());
    }

    @PostMapping("/{id}")
    Groups addGroups(@RequestBody Groups groups, @PathVariable("id") long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found with id " + id));
        Groups groups1 = groupsService.addGroups(groups);
        /*List<Groups> groupSet= user.getGroupAccess().stream().map(group -> new Groups(group.getGroupId(), group.getGroupName(),null,null))
                .collect(Collectors.toList());*/
        List<Groups> groupSet = user.getGroupAccess();
        groupSet.add(groups1);
        user.setGroupAccess(groupSet);
        userRepository.save(user);
        return groups1;
    }

    @PutMapping
    public Groups updateGroups(@RequestBody Groups groups) {
        return groupsService.updateGroups(groups);
    }

    @DeleteMapping("/delete/{group_id}")
    public void deleteGroups(@PathVariable("group_id") long id) {
        groupsService.deleteGroupRefrence(id);
        groupsService.deleteGroups(id);
    }

   /* @GetMapping("/requests/sort/{id}")
    public List<Requests> findAllGroupsByUserTest(@PathVariable("id") long id) {
        List<Requests> requests=findAllRequestByUser(id);
        System.out.println(requests);
        Collections.sort(requests);
        System.out.println(requests);
        return requests;
    }*/
}
