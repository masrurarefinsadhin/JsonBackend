package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.JwtBearer;
import com.jsonbook.Json.Book.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtBearerRepository extends JpaRepository<JwtBearer, Long> {
    JwtBearer findJwtBarerByRequests(Requests requests);

}
