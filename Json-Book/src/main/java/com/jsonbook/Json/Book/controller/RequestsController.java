package com.jsonbook.Json.Book.controller;

import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.service.RequestsService;
import com.jsonbook.Json.Book.service.RestTemplateService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:35549"} )
@RequestMapping("/requests")
public class RequestsController {
    private final RequestsService requestsService;
    private final RestTemplateService restTemplateService;

    public RequestsController(RequestsService requestsService, RestTemplateService restTemplateService) {
        this.requestsService = requestsService;
        this.restTemplateService = restTemplateService;
    }
    @GetMapping
    public List<Requests> findAllRequests(){
        return requestsService.findAllRequests();
    }
    @PostMapping
    public Requests saveRequests(@RequestBody Requests requests){
        Requests temp= requestsService.saveRequests(requests);
        System.out.println("here");
        System.out.println(requests.getRequestId());
        String s= restTemplateService.getResponse(requests.getRequestId());
        System.out.println(s);
        return temp;
    }
    public Requests updateRequests(@RequestBody Requests requests){
        return requestsService.updateRequests(requests);
    }
    @DeleteMapping("/delete/{requestId}")
    public void deleteRequests(@PathVariable("requestId") Long id){
        requestsService.deleteRequests(id);
    }
    @GetMapping("/group/{groupId}")
    public List<Requests> findRequestsById(@PathVariable("groupId") Long groupId){
        return requestsService.findRequestsById(groupId);
    }
    @GetMapping("/{requestId}")
    public Requests findRequests(@PathVariable("requestId") Long requestId){
        return requestsService.findRequests(requestId);
        //return restTemplateService.getResponse(requestId);
        //return "hello"+requestId.toString();
    }
    @GetMapping("create-response/{requestId}")
    public String createResponse(@PathVariable("requestId")Long requestId){
        return restTemplateService.getResponse(requestId);

    }

}
