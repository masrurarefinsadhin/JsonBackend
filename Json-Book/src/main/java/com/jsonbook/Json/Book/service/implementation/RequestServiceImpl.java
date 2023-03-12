package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.RequestEntity;
import com.jsonbook.Json.Book.repository.GroupRepository;
import com.jsonbook.Json.Book.repository.RequestRepository;
import com.jsonbook.Json.Book.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository repository) {
        this.requestRepository = repository;
    }


    @Override
    public List<RequestEntity> findAllRequest() {
        return requestRepository.findAll();
    }


    @Override
    public RequestEntity saveRequest(RequestEntity requestEntity) {
        return requestRepository.save(requestEntity);
    }

    @Override
    public RequestEntity updateRequest(RequestEntity requestEntity) {
        return requestRepository.save(requestEntity);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<RequestEntity> findRequestById(long groupId) {
        return requestRepository.findRequestById(groupId);
    }

}
