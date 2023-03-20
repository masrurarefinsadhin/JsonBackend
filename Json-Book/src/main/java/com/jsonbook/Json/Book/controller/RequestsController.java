package com.jsonbook.Json.Book.controller;

import com.jsonbook.Json.Book.entity.Forms;
import com.jsonbook.Json.Book.entity.RequestFormDto;
import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.service.FormsService;
import com.jsonbook.Json.Book.service.RequestsService;
import com.jsonbook.Json.Book.service.RestTemplateService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:35549"} )
@RequestMapping("/requests")
public class RequestsController {
    private final RequestsService requestsService;
    private final RestTemplateService restTemplateService;
    private final FormsService formsService;

    public RequestsController(RequestsService requestsService, RestTemplateService restTemplateService, FormsService formsService) {
        this.requestsService = requestsService;
        this.restTemplateService = restTemplateService;
        this.formsService = formsService;
    }
    @GetMapping
    public List<Requests> findAllRequests(){
        return requestsService.findAllRequests();
    }
    @PostMapping("/dto")
    public RequestFormDto saveRequestsForms(@RequestBody RequestFormDto requestFormDto){
        RequestFormDto  res = requestsService.saveRequestsForms(requestFormDto);
        String s= createResponse(requestFormDto.getRequests().getRequestId());
        System.out.println(s);
        return res;
    }
    @PostMapping
    public Requests saveRequests(@RequestBody Requests requests){
        return requestsService.saveRequests(requests);
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
