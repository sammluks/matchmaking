package com.matchmaking.matchmaking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matchmaking.matchmaking.dto.UserDto;
import com.matchmaking.matchmaking.model.User;
import com.matchmaking.matchmaking.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public void delete(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException());
        repository.delete(user);
    }

    @Transactional
    public void register(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getUserName(), userDto.getEmail());
        repository.save(user);
    }

    @Transactional
    public Page<UserDto> listUsers(Pageable pageable) {
        return repository.findAll(pageable).map(UserDto::new);
    }

    @Transactional
    public UserDto getUser(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException());
        return new UserDto(user);
    }
}
