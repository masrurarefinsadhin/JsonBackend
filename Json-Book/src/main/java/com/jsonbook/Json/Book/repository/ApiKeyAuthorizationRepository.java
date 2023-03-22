package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.ApiKeyAuthorization;
import com.jsonbook.Json.Book.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyAuthorizationRepository extends JpaRepository<ApiKeyAuthorization, Long> {
    ApiKeyAuthorization findApiKeyAuthorizationByRequests(Requests requests);
}
