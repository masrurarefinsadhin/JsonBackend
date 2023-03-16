package com.jsonbook.Json.Book.controller;

import com.jsonbook.Json.Book.entity.Groups;
import com.jsonbook.Json.Book.service.GroupsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:35549"} )
@RestController
@RequestMapping("/groups")
public class GroupsController {
    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }


    @GetMapping
    public List<Groups> findAllGroups() {
        return groupsService.findAllGroups();
    }

    @PostMapping
    Groups addGroups(@RequestBody Groups groups) {
        return groupsService.addGroups(groups);
    }

    @PutMapping
    public Groups updateGroups(@RequestBody Groups groups) {
        return groupsService.updateGroups(groups);
    }

    @DeleteMapping("/delete/{group_id}")
    public void deleteGroups(@PathVariable("group_id") long id) {
        groupsService.deleteGroups(id);
    }
}
