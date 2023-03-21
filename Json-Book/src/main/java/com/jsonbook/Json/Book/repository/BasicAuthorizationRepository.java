package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.BasicAuthorization;
import com.jsonbook.Json.Book.entity.Forms;
import com.jsonbook.Json.Book.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasicAuthorizationRepository extends JpaRepository<BasicAuthorization, Long> {
    BasicAuthorization findBasicAuthorizationByRequests(Requests requests);
}
