package com.jsonbook.Json.Book.repository;
import com.jsonbook.Json.Book.entity.Groups;
import com.jsonbook.Json.Book.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);

    User findByEmail(String email);

    @Query("select model.groupAccess from User model where model.id = :id")
    List<Groups> findByIdV2(long id);
}
