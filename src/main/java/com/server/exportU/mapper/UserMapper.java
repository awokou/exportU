package com.server.exportU.mapper;

import com.server.exportU.dto.UserDTO;
import com.server.exportU.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUser(UserDTO userDTO) {
        if(userDTO == null){
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        return user;
    }

    public  UserDTO mapToUserDTO(User user) {
        if(user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());

        return  userDTO;
    }
}
