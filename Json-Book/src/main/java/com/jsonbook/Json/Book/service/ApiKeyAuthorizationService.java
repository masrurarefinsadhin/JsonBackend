package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.entity.ApiKeyAuthorization;
import com.jsonbook.Json.Book.entity.BasicAuthorization;
import com.jsonbook.Json.Book.entity.Requests;

public interface ApiKeyAuthorizationService {
    ApiKeyAuthorization findApiKeyAuthorization(Requests requests);
    ApiKeyAuthorization addApiKeyAuthorization(ApiKeyAuthorization apiKeyAuthorization);
    void deleteApiKeyAuthorization(long id);
    ApiKeyAuthorization updateApiKeyAuthorization(ApiKeyAuthorization apiKeyAuthorization);
}