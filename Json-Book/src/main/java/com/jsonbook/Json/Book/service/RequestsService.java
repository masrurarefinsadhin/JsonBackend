package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.entity.Requests;

import java.util.List;

public interface RequestsService {
    List<Requests> findAllRequests();
    Requests saveRequests(Requests requests);
    Requests updateRequests(Requests requests);
    void deleteRequests(long id);

    Requests findRequests(long id);
    List<Requests> findRequestsById(long id);
}
