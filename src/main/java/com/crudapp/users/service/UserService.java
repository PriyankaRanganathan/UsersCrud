package com.crudapp.users.service;

import com.crudapp.users.dao.Users;
import com.crudapp.users.dto.UserDTO;
import com.crudapp.users.exceptions.UnableToProcessException;
import com.crudapp.users.exceptions.UserNotFoundException;
import com.crudapp.users.repository.UserRepository;
import com.crudapp.users.utils.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        log.info("createUser: {}", userDTO);
        if (userDTO.id() != null) {
            throw new UnableToProcessException("User ID must be null while creating the user!");
        }
        Users users = UserMapper.userDTOToUser(userDTO);
        Users savedUser = userRepository.save(users);
        return UserMapper.userToUserDTO(savedUser);
    }

    public UserDTO getUser(Long id) {
        log.info("getUser: {}", id);
        if (id == null || id == 0L) {
            log.error("Id is invalid: {}", id);
            throw new UnableToProcessException("ID must not be null");
        }
        return userRepository.findById(id)
                .map(UserMapper::userToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public UserDTO updateUser(UserDTO userDto) {
        log.info("updateUser method entered");
        if (userDto.id() == null) {
            log.error("User ID must not be null");
            throw new UnableToProcessException("User ID should not be null while updating the user!");
        }
        Users users = UserMapper.userDTOToUser(userDto);
        Users updatedUser = userRepository.save(users);
        return UserMapper.userToUserDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        log.info("deleteUser: {}", id);
        if (id == null || id == 0L) {
            log.error("ID must not be null");
            throw new UnableToProcessException("ID must not be null");
        }
        userRepository.findById(id)
                .ifPresentOrElse(user -> userRepository.deleteById(user.getId()), () -> {
                    throw new UserNotFoundException("User not found!");
                });
    }

}
