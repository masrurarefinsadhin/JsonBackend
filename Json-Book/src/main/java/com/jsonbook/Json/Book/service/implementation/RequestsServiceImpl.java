package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.repository.RequestsRepository;
import com.jsonbook.Json.Book.service.RequestsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestsServiceImpl implements RequestsService {
    private final RequestsRepository requestsRepository;

    public RequestsServiceImpl(RequestsRepository requestsRepository) {
        this.requestsRepository = requestsRepository;
    }

    @Override
    public List<Requests> findAllRequests() {
        return requestsRepository.findAll();
    }

    @Override
    public Requests saveRequests(Requests requests) {
        return requestsRepository.save(requests);
    }

    @Override
    public Requests updateRequests(Requests requests) {
        return requestsRepository.save(requests);
    }

    @Override
    public void deleteRequests(long id) {
        requestsRepository.deleteById(id);
    }

    @Override
    public Requests findRequests(long id) {
        return requestsRepository.findById(id).get();
    }

    @Override
    public List<Requests> findRequestsById(long id) {
        return requestsRepository.findRequestById(id);
    }
}
