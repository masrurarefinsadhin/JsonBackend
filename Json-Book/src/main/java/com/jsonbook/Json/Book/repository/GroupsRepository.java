package com.jsonbook.Json.Book.repository;

import com.jsonbook.Json.Book.entity.Forms;
import com.jsonbook.Json.Book.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

    @Query(value = "DELETE FROM user_access WHERE group_id = :groupId", nativeQuery = true)
    void deleteGroupRefrence(@Param("groupId") Long groupId);
}
