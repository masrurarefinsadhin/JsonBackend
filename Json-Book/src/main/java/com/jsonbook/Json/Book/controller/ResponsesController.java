package com.jsonbook.Json.Book.controller;

import com.jsonbook.Json.Book.entity.GroupEntity;
import com.jsonbook.Json.Book.entity.RequestEntity;
import com.jsonbook.Json.Book.service.ResponsesService;
import com.jsonbook.Json.Book.entity.ResponsesEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// connecting with frontend
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:46543", "http://localhost:35975"} )
@RestController
// defining path
@RequestMapping("/responses")
public class ResponsesController {
    private final ResponsesService responsesService;

    public ResponsesController(ResponsesService responsesService){this.responsesService= responsesService;}

    @GetMapping
    public List<ResponsesEntity> findAllResponses(){
        return responsesService.findAllResponses();
    }

    @PostMapping
    public ResponsesEntity saveResponses(@RequestBody ResponsesEntity responsesEntity){
        return responsesService.saveResponses(responsesEntity);
    }

    public ResponsesEntity updateResponses(@RequestBody ResponsesEntity responsesEntity){
        return responsesService.updateResponses(responsesEntity);
    }

    @GetMapping("/{responsesId}")
    public List<ResponsesEntity> findResponsesById(@PathVariable("responsesId") Long responsesId){
        return responsesService.findResponsesById(responsesId);
    }
}
