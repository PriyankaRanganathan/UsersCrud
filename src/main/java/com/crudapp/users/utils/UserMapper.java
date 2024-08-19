package com.crudapp.users.utils;

import com.crudapp.users.dao.Users;
import com.crudapp.users.dto.UserDTO;

public class UserMapper {
    public static UserDTO userToUserDTO(Users user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(),user.isActive());
    }

    public static Users userDTOToUser(UserDTO userDTO) {
        return Users.builder()
                .id(userDTO.id())
                .username(userDTO.username())
                .password(userDTO.password())
                .active(userDTO.active())
                .build();
    }
}
