package com.jsonbook.Json.Book.controller;

import com.jsonbook.Json.Book.entity.GroupEntity;
import com.jsonbook.Json.Book.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:46543", "http://localhost:35975"} )
@RestController
@RequestMapping("/group_list")
public class GroupController {
    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping
    public List<GroupEntity> findAllGroup(){
        return service.findAllGroup();
    }
    @PostMapping GroupEntity addGroup(@RequestBody GroupEntity groupEntity){
        return service.addGroup(groupEntity);
    }
    @PutMapping
    public GroupEntity updateGroup(@RequestBody GroupEntity groupEntity){
        return service.updateGroup(groupEntity);
    }
    @DeleteMapping("/delete/{group_id}")
    public void deleteGroup(@PathVariable("group_id") long id){
        service.deleteGroup(id);
    }
}
