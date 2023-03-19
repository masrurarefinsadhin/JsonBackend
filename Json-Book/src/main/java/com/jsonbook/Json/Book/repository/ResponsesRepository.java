package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.ResponsesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResponsesRepository extends JpaRepository<ResponsesEntity, Long> {
    // for many to one relationship
    // there are two responses table exist in our database namely responses_table and responses table_2
    @Query(value = "SELECT * FROM responses_table_2 WHERE request_id = :id", nativeQuery = true)
    List<ResponsesEntity> findResponsesById(@Param("id") Long id);
}
