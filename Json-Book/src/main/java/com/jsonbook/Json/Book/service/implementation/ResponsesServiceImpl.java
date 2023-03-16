package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.RequestEntity;
import com.jsonbook.Json.Book.entity.ResponsesEntity;
import com.jsonbook.Json.Book.repository.RequestRepository;
import com.jsonbook.Json.Book.repository.ResponsesRepository;
import com.jsonbook.Json.Book.service.ResponsesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsesServiceImpl implements ResponsesService{
    private final ResponsesRepository responsesRepository;
    private final RequestRepository requestRepository;


    public ResponsesServiceImpl(ResponsesRepository responsesRepository, RequestRepository requestRepository) {
        this.responsesRepository = responsesRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public List<ResponsesEntity> findAllResponses() {
        return responsesRepository.findAll();
    }

    @Override
    public ResponsesEntity saveResponses(ResponsesEntity responsesEntity) {
        // taking that request object that we passed as foreign key
        // and extracting all information (data) associated with that foreign key
        Long requestId = responsesEntity.getRequestEntity().getId();
        RequestEntity requestEntity = requestRepository.findById(requestId).get();
        responsesEntity.setRequestEntity(requestEntity);
        return responsesRepository.save(responsesEntity);
//        responsesEntity.setRequestedAt(Instant.now());
    }

    @Override
    public ResponsesEntity updateResponses(ResponsesEntity responsesEntity) {
        return responsesRepository.save(responsesEntity);
    }

    @Override
    public List<ResponsesEntity> findResponsesById(long id) {
        return responsesRepository.findResponsesById(id);
    }
}
