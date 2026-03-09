package com.server.exportU.service;

import java.util.List;

import com.server.exportU.domain.dto.UserDto;

public interface UserService {

    List<UserDto> getAllUsers();

    void deleteUserById(Integer id);
}
