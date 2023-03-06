package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {

    void delete(int id);
}
