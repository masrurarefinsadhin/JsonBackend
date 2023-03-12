package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.GroupEntity;
import com.jsonbook.Json.Book.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

    @Query(value = "SELECT * FROM request WHERE group_id = :groupId", nativeQuery = true)
    List<RequestEntity> findRequestById(@Param("groupId") Long groupId);
}
