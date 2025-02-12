package com.server.exportU.service.impl;

import com.server.exportU.dto.UserDto;
import com.server.exportU.mapper.UserMapper;
import com.server.exportU.entity.User;
import com.server.exportU.repository.UserRepository;
import com.server.exportU.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream().map((user) -> userMapper.mapToUserDto(user))
                .toList();
    }

    @Override
    public void saveUser(UserDto userDto) {}

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto getUserById(long id) {
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        this.userRepository.deleteById(id);
    }
}
