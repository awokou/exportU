package com.server.exportU.service;

import com.server.exportU.dto.UserDto;
import com.server.exportU.entity.User;
import com.server.exportU.mapper.UserMapper;
import com.server.exportU.repository.UserRepository;
import com.server.exportU.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setFirstName("Komivi");
        userList.add(user);
        lenient().when(userRepository.findAll()).thenReturn(userList);
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setFirstName("Komivi");
        userDtoList.add(userDto);
        lenient().when(userMapper.mapToUserDto(any(User.class))).thenReturn(userDto);
        List<UserDto> result = userService.getAllUsers();
        assertEquals(1, result.size());
    }
}
