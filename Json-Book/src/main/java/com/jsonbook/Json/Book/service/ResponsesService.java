package com.jsonbook.Json.Book.service;
import com.jsonbook.Json.Book.entity.ResponsesEntity;

import java.util.List;

public interface ResponsesService {
    //get all responses
    List<ResponsesEntity> findAllResponses();
    // save responses
    ResponsesEntity saveResponses(ResponsesEntity responsesEntity);
    // update responses
    ResponsesEntity updateResponses(ResponsesEntity responsesEntity);

    // get by ID
    List<ResponsesEntity> findResponsesById(long id);


}
