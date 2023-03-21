package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.entity.ResponsesEntity;
import com.jsonbook.Json.Book.repository.RequestsRepository;
import com.jsonbook.Json.Book.repository.ResponsesRepository;
import com.jsonbook.Json.Book.service.RequestsService;
import com.jsonbook.Json.Book.service.ResponsesService;
import com.jsonbook.Json.Book.service.RestTemplateService;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final RequestsRepository requestsRepository;
    private final ResponsesService responsesService;
    private final RequestsService requestsService;
    public RestTemplateServiceImpl(RequestsRepository requestsRepository, ResponsesRepository responsesRepository, ResponsesService responsesService, RequestsService requestsService) {
        this.requestsRepository = requestsRepository;
        this.responsesService = responsesService;
        this.requestsService = requestsService;
    }


    @Override
    public String getResponse(long id) {
        try {
            Requests requests= requestsService.findRequests(id);
            long requestId = requests.getRequestId();
            RequestMethod requestMethod= requests.getRequestMethod();
            switch (requestMethod){
                case GET: return getMethod(requests);
                case POST: return "";
                case DELETE: return deleteMethod(requestId, requests);
                case PUT: return "";
            }
        }
        catch (Exception e){return e.toString();}
        return null;
    }
    private String getMethod(Requests requests){
        //String url= requests.getUrl();
        //String requestHeader= requests.getRequestHeader();
        //String requestParam= requests.getRequestParam();
        RestTemplate restTemplate=  new RestTemplate();
        //String url= "https://dummyjson.com/products";
        String url=requests.getUrl();
        //MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        HttpHeaders headers = new HttpHeaders();
        //System.out.println(headers);
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //System.out.println(headers);
        //headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //System.out.println(headers);
        //headers.setContentType(MediaType.APPLICATION_JSON);

        //String requestHeader="{\"Accept\":\"application/json,text/plain,*/*\",\"Accept-Encoding\":\"deflate\",\"Accept-Language\":\"en-US,en;q=0.9\",\"Connection\":\"keep-alive\"}";
        String requestHeader=requests.getRequestHeader();
        if(requestHeader != null){
            JSONObject jsonObjectHeader = new JSONObject(requestHeader);
            for(String key: jsonObjectHeader.keySet()){
                //System.out.println(key);
                //System.out.println(jsonObjectHeader.get(key));
                headers.set(key, (String) jsonObjectHeader.get(key));
                //System.out.println(headers);
            }
        }
        //headers.set("Host", "<calculated when request is sent>");
        //headers.set("Accept", "application/json");
        //headers.set("Accept-Encoding", "gzip");
        //System.out.println(headers);

        //System.out.println(entity);
        //String requestParam="{\"limit\":\"10\"}";
        //String requestParam="{\"limit\":\"10\", \"select\":\"key1,key2,key3\"}";
        String requestParam= requests.getRequestParam();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (requestParam!=null){
            JSONObject jsonObjectParam = new JSONObject(requestParam);
            for(String key: jsonObjectParam.keySet()){
                System.out.println(key);
                System.out.println(jsonObjectParam.get(key));
                params.set(key, (String) jsonObjectParam.get(key));
            }
            System.out.println(params);
        }
        //MultiValueMap<String, String> myParams = new LinkedMultiValueMap<String, String>();
        //myParams.add("status", "inprogress");
        //myParams.add("status", "completed");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParams(params);
        URI uri = URI.create(builder.toUriString());
        System.out.println(uri);

        //ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        HttpEntity<String> entity = new HttpEntity<>( headers);
        Instant requestedAt= Instant.now();
        ResponseEntity<String> s=restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        Instant respondedAt = Instant.now();
        System.out.println(requestedAt);
        System.out.println(respondedAt);
        long timeInMils = Duration.between(requestedAt, respondedAt).toMillis();
        System.out.println(timeInMils);
        System.out.println(s.getBody());
        System.out.println(s.getHeaders());
        System.out.println(s.getClass());
        String responseStatus= s.getStatusCode().toString();
        System.out.println(responseStatus);
        System.out.println(s.getStatusCode());
        String responseBody=s.getBody();
        responsesService.saveResponses(new ResponsesEntity( null,responseStatus, responseBody,requestedAt,respondedAt,timeInMils,requests));
        return s.getBody();
    }

    private String deleteMethod(@PathVariable("requestId") long requestId, Requests requests){
        System.out.println("here inn delete");
        RestTemplate restTemplate=  new RestTemplate();
//        String url_r= "http://localhost:8080/requests/";
        String url_r = requests.getUrl();
//        System.out.println(url_r);
        requestId = requests.getRequestId();
//        System.out.println(requestId);
        //define header
        HttpHeaders headers = new HttpHeaders();
        // getting request header
        String requestHeader=requests.getRequestHeader();
        if(requestHeader != null){
            JSONObject jsonObjectHeader = new JSONObject(requestHeader);
            for(String key: jsonObjectHeader.keySet()){
                headers.set(key, (String) jsonObjectHeader.get(key));
            }
        }

        String requestParam= requests.getRequestParam();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (requestParam!=null){
            JSONObject jsonObjectParam = new JSONObject(requestParam);
            for(String key: jsonObjectParam.keySet()){
                System.out.println(key);
                System.out.println(jsonObjectParam.get(key));
                params.set(key, (String) jsonObjectParam.get(key));
            }
            System.out.println(params);
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url_r).queryParams(params);
        URI uri = URI.create(builder.toUriString());
        // perform delete

        HttpEntity<String> entity = new HttpEntity<>( headers);
        Instant requestedAt= Instant.now();
        ResponseEntity<String> s=restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
//        return restTemplate.exchange(url_r + requestId, HttpMethod.DELETE, entity, String.class).getBody();
        Instant respondedAt = Instant.now();
        long timeInMils = Duration.between(requestedAt, respondedAt).toMillis();
        System.out.println(s.getStatusCode());
        String responseStatus= s.getStatusCode().toString();
        String responseBody=s.getBody();
        responsesService.saveResponses(new ResponsesEntity( null,responseStatus, responseBody,requestedAt,respondedAt,timeInMils,requests));
        return s.getBody();

    }
}
