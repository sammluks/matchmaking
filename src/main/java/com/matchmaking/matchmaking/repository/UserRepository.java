package com.matchmaking.matchmaking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matchmaking.matchmaking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
