package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {
}
