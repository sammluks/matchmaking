package com.matchmaking.matchmaking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.matchmaking.matchmaking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getByUsername(@Param("username") String username);
}
