package com.server.exportU.service;

import com.server.exportU.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();
    void deleteUserById(Integer id);
}
