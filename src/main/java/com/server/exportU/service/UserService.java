package com.server.exportU.service;

import com.server.exportU.dto.UserDto;
import com.server.exportU.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    void saveUser(UserDto userDTO);
    User findUserByEmail(String email);
    UserDto getUserById(long id);
    void deleteUserById(int id);
}
