package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.FormType;
import com.jsonbook.Json.Book.RequestBodyType;
import com.jsonbook.Json.Book.entity.Forms;
import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.entity.ResponsesEntity;
import com.jsonbook.Json.Book.repository.RequestsRepository;
import com.jsonbook.Json.Book.repository.ResponsesRepository;
import com.jsonbook.Json.Book.service.FormsService;
import com.jsonbook.Json.Book.service.RequestsService;
import com.jsonbook.Json.Book.service.ResponsesService;
import com.jsonbook.Json.Book.service.RestTemplateService;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final RequestsRepository requestsRepository;
    private final ResponsesService responsesService;
    private final RequestsService requestsService;
    private  final FormsService formsService;
    public RestTemplateServiceImpl(RequestsRepository requestsRepository, ResponsesRepository responsesRepository, ResponsesService responsesService, RequestsService requestsService, FormsService formsService) {
        this.requestsRepository = requestsRepository;
        this.responsesService = responsesService;
        this.requestsService = requestsService;
        this.formsService = formsService;
    }


    @Override
    public String getResponse(long id) {
        try {
            Requests requests= requestsService.findRequests(id);
            RequestMethod requestMethod= requests.getRequestMethod();
            switch (requestMethod){
                case GET: return getMethod(requests);
                case POST:
                case PUT:
                    return postMethod(requests);
                case DELETE: return deleteMethod(requests);
            }
        }
        catch (Exception e){return e.toString();}
        return null;
    }
    private String getMethod(Requests requests){
        RestTemplate restTemplate=  new RestTemplate();
        URI uri= setParameters(requests.getRequestParam(),requests.getUrl());
        HttpHeaders headers= setHeaders(requests.getRequestHeader());
        HttpEntity<String> entity = new HttpEntity<>( headers);
        Instant requestedAt= Instant.now();
        ResponseEntity<String> s=restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        Instant respondedAt = Instant.now();
        long timeInMils = Duration.between(requestedAt, respondedAt).toMillis();

        String responseStatus= s.getStatusCode().toString();
        String responseBody=s.getBody();
        responsesService.saveResponses(new ResponsesEntity( null,responseStatus, responseBody,requestedAt,respondedAt,timeInMils,requests));
        return responseBody+responseStatus;
    }
    private RestTemplate getAuthentication(RestTemplate restTemplate, Requests requests){
        /*String authenticationType= requests.getAuthenticationType();
        switch (requests.getAuthenticationType()){
            case NO_AUTH:return restTemplate;
        }*/
        return restTemplate;
    }
    private String postMethod(Requests requests){
        RestTemplate restTemplate=  new RestTemplate();
        URI uri= setParameters(requests.getRequestParam(),requests.getUrl());
        HttpHeaders headers= setHeaders(requests.getRequestHeader());

        Instant requestedAt=null;
        Instant respondedAt=null;
        ResponseEntity<String> responseSet=null;

        RequestBodyType requestBodyType=requests.getRequestBodyType();
        if (requestBodyType == RequestBodyType.RAW) {
            String requestBodyRaw=requests.getRequestBodyRaw();
            System.out.println("raw type"+requestBodyRaw);
            if(requestBodyRaw !=null){
                //requestBodyRaw="{\"title\":\"BMW Pencil\"}";
                HttpEntity<String> entity = new HttpEntity<>(requestBodyRaw, headers);
                requestedAt= Instant.now();
                responseSet=restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
                respondedAt = Instant.now();
            }
        }
        else if (requestBodyType==RequestBodyType.FORM_ENCODED || requestBodyType==RequestBodyType.FORM_DATA) {
            if (requestBodyType==RequestBodyType.FORM_ENCODED){headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);}
            List<Forms> forms= formsService.findFormsByRequestId(requests.getRequestId(),requestBodyType);
            for (Forms obj : forms) {System.out.println(obj.getFormKey());}
            MultiValueMap<String, String> f = new LinkedMultiValueMap<>();
            for (Forms obj : forms) {f.add(obj.getFormKey(),obj.getFormValue());}
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(f, headers);
            requestedAt= Instant.now();
            responseSet=restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            respondedAt = Instant.now();
        }

        long timeInMils = Duration.between(requestedAt, respondedAt).toMillis();
        String responseStatus= responseSet.getStatusCode().toString();
        String responseBody=responseSet.getBody();
        responsesService.saveResponses(new ResponsesEntity( null,responseStatus, responseBody,requestedAt,respondedAt,timeInMils,requests));
        return responseBody+responseStatus;
    }

    private HttpHeaders setHeaders(String requestHeader){
        /*HttpHeaders headers = new HttpHeaders();
        if(requestHeader != null){
            JSONObject jsonObjectHeader = new JSONObject(requestHeader);
            for(String key: jsonObjectHeader.keySet()){
                headers.set(key, (String) jsonObjectHeader.get(key));
            }
        }
        return headers;*/
        HttpHeaders headers=  HttpHeaders.readOnlyHttpHeaders(setJsonObject(requestHeader));
        System.out.println(headers);
        return headers;
    }
    private URI setParameters(String requestParam, String url){
        /*MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        if (requestParam!=null){
            JSONObject jsonObjectParam = new JSONObject(requestParam);
            for(String key: jsonObjectParam.keySet()){
                params.set(key, (String) jsonObjectParam.get(key));
            }
        }*/
        MultiValueMap<String, String> params = setJsonObject(requestParam);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParams(params);
        URI uri = URI.create(builder.toUriString());
        return uri;
    }
    private MultiValueMap< String, String> setJsonObject(String value){
        MultiValueMap<String, String> list = new LinkedMultiValueMap<String, String>();
        if (value!=null){
            JSONObject jsonObjectParam = new JSONObject(value);
            for(String key: jsonObjectParam.keySet()){
                list.set(key, (String) jsonObjectParam.get(key));
            }
        }
        return list;
    }
    private String deleteMethod( Requests requests){
        RestTemplate restTemplate=  new RestTemplate();
        String url_r = requests.getUrl();
        HttpHeaders headers = setHeaders(requests.getRequestHeader());
        String requestParam= requests.getRequestParam();
        URI uri= setParameters(requests.getRequestParam(),requests.getUrl());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Instant requestedAt= Instant.now();
        ResponseEntity<String> s=restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
        Instant respondedAt = Instant.now();
        long timeInMils = Duration.between(requestedAt, respondedAt).toMillis();
        String responseStatus= s.getStatusCode().toString();
        String responseBody=s.getBody();
        responsesService.saveResponses(new ResponsesEntity( null,responseStatus, responseBody,requestedAt,respondedAt,timeInMils,requests));
        return responseBody+responseStatus;

    }

}
