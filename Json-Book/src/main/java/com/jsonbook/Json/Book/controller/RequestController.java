package com.jsonbook.Json.Book.controller;

import com.jsonbook.Json.Book.entity.GroupEntity;
import com.jsonbook.Json.Book.entity.RequestEntity;
import com.jsonbook.Json.Book.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:46543", "http://localhost:35975"} )
@RestController
@RequestMapping("/request")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public List<RequestEntity> findAllRequest(){
        return requestService.findAllRequest();
    }

    @PostMapping
    public RequestEntity saveRequest(@RequestBody RequestEntity requestEntity){
        return requestService.saveRequest(requestEntity);
    }

    public RequestEntity updateRequest(@RequestBody RequestEntity requestEntity){
        return requestService.updateRequest(requestEntity);
    }

    @DeleteMapping("/delete/{requestId}")
    public void deleteRequest(@PathVariable("requestId") Long id){
        requestService.deleteRequest(id);
    }

    @GetMapping("/{groupId}")
    public List<RequestEntity> findRequestById(@PathVariable("groupId") Long groupId){
        return requestService.findRequestById(groupId);
    }

}
