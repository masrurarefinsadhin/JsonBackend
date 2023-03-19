package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.Forms;
import com.jsonbook.Json.Book.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormsRepository extends JpaRepository<Forms, Long> {
    @Query(value = "SELECT * FROM forms WHERE request_id = :requestId and form_type = :requestBodyType", nativeQuery = true)
    List<Forms> findFormsByRequestId(@Param("requestId") Long requestId,@Param("requestBodyType") String requestBodyType);
}
