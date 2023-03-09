package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.entity.RequestEntity;

import java.util.List;

public interface RequestService {
    List<RequestEntity> findAllRequest();

    RequestEntity saveRequest(RequestEntity requestEntity);
    RequestEntity updateRequest(RequestEntity requestEntity);
    void deleteRequest(Long id);
}