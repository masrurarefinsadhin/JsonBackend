package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestsRepository extends JpaRepository<Requests, Long> {
    @Query(value = "SELECT * FROM requests WHERE group_id = :groupId", nativeQuery = true)
    List<Requests> findRequestById(@Param("groupId") Long groupId);
}
