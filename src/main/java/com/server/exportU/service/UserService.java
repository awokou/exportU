package com.server.exportU.service;

import com.server.exportU.dto.UserDTO;
import com.server.exportU.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    User findUserByEmail(String email);
}
