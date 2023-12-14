package com.server.exportU.service.impl;

import com.server.exportU.dto.UserDTO;
import com.server.exportU.mapper.UserMapper;
import com.server.exportU.model.User;
import com.server.exportU.repository.UserRepository;
import com.server.exportU.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream().map((user) -> userMapper.mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public void saveUser(UserDTO userDTO) {

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO getUserById(long id) {
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        this.userRepository.deleteById(id);
    }
}
