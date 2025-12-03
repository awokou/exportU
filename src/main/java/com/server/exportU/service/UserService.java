package com.server.exportU.service;

import com.server.exportU.dto.UserDto;
import com.server.exportU.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
                ))
                .toList();
    }

    @Override
    public void deleteUserById(Integer id) {
        this.userRepository.deleteById(id);
    }
}
