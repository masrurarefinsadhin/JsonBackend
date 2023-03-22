package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.entity.BasicAuthorization;
import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.repository.BasicAuthorizationRepository;

import java.util.Optional;

public interface BasicAuthorizationService {
    BasicAuthorization findBasicAuthorization(Requests requests);
    BasicAuthorization addBasicAuthorization(BasicAuthorization basicAuthorization);
    void deleteBasicAuthorization(long id);
    BasicAuthorization updateBasicAuthorization(BasicAuthorization basicAuthorization);
}
