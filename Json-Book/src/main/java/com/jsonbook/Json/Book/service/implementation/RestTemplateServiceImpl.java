package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.AuthorizationType;
import com.jsonbook.Json.Book.HeaderParamType;
import com.jsonbook.Json.Book.RequestBodyType;
import com.jsonbook.Json.Book.entity.*;
import com.jsonbook.Json.Book.repository.RequestsRepository;
import com.jsonbook.Json.Book.repository.ResponsesRepository;
import com.jsonbook.Json.Book.service.*;
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
    private final BasicAuthorizationService basicAuthorizationService;
    private final ApiKeyAuthorizationService apiKeyAuthorizationService;
    public RestTemplateServiceImpl(RequestsRepository requestsRepository, ResponsesRepository responsesRepository, ResponsesService responsesService, RequestsService requestsService, FormsService formsService,BasicAuthorizationService basicAuthorizationService,ApiKeyAuthorizationService apiKeyAuthorizationService) {
        this.requestsRepository = requestsRepository;
        this.responsesService = responsesService;
        this.requestsService = requestsService;
        this.formsService = formsService;
        this.basicAuthorizationService = basicAuthorizationService;
        this.apiKeyAuthorizationService = apiKeyAuthorizationService;
    }


    @Override
    public String getResponse(long id) {
        try {
            Requests requests= requestsService.findRequests(id);
            RequestMethod requestMethod= requests.getRequestMethod();
            AuthorizationType authorizationType= requests.getAuthenticationType();
            HttpHeaders headers= setHeaders(requests.getRequestHeader());
            URI uri = setParameters(requests.getRequestParam(),requests.getUrl());
            if (authorizationType != AuthorizationType.NO_AUTH) {
                //headers=setAuthorization(requests,headers,authorizationType);
                switch (authorizationType){
                    case BASIC_AUTH:
                        BasicAuthorization basicAuthorization= basicAuthorizationService.findBasicAuthorization(requests);
                        headers.setBasicAuth(basicAuthorization.getBasicUsername(),basicAuthorization.getBasicPassword());
                    case BEARER_TOKEN:
                        String token= requests.getRequestBearerToken();
                        if(token!= null ){headers.setBearerAuth(token);}
                    case API_KEY:
                        ApiKeyAuthorization apiKeyAuthorization= apiKeyAuthorizationService.findApiKeyAuthorization(requests);
                        HeaderParamType headerParamType= apiKeyAuthorization.getHeaderParamType();
                        switch (headerParamType){
                            case HEADER:
                                headers.set(apiKeyAuthorization.getApiKey(),apiKeyAuthorization.getApiValue());
                            case PARAM:
                                UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri)
                                        .queryParam(apiKeyAuthorization.getApiKey(), apiKeyAuthorization.getApiValue());
                                uri=URI.create(builder.toUriString());
                        }
                }
            }

            switch (requestMethod){
                case GET: return returnResponse(uri,HttpMethod.GET,new HttpEntity<String>( headers),requests);
                case POST: return postMethod(requests,headers,uri,HttpMethod.POST);
                case PUT: return postMethod(requests,headers,uri,HttpMethod.PUT);
                case DELETE: return returnResponse(uri,HttpMethod.DELETE,new HttpEntity<String>(headers),requests);
            }
        }
        catch (Exception e){return e.toString();}
        return null;
    }

    private HttpHeaders setAuthorization(Requests requests, HttpHeaders headers, AuthorizationType authorizationType) {
        switch (authorizationType){
            case BASIC_AUTH:
                BasicAuthorization basicAuthorization= basicAuthorizationService.findBasicAuthorization(requests);
                headers.setBasicAuth(basicAuthorization.getBasicUsername(),basicAuthorization.getBasicPassword());
            case BEARER_TOKEN:
                String token= requests.getRequestBearerToken();
                if(token!= null ){headers.setBearerAuth(token);}
            case API_KEY:
                ApiKeyAuthorization apiKeyAuthorization= apiKeyAuthorizationService.findApiKeyAuthorization(requests);
                headers.set(apiKeyAuthorization.getApiKey(),apiKeyAuthorization.getApiValue());
        }
        return headers;
    }

    private String getMethod(Requests requests, HttpHeaders headers, URI uri){
        HttpEntity<String> entity = new HttpEntity<>( headers);
        return returnResponse(uri,HttpMethod.GET,entity,requests);
    }

    private String postMethod(Requests requests, HttpHeaders headers,URI uri,HttpMethod httpMethod){
        RequestBodyType requestBodyType=requests.getRequestBodyType();
        String requestBodyRaw=requests.getRequestBodyRaw();
        Object entity=null;
        if (requestBodyType == RequestBodyType.RAW && requestBodyRaw!=null) {
            entity = new HttpEntity<>(requestBodyRaw, headers);
        }
        else if (requestBodyType==RequestBodyType.FORM_ENCODED || requestBodyType==RequestBodyType.FORM_DATA) {
            if (requestBodyType==RequestBodyType.FORM_ENCODED){headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);}
            List<Forms> forms= formsService.findFormsByRequestId(requests.getRequestId(),requestBodyType);
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            for (Forms obj : forms) {formData.add(obj.getFormKey(),obj.getFormValue());}
            entity = new HttpEntity<>(formData, headers);
        }
        return returnResponse(uri,httpMethod,entity,requests);
    }

    private HttpHeaders setHeaders(String requestHeader){
        HttpHeaders headers=  HttpHeaders.readOnlyHttpHeaders((HttpHeaders) setJsonObject(requestHeader));
        System.out.println(headers);
        return headers;
    }

    private URI setParameters(String requestParam, String url){
        MultiValueMap<String, String> params = setJsonObject(requestParam);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParams(params);
        return URI.create(builder.toUriString());
    }

    private HttpHeaders setJsonObject(String value){
        HttpHeaders list = new HttpHeaders();
        if (value!=null){
            JSONObject jsonObjectParam = new JSONObject(value);
            for(String key: jsonObjectParam.keySet()){
                list.add(key,(String) jsonObjectParam.get(key));
            }
        }
        return list;
    }

    private String deleteMethod( Requests requests, HttpHeaders headers,URI uri){
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return returnResponse(uri,HttpMethod.DELETE,entity,requests);


    }

    private String returnResponse(URI uri , HttpMethod httpMethod, Object entity ,Requests requests){
        RestTemplate restTemplate=  new RestTemplate();
        Instant requestedAt= Instant.now();
        ResponseEntity<String> response=restTemplate.exchange(uri, httpMethod, (HttpEntity<?>)entity, String.class);
        Instant respondedAt = Instant.now();
        long timeInMils = Duration.between(requestedAt, respondedAt).toMillis();
        String responseStatus= response.getStatusCode().toString();
        String responseBody=response.getBody();
        responsesService.saveResponses(new ResponsesEntity( null,responseStatus, responseBody,requestedAt,respondedAt,timeInMils,requests));
        return responseBody;
    }

}
