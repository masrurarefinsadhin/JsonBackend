package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.ApiKeyAuthorization;
import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.repository.ApiKeyAuthorizationRepository;
import com.jsonbook.Json.Book.service.ApiKeyAuthorizationService;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyAuthorizationServiceImpl implements ApiKeyAuthorizationService {
    private final ApiKeyAuthorizationRepository apiKeyAuthorizationRepository;

    public ApiKeyAuthorizationServiceImpl(ApiKeyAuthorizationRepository apiKeyAuthorizationRepository) {
        this.apiKeyAuthorizationRepository = apiKeyAuthorizationRepository;
    }

    @Override
    public ApiKeyAuthorization findApiKeyAuthorization(Requests requests) {
        return apiKeyAuthorizationRepository.findApiKeyAuthorizationByRequests(requests);
    }

    @Override
    public ApiKeyAuthorization addApiKeyAuthorization(ApiKeyAuthorization apiKeyAuthorization) {
        return apiKeyAuthorizationRepository.save(apiKeyAuthorization);
    }

    @Override
    public void deleteApiKeyAuthorization(long id) {
        apiKeyAuthorizationRepository.deleteById(id);
    }

    @Override
    public ApiKeyAuthorization updateApiKeyAuthorization(ApiKeyAuthorization apiKeyAuthorization) {
        return apiKeyAuthorizationRepository.save(apiKeyAuthorization);
    }
}
