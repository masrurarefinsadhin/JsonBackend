package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.BasicAuthorization;
import com.jsonbook.Json.Book.entity.JwtBearer;
import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.repository.JwtBearerRepository;
import com.jsonbook.Json.Book.service.JwtBearerService;
import org.springframework.stereotype.Service;

@Service
public class JwtBearerServiceImpl implements JwtBearerService {

    private final JwtBearerRepository jwtBearerRepository;

    public JwtBearerServiceImpl(JwtBearerRepository jwtBearerRepository) {
        this.jwtBearerRepository = jwtBearerRepository;
    }

    @Override
    public JwtBearer findJwtBearer(Requests requests){
        return jwtBearerRepository.findJwtBarerByRequests(requests);
    }
    @Override
    public JwtBearer addJwtBearer(JwtBearer jwtBearer) {
        return jwtBearerRepository.save(jwtBearer);
    }

    @Override
    public void deleteJwtBearer(long id) {
        jwtBearerRepository.deleteById(id);
    }
    @Override
    public JwtBearer updateJwrBearer(JwtBearer jwtBearer) {
        return jwtBearerRepository.save(jwtBearer);
    }
}
