package com.server.exportU.service.impl;

import com.server.exportU.domain.dto.UserDto;
import com.server.exportU.domain.entity.User;
import com.server.exportU.repository.UserRepository;
import com.server.exportU.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteUserById(Integer id) {
        this.userRepository.deleteById(id);
    }

    private UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }
}
