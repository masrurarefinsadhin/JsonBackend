package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.BasicAuthorization;
import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.repository.BasicAuthorizationRepository;
import com.jsonbook.Json.Book.service.BasicAuthorizationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BasicAuthorizationServiceImpl implements BasicAuthorizationService {
    private final BasicAuthorizationRepository basicAuthorizationRepository;

    public BasicAuthorizationServiceImpl(BasicAuthorizationRepository basicAuthorizationRepository) {
        this.basicAuthorizationRepository = basicAuthorizationRepository;
    }

    @Override
    public BasicAuthorization findBasicAuthorization(Requests requests) {
        return basicAuthorizationRepository.findBasicAuthorizationByRequests(requests);
    }

    @Override
    public BasicAuthorization addBasicAuthorization(BasicAuthorization basicAuthorization) {
        return basicAuthorizationRepository.save(basicAuthorization);
    }

    @Override
    public void deleteBasicAuthorization(long id) {
        basicAuthorizationRepository.deleteById(id);
    }

    @Override
    public BasicAuthorization updateBasicAuthorization(BasicAuthorization basicAuthorization) {
        return basicAuthorizationRepository.save(basicAuthorization);
    }
}
