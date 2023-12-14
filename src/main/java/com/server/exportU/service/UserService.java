package com.server.exportU.service;

import com.server.exportU.dto.UserDTO;
import com.server.exportU.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    void saveUser(UserDTO userDTO);
    User findUserByEmail(String email);
    UserDTO getUserById(long id);
    void deleteUserById(int id);
}
